/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
@Table(name = "tipo_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRecoleccion.findAll", query = "SELECT t FROM TipoRecoleccion t"),
    @NamedQuery(name = "TipoRecoleccion.findByIdTipoRecoleccion", query = "SELECT t FROM TipoRecoleccion t WHERE t.idTipoRecoleccion = :idTipoRecoleccion"),
    @NamedQuery(name = "TipoRecoleccion.findByNombreTipoRecoleccion", query = "SELECT t FROM TipoRecoleccion t WHERE t.nombreTipoRecoleccion = :nombreTipoRecoleccion")})
public class TipoRecoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_RECOLECCION")
    private Integer idTipoRecoleccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_TIPO_RECOLECCION")
    private String nombreTipoRecoleccion;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION_TIPO_RECOLECCION")
    private String descripcionTipoRecoleccion;
    @JoinTable(name = "punto_tipo", joinColumns = {
        @JoinColumn(name = "ID_TIPO_RECOLECCION", referencedColumnName = "ID_TIPO_RECOLECCION")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PUNTO", referencedColumnName = "ID_PUNTO")})
    @ManyToMany
    private Collection<PuntoRecoleccion> puntoRecoleccionCollection;
    @JoinColumn(name = "ID_TC", referencedColumnName = "ID_TC")
    @ManyToOne(optional = false)
    private TipoCamion idTc;

    public TipoRecoleccion() {
        this.puntoRecoleccionCollection = new LinkedList();
    }

    public TipoRecoleccion(Integer idTipoRecoleccion) {
        this.idTipoRecoleccion = idTipoRecoleccion;
    }

    public TipoRecoleccion(Integer idTipoRecoleccion, String nombreTipoRecoleccion) {
        this.idTipoRecoleccion = idTipoRecoleccion;
        this.nombreTipoRecoleccion = nombreTipoRecoleccion;
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

    public String getDescripcionTipoRecoleccion() {
        return descripcionTipoRecoleccion;
    }

    public void setDescripcionTipoRecoleccion(String descripcionTipoRecoleccion) {
        this.descripcionTipoRecoleccion = descripcionTipoRecoleccion;
    }

    @XmlTransient
    public Collection<PuntoRecoleccion> getPuntoRecoleccionCollection() {
        return puntoRecoleccionCollection;
    }

    public void setPuntoRecoleccionCollection(Collection<PuntoRecoleccion> puntoRecoleccionCollection) {
        this.puntoRecoleccionCollection = puntoRecoleccionCollection;
    }

    public TipoCamion getIdTc() {
        return idTc;
    }

    public void setIdTc(TipoCamion idTc) {
        this.idTc = idTc;
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
