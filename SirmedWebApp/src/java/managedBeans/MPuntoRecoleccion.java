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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    private String nombrePunto;
    private String descripcion;
    private Collection<String> tipoRecoleccionSeleccionados;
    private Collection<PuntoRecoleccion> puntosRecoleccion;
    private PuntoRecoleccion prSeleccionado;
    private Collection<Municipalidad> ms;
    private Collection<TipoRecoleccion> tipoRecoleccions;
    private MAccionesGenerales ag;
    private MMessaegeController mc;
    
    @PostConstruct
    public void init() {
        ms = municipalidades.listaMunicipalidades();
        tipoRecoleccions = crudTipoRecoleccion.listaTipoRecoleccion();
        puntosRecoleccion = crudRecoleccion.listaPuntosRecoleccion();
        mc = new MMessaegeController();
        ag = new MAccionesGenerales();    
    }

    public Collection<Municipalidad> getMs() {
        return ms;
    }

    public void setMs(Collection<Municipalidad> ms) {
        this.ms = ms;
    }

    public Collection<TipoRecoleccion> getTipoRecoleccions() {
        return tipoRecoleccions;
    }

    public void setTipoRecoleccions(Collection<TipoRecoleccion> tipoRecoleccions) {
        this.tipoRecoleccions = tipoRecoleccions;
    }

    public Collection<String> getTipoRecoleccionSeleccionados() {
        return tipoRecoleccionSeleccionados;
    }

    public void setTipoRecoleccionSeleccionados(Collection<String> tipoRecoleccion) {
        this.tipoRecoleccionSeleccionados = tipoRecoleccion;
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

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public void nuevoPunto() {
        try {
            crudRecoleccion.crearPuntoRecoleccion(direccion, nombrePunto, descripcion, municipalidad, tipoRecoleccionSeleccionados);
            mc.mensajeRetroalimentacion("Operacion", "Exitosa");
            resetCampos();
        } catch (Exception e) {
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }

    public void actualizarPunto() {

        try {
            direccion = prSeleccionado.getDireccionPunto();
            crudRecoleccion.editarPuntoRecoleccion(direccion.toUpperCase(), nombrePunto, descripcion);
            resetCampos();
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarPunto() {

        try {
            setearPunto();
            crudRecoleccion.eliminarPuntoRecoleccion(direccion, nombrePunto, descripcion);
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setearPunto() {
        direccion = prSeleccionado.getDireccionPunto();
        nombrePunto = prSeleccionado.getNombrePunto();
        descripcion = prSeleccionado.getDescripcionPunto();
   }

    public void resetCampos() {

        this.direccion = null;
        this.descripcion = null;
        this.municipalidad = null;
        this.nombrePunto = null;
    }
}
