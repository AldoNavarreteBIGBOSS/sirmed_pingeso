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
import javax.persistence.Lob;
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
@Table(name = "tipo_camion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCamion.findAll", query = "SELECT t FROM TipoCamion t"),
    @NamedQuery(name = "TipoCamion.findByNombreTipoCamion", query = "SELECT t FROM TipoCamion t WHERE t.nombreTipoCamion = :nombreTipoCamion")})
public class TipoCamion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_TIPO_CAMION")
    private String nombreTipoCamion;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRP_CAMION")
    private String descrpCamion;
    @OneToMany(mappedBy = "nombreTipoCamion")
    private Collection<Camion> camionCollection;

    public TipoCamion() {
    }

    public TipoCamion(String nombreTipoCamion) {
        this.nombreTipoCamion = nombreTipoCamion;
    }

    public String getNombreTipoCamion() {
        return nombreTipoCamion;
    }

    public void setNombreTipoCamion(String nombreTipoCamion) {
        this.nombreTipoCamion = nombreTipoCamion;
    }

    public String getDescrpCamion() {
        return descrpCamion;
    }

    public void setDescrpCamion(String descrpCamion) {
        this.descrpCamion = descrpCamion;
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
        hash += (nombreTipoCamion != null ? nombreTipoCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCamion)) {
            return false;
        }
        TipoCamion other = (TipoCamion) object;
        if ((this.nombreTipoCamion == null && other.nombreTipoCamion != null) || (this.nombreTipoCamion != null && !this.nombreTipoCamion.equals(other.nombreTipoCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoCamion[ nombreTipoCamion=" + nombreTipoCamion + " ]";
    }
    
}
