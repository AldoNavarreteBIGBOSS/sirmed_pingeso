/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;


import entities.JefePlanta;
import entities.Usuario;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.Pojo.JefePlantaPojo;
import managedBeans.Pojo.ListasComboPojo;
import sessionBeans.CrudJefePlantaLocal;
import sessionBeans.CrudUsuarioLocal;
import sessionBeans.InformeMailLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mConfigJefePlanta")
@RequestScoped
public class MConfigJefePlanta {
    @EJB
    private CrudJefePlantaLocal crudJefePlanta;
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @EJB
    private InformeMailLocal informeMail;    
    @Inject
    private MAccionesGenerales ag;
    @Inject
    private MMessaegeController mc;
    
    private String rutJP;
    private String hora1;
    private String hora2;
    private String minuto1;
    private List<ListasComboPojo> listaHorarios;
    private List<ListasComboPojo> listaMinutos;
    private JefePlantaPojo jefePlantaPojo;
    
    
    public MConfigJefePlanta() {
    }

    public String getMinuto1() {
        return minuto1;
    }

    public void setMinuto1(String minuto1) {
        this.minuto1 = minuto1;
    }   

    public List<ListasComboPojo> getListaMinutos() {
        return listaMinutos;
    }

    public void setListaMinutos(List<ListasComboPojo> listaMinutos) {
        this.listaMinutos = listaMinutos;
    }

    public JefePlantaPojo getJefePlantaPojo() {
        return jefePlantaPojo;
    }

    public void setJefePlantaPojo(JefePlantaPojo jefePlantaPojo) {
        this.jefePlantaPojo = jefePlantaPojo;
    }

    public String getHora1() {
        return hora1;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

    public List<ListasComboPojo> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<ListasComboPojo> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }
    
    
    
    @PostConstruct
    public void init(){
        
        rutJP = ag.devolverUsername();
        
        cargarDatosJefe();
        cargarLista();
    }
    
    private void cargarLista(){
    
       listaHorarios = new LinkedList<ListasComboPojo>();
        for(Integer i = 1; i <= 24; i++){
           ListasComboPojo lh = new ListasComboPojo();
           lh.setEtiqueta(i.toString());
           lh.setValor(i.toString());
           listaHorarios.add(lh);
        }
        
        listaMinutos = new LinkedList<>();
        for(Integer i = 0; i<= 59; i++){
            ListasComboPojo lm = new ListasComboPojo();
            if(i < 10){
                lm.setEtiqueta("0"+i.toString());
            }
            else{
                lm.setEtiqueta(i.toString());
            }
            lm.setValor(i.toString());
            listaMinutos.add(lm);
        }
        
    
    }
    
    public void determinarHora(){
        try{
            informeMail.determinarHora(hora1,minuto1, hora2);
            if(minuto1.length()==1){
                mc.mensajeRetroalimentacion("Operación Exitosa", "Horarios modificados a las "+hora1+":"+"0"+minuto1+" y "+hora2+":"+minuto1);
            }
            else{
                mc.mensajeRetroalimentacion("Operación Exitosa", "Horarios modificados a las "+hora1+":"+minuto1+" y "+hora2+":"+minuto1);
            }
        }
        catch(Exception e){}
    }
    
    private void cargarDatosJefe(){
    
        
        jefePlantaPojo = new JefePlantaPojo();
        try{
            Usuario u = crudUsuario.entregarPorRut(rutJP);
            JefePlanta jp = crudJefePlanta.entregarPorRut(rutJP);
            
            jefePlantaPojo.setRutJP(u.getRut());
            jefePlantaPojo.setNombreJP(jp.getNombreJp());
            jefePlantaPojo.setApellidoJP(jp.getApellidoJp());
            jefePlantaPojo.setTelefonoJP(jp.getTelefonoJp());
            jefePlantaPojo.setEmailJP(u.getMail());
            
           
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void editarDatosPersonales(){
        try{
        
                crudJefePlanta.actualizarDatosPersonales(jefePlantaPojo.getRutJP(), jefePlantaPojo.getNombreJP(), jefePlantaPojo.getApellidoJP(), jefePlantaPojo.getTelefonoJP(), jefePlantaPojo.getEmailJP());
                mc.mensajeRetroalimentacion("Operación Exitosa", "Datos actualizados");
               
            
        }
        catch(Exception e){
            mc.mensajeRetroalimentacion("ERROR", e.getMessage());
        }
    }
    
    public void cambiarContraseña(){
        try{
        if(crudUsuario.analizarContraseña(jefePlantaPojo.getRutJP(), jefePlantaPojo.getAntiguoPass())){
            if(jefePlantaPojo.getNewPass().compareTo(jefePlantaPojo.getConfNewPass())==0){
                crudUsuario.actualizarUsuario(jefePlantaPojo.getRutJP(), jefePlantaPojo.getNewPass());
                mc.mensajeRetroalimentacion("Operación Exitosa", "Nueva contraseña establecida");
                 resetCampos();
            }
            else{
                mc.mensajeRetroalimentacion("Error", "Las contraseñas no son iguales");
            }
        }
        else{
            mc.mensajeRetroalimentacion("Error", "La contraseña antigua no corresponde");
        }
        
        }
        catch(Exception e){
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }
    public void resetCampos(){
        jefePlantaPojo = null;
    }
}
