/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a"),
    @NamedQuery(name = "Auditoria.findByIdAuditoria", query = "SELECT a FROM Auditoria a WHERE a.idAuditoria = :idAuditoria"),
    @NamedQuery(name = "Auditoria.findByFechaAuditoria", query = "SELECT a FROM Auditoria a WHERE a.fechaAuditoria = :fechaAuditoria"),
    @NamedQuery(name = "Auditoria.findByDescripcionAuditoria", query = "SELECT a FROM Auditoria a WHERE a.descripcionAuditoria = :descripcionAuditoria"),
    @NamedQuery(name = "Auditoria.findByInfoAuditoria", query = "SELECT a FROM Auditoria a WHERE a.infoAuditoria = :infoAuditoria")})
public class Auditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_AUDITORIA")
    private Integer idAuditoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_AUDITORIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAuditoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION_AUDITORIA")
    private String descripcionAuditoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "INFO_AUDITORIA")
    private String infoAuditoria;

    public Auditoria() {
    }

    public Auditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Auditoria(Integer idAuditoria, Date fechaAuditoria, String descripcionAuditoria, String infoAuditoria) {
        this.idAuditoria = idAuditoria;
        this.fechaAuditoria = fechaAuditoria;
        this.descripcionAuditoria = descripcionAuditoria;
        this.infoAuditoria = infoAuditoria;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getDescripcionAuditoria() {
        return descripcionAuditoria;
    }

    public void setDescripcionAuditoria(String descripcionAuditoria) {
        this.descripcionAuditoria = descripcionAuditoria;
    }

    public String getInfoAuditoria() {
        return infoAuditoria;
    }

    public void setInfoAuditoria(String infoAuditoria) {
        this.infoAuditoria = infoAuditoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuditoria != null ? idAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.idAuditoria == null && other.idAuditoria != null) || (this.idAuditoria != null && !this.idAuditoria.equals(other.idAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Auditoria[ idAuditoria=" + idAuditoria + " ]";
    }
    
}
