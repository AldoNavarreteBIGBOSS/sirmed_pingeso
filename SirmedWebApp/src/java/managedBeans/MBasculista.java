/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Basculista;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import sessionBeans.CrudBasculistaLocal;
import sessionBeans.CrudUsuarioLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mBasculista")
@RequestScoped
public class MBasculista {

   
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @EJB
    private CrudBasculistaLocal crudBasculista;
    
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Collection<Basculista> basculista;
    private Basculista basculistaSeleccionado;
    private AccionesGenerales ag;
    private MessaegeController mc;
    private Basculista[] test;
    
    @PostConstruct
    public void init() {
   
        basculista = crudBasculista.listaBasculistas();
        mc = new MessaegeController();
        basculistaSeleccionado = new Basculista();
        ag = new AccionesGenerales();
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

    

  

    public MBasculista() {
    }

    public void nuevoBasculista(ActionEvent actionEvent) {
             
        try {
            
            crudUsuario.crearUsuario(rut, email);
            crudBasculista.crearBasculista(rut, nombre, apellido, telefono);
            mc.addInfo(actionEvent, "Basculista " + nombre + " " + apellido, "Ingresado con éxito");
            resetCampos();
        } catch (Exception e) {
            mc.addError(actionEvent);
        }
    }

    public void actualizarBasculista() {

        try {
            rut = basculistaSeleccionado.getRut();
            crudBasculista.editarBasculista(rut, nombre, apellido, telefono);
            resetCampos();
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarBasculista() {

        try {
            setearBasculista();
            crudBasculista.eliminarBasculista(rut, nombre, apellido, telefono);
            crudUsuario.eliminarUsuario(rut);
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setearBasculista() {
        rut = basculistaSeleccionado.getRut();
        nombre = basculistaSeleccionado.getNombreB();
        apellido = basculistaSeleccionado.getApellidoB();
        telefono = basculistaSeleccionado.getTelefonoB();
        
    }

    public void resetCampos() {

        this.rut = null;
        this.nombre = null;
        this.apellido = null;
        this.telefono = null;
        this.email = null;
        
    }
}