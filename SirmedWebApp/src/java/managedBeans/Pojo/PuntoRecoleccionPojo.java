/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.Pojo;

/**
 *
 * @author Aldo
 */

public class PuntoRecoleccionPojo{
    
    private Integer idTipo;
    private String nombrePunto ;
    private String direccionPunto;
    private String municipalidad;
    private String descripcion;
    private String tiposRecoleccion;

    public PuntoRecoleccionPojo(){}
    
    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getDireccionPunto() {
        return direccionPunto;
    }

    public void setDireccionPunto(String direccionPunto) {
        this.direccionPunto = direccionPunto;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiposRecoleccion() {
        return tiposRecoleccion;
    }

    public void setTiposRecoleccion(String tiposRecoleccion) {
        this.tiposRecoleccion = tiposRecoleccion;
    }
    
    
    
}
