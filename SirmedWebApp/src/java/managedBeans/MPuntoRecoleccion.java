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
    private String nombrePunto;
    private String descripcion;
    private Collection<PuntoRecoleccion> puntosRecoleccion;
    private PuntoRecoleccion prSeleccionado;
    private AccionesGenerales ag;
    private MessaegeController mc;
    
    @PostConstruct
    public void init() {
        
        puntosRecoleccion = crudRecoleccion.listaPuntosRecoleccion();
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
    
    public void nuevoPunto(ActionEvent actionEvent) {

        try {
            crudRecoleccion.crearPuntoRecoleccion(direccion, nombrePunto, descripcion);
            mc.addInfo(actionEvent, "Punto de Recolección Ubicado en " + direccion+" en la Municipalidad de "+municipalidad, "Ingresado con éxito");
            resetCampos();
        } catch (Exception e) {
            mc.addError(actionEvent);
        }
    }

    public void actualizarPunto() {

        try {
            direccion = prSeleccionado.getDireccionPunto();
            crudRecoleccion.editarPuntoRecoleccion(direccion, nombrePunto, descripcion);
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
