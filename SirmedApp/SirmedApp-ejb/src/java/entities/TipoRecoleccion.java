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
@Table(name = "tipo_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRecoleccion.findAll", query = "SELECT t FROM TipoRecoleccion t"),
    @NamedQuery(name = "TipoRecoleccion.findByIdTipoRecoleccion", query = "SELECT t FROM TipoRecoleccion t WHERE t.idTipoRecoleccion = :idTipoRecoleccion"),
    @NamedQuery(name = "TipoRecoleccion.findByNombreTipoRecoleccion", query = "SELECT t FROM TipoRecoleccion t WHERE t.nombreTipoRecoleccion = :nombreTipoRecoleccion")})
public class TipoRecoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_RECOLECCION")
    private Integer idTipoRecoleccion;
    @Size(max = 80)
    @Column(name = "NOMBRE_TIPO_RECOLECCION")
    private String nombreTipoRecoleccion;
    @OneToMany(mappedBy = "idTipoRecoleccion")
    private Collection<PuntoRecoleccion> puntoRecoleccionCollection;

    public TipoRecoleccion() {
    }

    public TipoRecoleccion(Integer idTipoRecoleccion) {
        this.idTipoRecoleccion = idTipoRecoleccion;
    }

    public Integer getIdTipoRecoleccion() {
        return idTipoRecoleccion;
    }

    public void setIdTipoRecoleccion(Integer idTipoRecoleccion) {
        this.idTipoRecoleccion = idTipoRecoleccion;
    }

    public String getNombreTipoRecoleccion() {
        return nombreTipoRecoleccion;
    }

    public void setNombreTipoRecoleccion(String nombreTipoRecoleccion) {
        this.nombreTipoRecoleccion = nombreTipoRecoleccion;
    }

    @XmlTransient
    public Collection<PuntoRecoleccion> getPuntoRecoleccionCollection() {
        return puntoRecoleccionCollection;
    }

    public void setPuntoRecoleccionCollection(Collection<PuntoRecoleccion> puntoRecoleccionCollection) {
        this.puntoRecoleccionCollection = puntoRecoleccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRecoleccion != null ? idTipoRecoleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecoleccion)) {
            return false;
        }
        TipoRecoleccion other = (TipoRecoleccion) object;
        if ((this.idTipoRecoleccion == null && other.idTipoRecoleccion != null) || (this.idTipoRecoleccion != null && !this.idTipoRecoleccion.equals(other.idTipoRecoleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoRecoleccion[ idTipoRecoleccion=" + idTipoRecoleccion + " ]";
    }
    
}
