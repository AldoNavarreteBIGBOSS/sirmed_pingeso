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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "municipalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipalidad.findAll", query = "SELECT m FROM Municipalidad m"),
    @NamedQuery(name = "Municipalidad.findByNombreMunicipalidad", query = "SELECT m FROM Municipalidad m WHERE m.nombreMunicipalidad = :nombreMunicipalidad"),
    @NamedQuery(name = "Municipalidad.findByTelefonpMunicipalidad", query = "SELECT m FROM Municipalidad m WHERE m.telefonpMunicipalidad = :telefonpMunicipalidad"),
    @NamedQuery(name = "Municipalidad.findByDireccionMunicipalidad", query = "SELECT m FROM Municipalidad m WHERE m.direccionMunicipalidad = :direccionMunicipalidad")})
public class Municipalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_MUNICIPALIDAD")
    private String nombreMunicipalidad;
    @Size(max = 40)
    @Column(name = "TELEFONP_MUNICIPALIDAD")
    private String telefonpMunicipalidad;
    @Size(max = 40)
    @Column(name = "DIRECCION_MUNICIPALIDAD")
    private String direccionMunicipalidad;
    @OneToMany(mappedBy = "nombreMunicipalidad")
    private Collection<PuntoRecoleccion> puntoRecoleccionCollection;
    @OneToMany(mappedBy = "nombreMunicipalidad")
    private Collection<Chofer> choferCollection;
    @OneToMany(mappedBy = "nombreMunicipalidad")
    private Collection<Registros> registrosCollection;
    @OneToMany(mappedBy = "nombreMunicipalidad")
    private Collection<Camion> camionCollection;

    public Municipalidad() {
    }

    public Municipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public String getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public String getTelefonpMunicipalidad() {
        return telefonpMunicipalidad;
    }

    public void setTelefonpMunicipalidad(String telefonpMunicipalidad) {
        this.telefonpMunicipalidad = telefonpMunicipalidad;
    }

    public String getDireccionMunicipalidad() {
        return direccionMunicipalidad;
    }

    public void setDireccionMunicipalidad(String direccionMunicipalidad) {
        this.direccionMunicipalidad = direccionMunicipalidad;
    }

    @XmlTransient
    public Collection<PuntoRecoleccion> getPuntoRecoleccionCollection() {
        return puntoRecoleccionCollection;
    }

    public void setPuntoRecoleccionCollection(Collection<PuntoRecoleccion> puntoRecoleccionCollection) {
        this.puntoRecoleccionCollection = puntoRecoleccionCollection;
    }

    @XmlTransient
    public Collection<Chofer> getChoferCollection() {
        return choferCollection;
    }

    public void setChoferCollection(Collection<Chofer> choferCollection) {
        this.choferCollection = choferCollection;
    }

    @XmlTransient
    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
    }

    @XmlTransient
    public Collection<Camion> getCamionCollection() {
        return camionCollection;
    }

    public void setCamionCollection(Collection<Camion> camionCollection) {
        this.camionCollection = camionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreMunicipalidad != null ? nombreMunicipalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipalidad)) {
            return false;
        }
        Municipalidad other = (Municipalidad) object;
        if ((this.nombreMunicipalidad == null && other.nombreMunicipalidad != null) || (this.nombreMunicipalidad != null && !this.nombreMunicipalidad.equals(other.nombreMunicipalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Municipalidad[ nombreMunicipalidad=" + nombreMunicipalidad + " ]";
    }
    
}
