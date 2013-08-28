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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.Pojo.PuntoRecoleccionPojo;
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
    private Integer idTipo;
    private String municipalidad;
    private String nombrePunto;
    private String descripcion;
    private Collection<String> tipoRecoleccionSeleccionados;
    private Collection<PuntoRecoleccionPojo> puntosRecoleccionPojo;
    private PuntoRecoleccionPojo prSeleccionadoPojo;
    private Collection<Municipalidad> ms;
    private Collection<TipoRecoleccion> tipoRecoleccions;
    private MAccionesGenerales ag;
    private MMessaegeController mc;
    
    @PostConstruct
    public void init() {
        
        ms = municipalidades.listaMunicipalidades();
        tipoRecoleccions = crudTipoRecoleccion.listaTipoRecoleccion();
        cargarPuntosRecoleccion();
        mc = new MMessaegeController();
        ag = new MAccionesGenerales();    
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
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

    public Collection<PuntoRecoleccionPojo> getPuntosRecoleccionPojo() {
        return puntosRecoleccionPojo;
    }

    public void setPuntosRecoleccionPojo(Collection<PuntoRecoleccionPojo> puntosRecoleccion) {
        this.puntosRecoleccionPojo = puntosRecoleccion;
    }

    public PuntoRecoleccionPojo getPrSeleccionadoPojo() {
        return prSeleccionadoPojo;
    }

    public void setPrSeleccionadoPojo(PuntoRecoleccionPojo prSeleccionadoPojo) {
        this.prSeleccionadoPojo = prSeleccionadoPojo;
    }
    
    public MPuntoRecoleccion() {
    }
    
    public void nuevoPunto() {
        try {
            crudRecoleccion.crearPuntoRecoleccion(direccion.toUpperCase(), nombrePunto.toUpperCase(), descripcion, municipalidad, tipoRecoleccionSeleccionados);
            mc.mensajeRetroalimentacion("Operacion", "Exitosa");
            resetCampos();
        } catch (Exception e) {
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }

    public void actualizarPunto() {
        
        try {
            
            crudRecoleccion.editarPuntoRecoleccion(idTipo, direccion.toUpperCase(), nombrePunto.toUpperCase(), descripcion, municipalidad, tipoRecoleccionSeleccionados);
            resetCampos();
            ag.actualizarPagina();
        } catch (Exception ex) {
            mc.mensajeRetroalimentacion("Error", ex.getMessage());
        }

    }

    public void borrarPunto() {

        try {
            setearPunto();
            crudRecoleccion.eliminarPuntoRecoleccion(idTipo);
            ag.actualizarPagina();
        } catch (Exception ex) {
            mc.mensajeRetroalimentacion("Error", ex.getMessage());
        }
    }

    public void setearPunto() {
       
        idTipo = prSeleccionadoPojo.getIdTipo();
        nombrePunto = prSeleccionadoPojo.getNombrePunto();
        municipalidad = prSeleccionadoPojo.getMunicipalidad();
        direccion = prSeleccionadoPojo.getDireccionPunto();
        descripcion = prSeleccionadoPojo.getDescripcion();
        
   }

    public void resetCampos() {

        this.direccion = null;
        this.descripcion = null;
        this.municipalidad = null;
        this.nombrePunto = null;
        
        this.tipoRecoleccionSeleccionados = null;
    }
    
    public String returnStringCollection(Collection<TipoRecoleccion> tipoRecoleccion){
    
        String str = "";
        String tmp;
        for(Iterator<TipoRecoleccion> it = tipoRecoleccion.iterator(); it.hasNext();){         
                tmp = it.next().getNombreTipoRecoleccion();
                str += tmp+" - ";           
        }     
        return str;
    }
    
    public void cargarPuntosRecoleccion(){
        
        Collection<PuntoRecoleccion> prs = crudRecoleccion.listaPuntosRecoleccion();
        PuntoRecoleccion tmp;
        puntosRecoleccionPojo = new LinkedList();
        for(Iterator<PuntoRecoleccion> it = prs.iterator(); it.hasNext();){
            PuntoRecoleccionPojo prp = new PuntoRecoleccionPojo();
            tmp = it.next();
            prp.setIdTipo(tmp.getIdPunto());
            prp.setDescripcion(tmp.getDescripcionPunto());
            prp.setDireccionPunto(tmp.getDireccionPunto());
            prp.setMunicipalidad(tmp.getNombreMunicipalidad().getNombreMunicipalidad());
            prp.setNombrePunto(tmp.getNombrePunto());
            prp.setTiposRecoleccion(returnStringCollection(tmp.getTipoRecoleccionCollection()));
            puntosRecoleccionPojo.add(prp);
        }
        
    }
}
