/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Camion;
import entities.Municipalidad;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.CrudCamionLocal;
import sessionBeans.MunicipalidadesLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mCamiones")
@RequestScoped
public class MCamiones {
    @EJB
    private MunicipalidadesLocal municipalidades1;
    
    @EJB
    private CrudCamionLocal crudCamion;
    
    private String patente;
    private String municipalidad;
    private String nombreTipo;
    private Collection<Municipalidad> municipalidades;
    private Collection<Camion> camiones;
    private Camion camionSeleccionado;
    private MessaegeController mc;
    private AccionesGenerales ag;
    
    public MCamiones() {
    }
    
    @PostConstruct
    public void init(){
        camionSeleccionado = new Camion();
        municipalidades = municipalidades1.listaMunicipalidades();
        camiones = crudCamion.listaCamiones();
        mc = new MessaegeController(); 
        ag = new AccionesGenerales();
    }

    public MunicipalidadesLocal getMunicipalidades1() {
        return municipalidades1;
    }

    public void setMunicipalidades1(MunicipalidadesLocal municipalidades1) {
        this.municipalidades1 = municipalidades1;
    }

    public CrudCamionLocal getCrudCamion() {
        return crudCamion;
    }

    public void setCrudCamion(CrudCamionLocal crudCamion) {
        this.crudCamion = crudCamion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
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

    public Collection<Municipalidad> getMunicipalidades() {
        return municipalidades;
    }

    public void setMunicipalidades(Collection<Municipalidad> municipalidades) {
        this.municipalidades = municipalidades;
    }

    public Collection<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(Collection<Camion> camiones) {
        this.camiones = camiones;
    }

    public Camion getCamionSeleccionado() {
        return camionSeleccionado;
    }

    public void setCamionSeleccionado(Camion camionSeleccionado) {
        this.camionSeleccionado = camionSeleccionado;
    }
    
    public void nuevoCamion(){
        try{
            crudCamion.crearCamion(patente, municipalidad, nombreTipo);
            mc.addInfo(null, "Camión con patente "+patente, " Ingresado con éxito");
            resetCampos();
        }
        catch(Exception e){
            mc.addError(null);
        }
    }
    
    public void actualizarCamion(){
        try {
            patente = camionSeleccionado.getPatenteCamion();
            crudCamion.editarCamion(patente, municipalidad, nombreTipo);
            resetCampos();
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarCamion(){
                   
        try {
            setearCamion();
            crudCamion.eliminarCamion(patente, municipalidad, nombreTipo);
            ag.actualizarPagina();
        } catch (IOException ex) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setearCamion(){
        patente = camionSeleccionado.getPatenteCamion();
        municipalidad = camionSeleccionado.getNombreMunicipalidad().getNombreMunicipalidad();
        nombreTipo = camionSeleccionado.getNombreTipoCamion().getNombreTipoCamion();
    }
    
    public void resetCampos(){
        this.patente = null;
        this.municipalidad = null;
        this.nombreTipo = null;
    }
}
