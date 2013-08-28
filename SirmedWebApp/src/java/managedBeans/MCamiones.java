/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Camion;
import entities.Municipalidad;
import entities.TipoCamion;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.CrudCamionLocal;
import sessionBeans.CrudTipoCamionLocal;
import sessionBeans.MunicipalidadesLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mCamiones")
@RequestScoped
public class MCamiones {
    @EJB
    private CrudTipoCamionLocal crudTipoCamion;
    @EJB
    private MunicipalidadesLocal municipalidades1;
    @EJB
    private CrudCamionLocal crudCamion;
    
    private String patente;
    private String municipalidad;
    private Integer TipoCamion;
    private Collection<Municipalidad> municipalidades;
    private Collection<Camion> camiones;
    private Collection<TipoCamion> tc;
    private Camion camionSeleccionado;
    private MMessaegeController mc;
    private MAccionesGenerales ag;
    private String patenteActivar;
    
    public MCamiones() {
    }
    
    @PostConstruct
    public void init(){
        
        municipalidades = municipalidades1.listaMunicipalidades();
        camiones = crudCamion.listaCamiones();
        tc = crudTipoCamion.listaTipoCamion();
        mc = new MMessaegeController(); 
        ag = new MAccionesGenerales();
    }

    public String getPatenteActivar() {
        return patenteActivar;
    }

    public void setPatenteActivar(String patenteActivar) {
        this.patenteActivar = patenteActivar;
    }

    
    
    public Collection<TipoCamion> getTc() {
        return tc;
    }

    public void setTc(Collection<TipoCamion> tc) {
        this.tc = tc;
    }

    
    
    public MunicipalidadesLocal getMunicipalidades1() {
        return municipalidades1;
    }

    public void setMunicipalidades1(MunicipalidadesLocal municipalidades1) {
        this.municipalidades1 = municipalidades1;
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

    public Integer getTipoCamion() {
        return TipoCamion;
    }

    public void setTipoCamion(Integer TipoCamion) {
        this.TipoCamion = TipoCamion;
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
            crudCamion.crearCamion(patente.toUpperCase(), municipalidad, TipoCamion);
            mc.mensajeRetroalimentacion( "Operación Existosa", "Camión "+patente.toUpperCase()+" ingresado");
            resetCampos();
        }
        catch(Exception e){
            mc.mensajeRetroalimentacion( "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public void actualizarCamion(){
        try {
            patente = camionSeleccionado.getPatente();
            crudCamion.editarCamion(patente.toUpperCase(), municipalidad, TipoCamion);
            resetCampos();
            ag.actualizarPagina();
        } catch (Exception e) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void borrarCamion(){
                   
        try {
            setearCamion();
            crudCamion.eliminarCamion(patente.toUpperCase());
            ag.actualizarPagina();
        } catch (Exception e) {
            Logger.getLogger(MBasculista.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void setearCamion(){
        patente = camionSeleccionado.getPatente();
        municipalidad = camionSeleccionado.getNombreMunicipalidad().getNombreMunicipalidad();
        TipoCamion = camionSeleccionado.getIdTc().getIdTc();
    }
    
    public void resetCampos(){
        this.patente = null;
        this.municipalidad = null;
        
    }
    
    public void activarCamion(){
        
        try{
            crudCamion.activarCamion(patenteActivar.toUpperCase());
            ag.actualizarPagina();
        }
        catch(Exception e){
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }
}
