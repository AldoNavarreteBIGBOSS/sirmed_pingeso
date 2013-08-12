/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "punto_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntoRecoleccion.findAll", query = "SELECT p FROM PuntoRecoleccion p"),
    @NamedQuery(name = "PuntoRecoleccion.findByIdPunto", query = "SELECT p FROM PuntoRecoleccion p WHERE p.idPunto = :idPunto"),
    @NamedQuery(name = "PuntoRecoleccion.findByNombrePunto", query = "SELECT p FROM PuntoRecoleccion p WHERE p.nombrePunto = :nombrePunto"),
    @NamedQuery(name = "PuntoRecoleccion.findByDireccionPunto", query = "SELECT p FROM PuntoRecoleccion p WHERE p.direccionPunto = :direccionPunto"),
    @NamedQuery(name = "PuntoRecoleccion.findByDescripcionPunto", query = "SELECT p FROM PuntoRecoleccion p WHERE p.descripcionPunto = :descripcionPunto"),
    @NamedQuery(name = "PuntoRecoleccion.finByDireccionLike", query = "SELECT p FROM PuntoRecoleccion p WHERE p.direccionPunto LIKE :direccionPunto ")})
public class PuntoRecoleccion implements Serializable {
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne(optional = false)
    private Municipalidad nombreMunicipalidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PUNTO")
    private Integer idPunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_PUNTO")
    private String nombrePunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DIRECCION_PUNTO")
    private String direccionPunto;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_PUNTO")
    private String descripcionPunto;
    @ManyToMany(mappedBy = "puntoRecoleccionCollection")
    private Collection<TipoRecoleccion> tipoRecoleccionCollection;
    

    public PuntoRecoleccion() {
    }

    public PuntoRecoleccion(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public PuntoRecoleccion(Integer idPunto, String nombrePunto, String direccionPunto) {
        this.idPunto = idPunto;
        this.nombrePunto = nombrePunto;
        this.direccionPunto = direccionPunto;
    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
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

    public String getDescripcionPunto() {
        return descripcionPunto;
    }

    public void setDescripcionPunto(String descripcionPunto) {
        this.descripcionPunto = descripcionPunto;
    }

    @XmlTransient
    public Collection<TipoRecoleccion> getTipoRecoleccionCollection() {
        return tipoRecoleccionCollection;
    }

    public void setTipoRecoleccionCollection(Collection<TipoRecoleccion> tipoRecoleccionCollection) {
        this.tipoRecoleccionCollection = tipoRecoleccionCollection;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPunto != null ? idPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoRecoleccion)) {
            return false;
        }
        PuntoRecoleccion other = (PuntoRecoleccion) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PuntoRecoleccion[ idPunto=" + idPunto + " ]";
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }
    
}
