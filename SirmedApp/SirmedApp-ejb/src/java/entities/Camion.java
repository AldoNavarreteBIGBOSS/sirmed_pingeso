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
import javax.persistence.ManyToOne;
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
@Table(name = "camion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findByPatente", query = "SELECT c FROM Camion c WHERE c.patente = :patente")})
public class Camion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PATENTE")
    private String patente;
    @JoinColumn(name = "ID_TC", referencedColumnName = "ID_TC")
    @ManyToOne(optional = false)
    private TipoCamion idTc;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne(optional = false)
    private Municipalidad nombreMunicipalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patente")
    private Collection<Registro> registroCollection;

    public Camion() {
    }

    public Camion(String patente) {
        this.patente = patente;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public TipoCamion getIdTc() {
        return idTc;
    }

    public void setIdTc(TipoCamion idTc) {
        this.idTc = idTc;
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
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
        hash += (patente != null ? patente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.patente == null && other.patente != null) || (this.patente != null && !this.patente.equals(other.patente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Camion[ patente=" + patente + " ]";
    }
    
}
