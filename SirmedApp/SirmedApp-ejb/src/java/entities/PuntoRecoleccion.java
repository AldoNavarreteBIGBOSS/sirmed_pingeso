/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "punto_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntoRecoleccion.findAll", query = "SELECT p FROM PuntoRecoleccion p"),
    @NamedQuery(name = "PuntoRecoleccion.findByDireccionPunto", query = "SELECT p FROM PuntoRecoleccion p WHERE p.direccionPunto = :direccionPunto")})
public class PuntoRecoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "DIRECCION_PUNTO")
    private String direccionPunto;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRP_PUNTO")
    private String descrpPunto;
    @JoinColumn(name = "ID_TIPO_RECOLECCION", referencedColumnName = "ID_TIPO_RECOLECCION")
    @ManyToOne
    private TipoRecoleccion idTipoRecoleccion;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne
    private Municipalidad nombreMunicipalidad;

    public PuntoRecoleccion() {
    }

    public PuntoRecoleccion(String direccionPunto) {
        this.direccionPunto = direccionPunto;
    }

    public String getDireccionPunto() {
        return direccionPunto;
    }

    public void setDireccionPunto(String direccionPunto) {
        this.direccionPunto = direccionPunto;
    }

    public String getDescrpPunto() {
        return descrpPunto;
    }

    public void setDescrpPunto(String descrpPunto) {
        this.descrpPunto = descrpPunto;
    }

    public TipoRecoleccion getIdTipoRecoleccion() {
        return idTipoRecoleccion;
    }

    public void setIdTipoRecoleccion(TipoRecoleccion idTipoRecoleccion) {
        this.idTipoRecoleccion = idTipoRecoleccion;
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (direccionPunto != null ? direccionPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoRecoleccion)) {
            return false;
        }
        PuntoRecoleccion other = (PuntoRecoleccion) object;
        if ((this.direccionPunto == null && other.direccionPunto != null) || (this.direccionPunto != null && !this.direccionPunto.equals(other.direccionPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PuntoRecoleccion[ direccionPunto=" + direccionPunto + " ]";
    }
    
}
