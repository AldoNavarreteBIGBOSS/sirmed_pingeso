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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "tipo_camion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCamion.findAll", query = "SELECT t FROM TipoCamion t"),
    @NamedQuery(name = "TipoCamion.findByIdTipoCamion", query = "SELECT t FROM TipoCamion t WHERE t.idTipoCamion = :idTipoCamion"),
    @NamedQuery(name = "TipoCamion.findByNombreTipoCamion", query = "SELECT t FROM TipoCamion t WHERE t.nombreTipoCamion = :nombreTipoCamion")})
public class TipoCamion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_CAMION")
    private Integer idTipoCamion;
    @Size(max = 40)
    @Column(name = "NOMBRE_TIPO_CAMION")
    private String nombreTipoCamion;
    @OneToMany(mappedBy = "idTipoCamion")
    private Collection<Camion> camionCollection;

    public TipoCamion() {
    }

    public TipoCamion(Integer idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }

    public Integer getIdTipoCamion() {
        return idTipoCamion;
    }

    public void setIdTipoCamion(Integer idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }

    public String getNombreTipoCamion() {
        return nombreTipoCamion;
    }

    public void setNombreTipoCamion(String nombreTipoCamion) {
        this.nombreTipoCamion = nombreTipoCamion;
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
        hash += (idTipoCamion != null ? idTipoCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCamion)) {
            return false;
        }
        TipoCamion other = (TipoCamion) object;
        if ((this.idTipoCamion == null && other.idTipoCamion != null) || (this.idTipoCamion != null && !this.idTipoCamion.equals(other.idTipoCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoCamion[ idTipoCamion=" + idTipoCamion + " ]";
    }
    
}
