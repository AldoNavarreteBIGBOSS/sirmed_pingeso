/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Basculista;
import entities.TurnoTrabajo;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import sessionBeans.CrudBasculistaLocal;
import sessionBeans.CrudUsuarioLocal;
import sessionBeans.ListaTurnosLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mBasculista")
@RequestScoped
public class MBasculista{
    @EJB
    private ListaTurnosLocal listaTurnos;
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @EJB
    private CrudBasculistaLocal crudBasculista;
    
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String turno;
    private Collection<TurnoTrabajo> turnoTrabajos;
    private Collection<Basculista> basculista;
    private MessaegeController mc;
    private Basculista basculistaSeleccionado;
    private boolean flag;

   
    @PostConstruct
    public void init(){
        turnoTrabajos = listaTurnos.listaTurnos();
        basculista = crudBasculista.listaBasculistas();
        mc = new MessaegeController(); 
        basculistaSeleccionado = new Basculista();
        flag = false;
    }
 public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Basculista getBasculistaSeleccionado() {
        return basculistaSeleccionado;
    }

    public void setBasculistaSeleccionado(Basculista basculistaSeleccionado) {
        this.basculistaSeleccionado = basculistaSeleccionado;
    }
    
    public Collection<Basculista> getBasculista() {
        return basculista;
    }

    public void setBasculista(Collection<Basculista> basculista) {
        this.basculista = basculista;
    }

    public Collection<TurnoTrabajo> getTurnoTrabajos() {
        return turnoTrabajos;
    }

    public void setTurnoTrabajos(Collection<TurnoTrabajo> turnoTrabajos) {
        this.turnoTrabajos = turnoTrabajos;
    }
    
    public CrudUsuarioLocal getCrudUsuario() {
        return crudUsuario;
    }

    public void setCrudUsuario(CrudUsuarioLocal crudUsuario) {
        this.crudUsuario = crudUsuario;
    }

    public CrudBasculistaLocal getCrudBasculista() {
        return crudBasculista;
    }

    public void setCrudBasculista(CrudBasculistaLocal crudBasculista) {
        this.crudBasculista = crudBasculista;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public MBasculista() {
    }
    
    public void nuevoBasculista(ActionEvent actionEvent){
        
        try{
            crudUsuario.crearUsuario(rut, email);
            crudBasculista.crearBasculista(rut, turno, nombre, apellido, telefono);
            mc.addInfo(actionEvent, "Basculista "+nombre+" "+apellido, "Ingresado con éxito");     
            resetCampos();
        }
        catch(Exception e){
            mc.addError(actionEvent);
        }
    }
    
    public void editarBasculista(){
       
    }
    
    public void setearBasculista(){
        this.rut = basculistaSeleccionado.getRut();
        this.nombre = basculistaSeleccionado.getNombreB();
        this.apellido = basculistaSeleccionado.getApellidoB();
        this.telefono = basculistaSeleccionado.getTelefonoB();
        this.turno = basculistaSeleccionado.getNombreTurno().getNombreTurno();
        this.flag = true;
        System.out.println("HOLA");
    }
    
    public void resetCampos(){
    
            this.rut = null;
            this.nombre = null;
            this.apellido = null;
            this.telefono = null;
            this.email = null;
            this.turno = null;
    }
    
    
}
