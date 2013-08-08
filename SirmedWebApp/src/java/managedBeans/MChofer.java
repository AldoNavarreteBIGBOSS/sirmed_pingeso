/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Chofer;
import entities.Municipalidad;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import sessionBeans.CrudChoferLocal;
import sessionBeans.MunicipalidadesLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mChofer")
@RequestScoped
public class MChofer {

    @EJB
    private MunicipalidadesLocal municipalidades;
    @EJB
    private CrudChoferLocal crudChofer;
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String municipalidad;
    private Collection<Municipalidad> ms;
    private Collection<Chofer> choferes;
    private Chofer choferSeleccionado;
    private MessaegeController mc;
    private AccionesGenerales ag;

    @PostConstruct
    public void init() {
        ms = municipalidades.listaMunicipalidades();
        choferes = crudChofer.listaChoferes();
        choferSeleccionado = new Chofer();
        mc = new MessaegeController();
        ag = new AccionesGenerales();
    }

    public MunicipalidadesLocal getMunicipalidades() {
        return municipalidades;
    }

    public void setMunicipalidades(MunicipalidadesLocal municipalidades) {
        this.municipalidades = municipalidades;
    }

    public CrudChoferLocal getCrudChofer() {
        return crudChofer;
    }

    public void setCrudChofer(CrudChoferLocal crudChofer) {
        this.crudChofer = crudChofer;
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

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public Collection<Municipalidad> getMs() {
        return ms;
    }

    public void setMs(Collection<Municipalidad> ms) {
        this.ms = ms;
    }

    public Collection<Chofer> getChoferes() {
        return choferes;
    }

    public void setChoferes(Collection<Chofer> choferes) {
        this.choferes = choferes;
    }

    public Chofer getChoferSeleccionado() {
        return choferSeleccionado;
    }

    public void setChoferSeleccionado(Chofer choferSeleccionado) {
        this.choferSeleccionado = choferSeleccionado;
    }

    public MChofer() {
    }

    public void nuevoChofer(ActionEvent actionEvent) {

        try {
            crudChofer.crearChofer(rut, nombre, apellido, telefono, email, municipalidad);
            
            resetCampos();
        } catch (Exception e) {
            
        }
    }

    public void actualizarBasculista() {

        try {
            rut = choferSeleccionado.getRutChofer();
            crudChofer.editarChofer(rut, nombre, apellido, telefono, email, municipalidad);
            resetCampos();
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarBasculista() {

        try {
            setearBasculista();
            crudChofer.eliminarChofer(rut, nombre, apellido, telefono, email, municipalidad);
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setearBasculista() {
        rut = choferSeleccionado.getRutChofer();
        nombre = choferSeleccionado.getNombreChofer();
        apellido = choferSeleccionado.getApellidoChofer();
        telefono = choferSeleccionado.getTelefonoChofer();
        municipalidad = choferSeleccionado.getNombreMunicipalidad().getNombreMunicipalidad();
    }

    public void resetCampos() {

        this.rut = null;
        this.nombre = null;
        this.apellido = null;
        this.telefono = null;
        this.email = null;
        this.municipalidad = null;
    }
}
