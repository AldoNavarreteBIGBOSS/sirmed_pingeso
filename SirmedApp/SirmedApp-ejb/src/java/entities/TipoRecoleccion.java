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
@Table(name = "tipo_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRecoleccion.findAll", query = "SELECT t FROM TipoRecoleccion t"),
    @NamedQuery(name = "TipoRecoleccion.findByNombreTipoRecoleccion", query = "SELECT t FROM TipoRecoleccion t WHERE t.nombreTipoRecoleccion = :nombreTipoRecoleccion")})
public class TipoRecoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE_TIPO_RECOLECCION")
    private String nombreTipoRecoleccion;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRP_TIPO_RECOLECCION")
    private String descrpTipoRecoleccion;
    @OneToMany(mappedBy = "nombreTipoRecoleccion")
    private Collection<PuntoRecoleccion> puntoRecoleccionCollection;

    public TipoRecoleccion() {
    }

    public TipoRecoleccion(String nombreTipoRecoleccion) {
        this.nombreTipoRecoleccion = nombreTipoRecoleccion;
    }

    public String getNombreTipoRecoleccion() {
        return nombreTipoRecoleccion;
    }

    public void setNombreTipoRecoleccion(String nombreTipoRecoleccion) {
        this.nombreTipoRecoleccion = nombreTipoRecoleccion;
    }

    public String getDescrpTipoRecoleccion() {
        return descrpTipoRecoleccion;
    }

    public void setDescrpTipoRecoleccion(String descrpTipoRecoleccion) {
        this.descrpTipoRecoleccion = descrpTipoRecoleccion;
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
        hash += (nombreTipoRecoleccion != null ? nombreTipoRecoleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecoleccion)) {
            return false;
        }
        TipoRecoleccion other = (TipoRecoleccion) object;
        if ((this.nombreTipoRecoleccion == null && other.nombreTipoRecoleccion != null) || (this.nombreTipoRecoleccion != null && !this.nombreTipoRecoleccion.equals(other.nombreTipoRecoleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoRecoleccion[ nombreTipoRecoleccion=" + nombreTipoRecoleccion + " ]";
    }
    
}
