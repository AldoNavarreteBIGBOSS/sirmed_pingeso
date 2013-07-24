/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Municipalidad;
import entities.PuntoRecoleccion;
import entities.TipoRecoleccion;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import sessionBeans.CrudRecoleccionLocal;
import sessionBeans.CrudTipoRecoleccionLocal;
import sessionBeans.MunicipalidadesLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mPuntoRecoleccion")
@RequestScoped
public class MPuntoRecoleccion {
    @EJB
    private CrudTipoRecoleccionLocal crudTipoRecoleccion;
    @EJB
    private MunicipalidadesLocal municipalidades;
    @EJB
    private CrudRecoleccionLocal crudRecoleccion;
    
    private String direccion;
    private String municipalidad;
    private String nombreTipo;
    private String descripcion;
    private Collection<Municipalidad> municipalidads;
    private Collection<TipoRecoleccion> tipoRecoleccion;
    private Collection<PuntoRecoleccion> puntosRecoleccion;
    private PuntoRecoleccion prSeleccionado;
    private AccionesGenerales ag;
    private MessaegeController mc;
    
    @PostConstruct
    public void init() {
        municipalidads = municipalidades.listaMunicipalidades();
        puntosRecoleccion = crudRecoleccion.listaPuntosRecoleccion();
        tipoRecoleccion = crudTipoRecoleccion.listaTipoRecoleccion();
        prSeleccionado = new PuntoRecoleccion();
        mc = new MessaegeController();
        ag = new AccionesGenerales();
    }

    public CrudTipoRecoleccionLocal getCrudTipoRecoleccion() {
        return crudTipoRecoleccion;
    }

    public void setCrudTipoRecoleccion(CrudTipoRecoleccionLocal crudTipoRecoleccion) {
        this.crudTipoRecoleccion = crudTipoRecoleccion;
    }

    public MunicipalidadesLocal getMunicipalidades() {
        return municipalidades;
    }

    public void setMunicipalidades(MunicipalidadesLocal municipalidades) {
        this.municipalidades = municipalidades;
    }

    public CrudRecoleccionLocal getCrudRecoleccion() {
        return crudRecoleccion;
    }

    public void setCrudRecoleccion(CrudRecoleccionLocal crudRecoleccion) {
        this.crudRecoleccion = crudRecoleccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Municipalidad> getMunicipalidads() {
        return municipalidads;
    }

    public void setMunicipalidads(Collection<Municipalidad> municipalidads) {
        this.municipalidads = municipalidads;
    }

    public Collection<TipoRecoleccion> getTipoRecoleccion() {
        return tipoRecoleccion;
    }

    public void setTipoRecoleccion(Collection<TipoRecoleccion> tipoRecoleccion) {
        this.tipoRecoleccion = tipoRecoleccion;
    }

    public Collection<PuntoRecoleccion> getPuntosRecoleccion() {
        return puntosRecoleccion;
    }

    public void setPuntosRecoleccion(Collection<PuntoRecoleccion> puntosRecoleccion) {
        this.puntosRecoleccion = puntosRecoleccion;
    }

    public PuntoRecoleccion getPrSeleccionado() {
        return prSeleccionado;
    }

    public void setPrSeleccionado(PuntoRecoleccion prSeleccionado) {
        this.prSeleccionado = prSeleccionado;
    }
    
    public MPuntoRecoleccion() {
    }
    
    public void nuevoPunto(ActionEvent actionEvent) {

        try {
            crudRecoleccion.crearPuntoRecoleccion(direccion, municipalidad, nombreTipo, descripcion);
            mc.addInfo(actionEvent, "Punto de Recolección Ubicado en " + direccion+" en la Municipalidad de "+municipalidad, "Ingresado con éxito");
            resetCampos();
        } catch (Exception e) {
            mc.addError(actionEvent);
        }
    }

    public void actualizarPunto() {

        try {
            direccion = prSeleccionado.getDireccionPunto();
            crudRecoleccion.editarPuntoRecoleccion(direccion, municipalidad, nombreTipo, descripcion);
            resetCampos();
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarPunto() {

        try {
            setearPunto();
            crudRecoleccion.eliminarPuntoRecoleccion(direccion, municipalidad, nombreTipo, descripcion);
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setearPunto() {
        direccion = prSeleccionado.getDireccionPunto();
        municipalidad = prSeleccionado.getNombreMunicipalidad().getNombreMunicipalidad();
        nombreTipo = prSeleccionado.getNombreTipoRecoleccion().getNombreTipoRecoleccion();
        descripcion = prSeleccionado.getDescrpPunto();
   }

    public void resetCampos() {

        this.direccion = null;
        this.descripcion = null;
        this.municipalidad = null;
        this.nombreTipo = null;
    }
}
