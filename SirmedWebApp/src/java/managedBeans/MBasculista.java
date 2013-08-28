/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Basculista;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.CrudBasculistaLocal;
import sessionBeans.CrudUsuarioLocal;
import sessionBeans.InformeMailLocal;
import sessionBeans.MensajeriaLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mBasculista")
@RequestScoped
public class MBasculista {
    @EJB
    private InformeMailLocal informeMail;
    @EJB
    private MensajeriaLocal mensajeria;
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @EJB
    private CrudBasculistaLocal crudBasculista;
    
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String rutActivar;
    private Collection<Basculista> basculista;
    private Basculista basculistaSeleccionado;
    private MAccionesGenerales ag;
    private MMessaegeController mc;
    
    
    @PostConstruct
    public void init() {
        basculista = crudBasculista.listaBasculistas();
        mc = new MMessaegeController();
        ag = new MAccionesGenerales();
    }

    public String getRutActivar() {
        return rutActivar;
    }

    public void setRutActivar(String rutActivar) {
        this.rutActivar = rutActivar;
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

    public void nuevoBasculista() {
        try {
            crudUsuario.crearUsuario(rut, email);
            crudBasculista.crearBasculista(rut, nombre.toUpperCase(), apellido.toUpperCase(), telefono);
            mensajeria.enviarMensajeBienvenida(email, rut, nombre.toUpperCase()+" "+apellido.toUpperCase());
            mc.mensajeRetroalimentacion("Operación Exitosa", null);
            resetCampos();
        } catch (Exception ex) {
            mc.mensajeRetroalimentacion("Error", ex.getMessage());
        }    
    }

    public void actualizarBasculista() {

        try {
            rut = basculistaSeleccionado.getRut();
            crudBasculista.editarBasculista(rut, nombre.toUpperCase(), apellido.toUpperCase(), telefono);
            resetCampos();
            ag.actualizarPagina();
        } catch (Exception e) {
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }

    }

    public void borrarBasculista() {

        try {
            rut = basculistaSeleccionado.getRut();
            crudBasculista.eliminarBasculista(rut);
            crudUsuario.eliminarUsuario(rut);
            ag.actualizarPagina();
        } catch (Exception ex) {
            mc.mensajeRetroalimentacion("Error", ex.getMessage());
        }
    }

    public void setearBasculista() {     
        rut = basculistaSeleccionado.getRut();
        nombre = basculistaSeleccionado.getNombreB();
        apellido = basculistaSeleccionado.getApellidoB();
        telefono = basculistaSeleccionado.getTelefonoB();       
    }

    public void resetCampos(){
        this.rut = null;
        this.nombre = null;
        this.apellido = null;
        this.telefono = null;
        this.email = null;      
    }
    
    public void activarRut(){
    
       try{
          
           crudBasculista.activarBasculistaUsuario(rutActivar);
           ag.actualizarPagina();
       } 
       catch(Exception e){
           mc.mensajeRetroalimentacion("Error", e.getMessage());
       }
    }
}
