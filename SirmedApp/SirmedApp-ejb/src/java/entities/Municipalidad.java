/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Municipalidad.findByTelefonoMunicipalidad", query = "SELECT m FROM Municipalidad m WHERE m.telefonoMunicipalidad = :telefonoMunicipalidad"),
    @NamedQuery(name = "Municipalidad.findByDireccionMunicipalidad", query = "SELECT m FROM Municipalidad m WHERE m.direccionMunicipalidad = :direccionMunicipalidad")})
public class Municipalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_MUNICIPALIDAD")
    private String nombreMunicipalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TELEFONO_MUNICIPALIDAD")
    private String telefonoMunicipalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DIRECCION_MUNICIPALIDAD")
    private String direccionMunicipalidad;
    @JoinTable(name = "municipalidad_punto", joinColumns = {
        @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PUNTO", referencedColumnName = "ID_PUNTO")})
    @ManyToMany
    private Collection<PuntoRecoleccion> puntoRecoleccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreMunicipalidad")
    private Collection<Chofer> choferCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreMunicipalidad")
    private Collection<Camion> camionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreMunicipalidad")
    private Collection<Registro> registroCollection;

    public Municipalidad() {
    }

    public Municipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public Municipalidad(String nombreMunicipalidad, String telefonoMunicipalidad, String direccionMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
        this.telefonoMunicipalidad = telefonoMunicipalidad;
        this.direccionMunicipalidad = direccionMunicipalidad;
    }

    public String getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public String getTelefonoMunicipalidad() {
        return telefonoMunicipalidad;
    }

    public void setTelefonoMunicipalidad(String telefonoMunicipalidad) {
        this.telefonoMunicipalidad = telefonoMunicipalidad;
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
    public Collection<Camion> getCamionCollection() {
        return camionCollection;
    }

    public void setCamionCollection(Collection<Camion> camionCollection) {
        this.camionCollection = camionCollection;
    }

    @XmlTransient
    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
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
