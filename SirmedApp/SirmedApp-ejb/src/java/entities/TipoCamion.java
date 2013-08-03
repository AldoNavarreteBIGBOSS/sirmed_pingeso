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
    @NamedQuery(name = "TipoCamion.findByIdTc", query = "SELECT t FROM TipoCamion t WHERE t.idTc = :idTc"),
    @NamedQuery(name = "TipoCamion.findByNombreTc", query = "SELECT t FROM TipoCamion t WHERE t.nombreTc = :nombreTc"),
    @NamedQuery(name = "TipoCamion.findByDescripcionTc", query = "SELECT t FROM TipoCamion t WHERE t.descripcionTc = :descripcionTc")})
public class TipoCamion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TC")
    private Integer idTc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_TC")
    private String nombreTc;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_TC")
    private String descripcionTc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTc")
    private Collection<TipoRecoleccion> tipoRecoleccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTc")
    private Collection<Camion> camionCollection;

    public TipoCamion() {
    }

    public TipoCamion(Integer idTc) {
        this.idTc = idTc;
    }

    public TipoCamion(Integer idTc, String nombreTc) {
        this.idTc = idTc;
        this.nombreTc = nombreTc;
    }

    public Integer getIdTc() {
        return idTc;
    }

    public void setIdTc(Integer idTc) {
        this.idTc = idTc;
    }

    public String getNombreTc() {
        return nombreTc;
    }

    public void setNombreTc(String nombreTc) {
        this.nombreTc = nombreTc;
    }

    public String getDescripcionTc() {
        return descripcionTc;
    }

    public void setDescripcionTc(String descripcionTc) {
        this.descripcionTc = descripcionTc;
    }

    @XmlTransient
    public Collection<TipoRecoleccion> getTipoRecoleccionCollection() {
        return tipoRecoleccionCollection;
    }

    public void setTipoRecoleccionCollection(Collection<TipoRecoleccion> tipoRecoleccionCollection) {
        this.tipoRecoleccionCollection = tipoRecoleccionCollection;
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
        hash += (idTc != null ? idTc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCamion)) {
            return false;
        }
        TipoCamion other = (TipoCamion) object;
        if ((this.idTc == null && other.idTc != null) || (this.idTc != null && !this.idTc.equals(other.idTc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoCamion[ idTc=" + idTc + " ]";
    }
    
}
