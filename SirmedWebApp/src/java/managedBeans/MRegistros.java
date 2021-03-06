/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Camion;
import entities.Chofer;
import entities.Municipalidad;
import entities.PuntoRecoleccion;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import sessionBeans.CrudCamionLocal;
import sessionBeans.CrudChoferLocal;
import sessionBeans.CrudRecoleccionLocal;
import sessionBeans.MunicipalidadesLocal;
import sessionBeans.RegistrosLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mRegistros")
@SessionScoped
public class MRegistros implements Serializable{
    
    @EJB
    private RegistrosLocal registros;
    @EJB
    private CrudRecoleccionLocal crudRecoleccion;
    @EJB
    private CrudCamionLocal crudCamion;
    @EJB
    private MunicipalidadesLocal municipalidades;
    @EJB
    private CrudChoferLocal crudChofer;
    @Inject
    private MAccionesGenerales ag;
    @Inject
    private MMessaegeController mc;
    
    private String rutChofer;
    private String rutBasculista;
    private String municipalidad;
    private String patenteCamion;
    private float pesajeCamion;
    private String comentarioRegistro;
    private String detalleRegistro;
    private Collection<Chofer> choferes;
    private Collection<Municipalidad> listaMunicipalidades;
    private Collection<Camion> camiones;
    private Collection<PuntoRecoleccion> puntosRecoleccion;
    private Collection<PuntoRecoleccion> puntosRecoleccionSeleccionados;
    
    
    @PostConstruct
   public void init(){
       
      
       rutBasculista = ag.devolverUsername();
       listaMunicipalidades = municipalidades.listaMunicipalidades();    
   }

    public Collection<PuntoRecoleccion> getPuntosRecoleccionSeleccionados() {
        return puntosRecoleccionSeleccionados;
    }

    public void setPuntosRecoleccionSeleccionados(Collection<PuntoRecoleccion> puntosRecoleccionSeleccionados) {
        this.puntosRecoleccionSeleccionados = puntosRecoleccionSeleccionados;
    }

    public Collection<Chofer> getChoferes() {
        return choferes;
    }

    public void setChoferes(Collection<Chofer> choferes) {
        this.choferes = choferes;
    }

    public Collection<Municipalidad> getListaMunicipalidades() {
        return listaMunicipalidades;
    }

    public void setListaMunicipalidades(Collection<Municipalidad> listaMunicipalidades) {
        this.listaMunicipalidades = listaMunicipalidades;
    }

    public Collection<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(Collection<Camion> Camiones) {
        this.camiones = Camiones;
    }

    public Collection<PuntoRecoleccion> getPuntosRecoleccion() {
        return puntosRecoleccion;
    }

    public void setPuntosRecoleccion(Collection<PuntoRecoleccion> puntosRecoleccion) {
        this.puntosRecoleccion = puntosRecoleccion;
    }
   
    public String getRutChofer() {
        return rutChofer;
    }

    public void setRutChofer(String rutChofer) {
        this.rutChofer = rutChofer;
    }

    public String getRutBasculista() {
        return rutBasculista;
    }

    public void setRutBasculista(String rutBasculista) {
        this.rutBasculista = rutBasculista;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public String getPatenteCamion() {
        return patenteCamion;
    }

    public void setPatenteCamion(String patenteCamion) {
        this.patenteCamion = patenteCamion;
    }

    public float getPesajeCamion() {
        return pesajeCamion;
    }

    public void setPesajeCamion(float pesajeCamion) {
        this.pesajeCamion = pesajeCamion;
    }

    public String getComentarioRegistro() {
        return comentarioRegistro;
    }

    public void setComentarioRegistro(String comentarioRegistro) {
        this.comentarioRegistro = comentarioRegistro;
    }

    public String getDetalleRegistro() {
        return detalleRegistro;
    }

    public void setDetalleRegistro(String detalleRegistro) {
        this.detalleRegistro = detalleRegistro;
    }
       
    public MRegistros() {
    }
    
    public void cargarDatos(){     
        camiones = crudCamion.listarCamionMunicipalidad(municipalidad);
        choferes = crudChofer.listarChoferMunicipalidad(municipalidad);
        puntosRecoleccion = crudRecoleccion.listarPuntoRecoleccionMunicipalidad(municipalidad);      
    }
    
    public void crearRegistro(){
        
            try{
                if(!puntosRecoleccionSeleccionados.isEmpty()){
                registros.crearRegistro(rutBasculista, municipalidad, patenteCamion, rutChofer, pesajeCamion, comentarioRegistro, puntosRecoleccionSeleccionados);             
                resetCampos();
                mc.mensajeRetroalimentacion("Operación exitosa", "Registro ingresado");
                ag.actualizarPagina();
                }
                else{
                    mc.mensajeRetroalimentacion("Error", "Seleccione puntos de recolección");
                }
            }
            catch(Exception e){
                mc.mensajeRetroalimentacion("Error", e.getMessage());
            }
    }
    
    public void resetCampos(){
        this.puntosRecoleccionSeleccionados = null;
        this.camiones = null;
        this.choferes = null;
        this.comentarioRegistro = null;
        this.pesajeCamion = 0;
        this.municipalidad = null;
        
    }
}
