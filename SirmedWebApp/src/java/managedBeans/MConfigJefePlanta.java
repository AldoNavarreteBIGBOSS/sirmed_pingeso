/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import managedBeans.Pojo.ListasComboPojo;
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
    private MAutentificador autentificador;
    
    private String rutJP;
    private String hora1;
    private String hora2;
    private List<ListasComboPojo> listaHorarios;
    private JefePlantaPojo jefePlantaPojo;
    private MMessaegeController mc;
    
    public MConfigJefePlanta() {
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
        mc = new MMessaegeController();
        rutJP = autentificador.getUsername();
        
        cargarDatosJefe();
        cargarLista();
    }
    
    private void cargarLista(){
    
       listaHorarios = new LinkedList<ListasComboPojo>();
        for(Integer i = 1; i <= 24; i++){
           ListasComboPojo lh = new ListasComboPojo();
           lh.setEtiqueta(i.toString()+":00");
           lh.setValor(i.toString());
           listaHorarios.add(lh);
        }
    
    }
    
    public void determinarHora(){
        try{
            informeMail.determinarHora(hora1, hora2);
            mc.mensajeRetroalimentacion("Operación Exitosa", "Horarios modificados");
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
}
