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
    @NamedQuery(name = "Camion.findByPatenteCamion", query = "SELECT c FROM Camion c WHERE c.patenteCamion = :patenteCamion")})
public class Camion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PATENTE_CAMION")
    private String patenteCamion;
    @OneToMany(mappedBy = "patenteCamion")
    private Collection<Registros> registrosCollection;
    @JoinColumn(name = "NOMBRE_TIPO_CAMION", referencedColumnName = "NOMBRE_TIPO_CAMION")
    @ManyToOne
    private TipoCamion nombreTipoCamion;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne
    private Municipalidad nombreMunicipalidad;

    public Camion() {
    }

    public Camion(String patenteCamion) {
        this.patenteCamion = patenteCamion;
    }

    public String getPatenteCamion() {
        return patenteCamion;
    }

    public void setPatenteCamion(String patenteCamion) {
        this.patenteCamion = patenteCamion;
    }

    @XmlTransient
    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
    }

    public TipoCamion getNombreTipoCamion() {
        return nombreTipoCamion;
    }

    public void setNombreTipoCamion(TipoCamion nombreTipoCamion) {
        this.nombreTipoCamion = nombreTipoCamion;
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
        hash += (patenteCamion != null ? patenteCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.patenteCamion == null && other.patenteCamion != null) || (this.patenteCamion != null && !this.patenteCamion.equals(other.patenteCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Camion[ patenteCamion=" + patenteCamion + " ]";
    }
    
}
