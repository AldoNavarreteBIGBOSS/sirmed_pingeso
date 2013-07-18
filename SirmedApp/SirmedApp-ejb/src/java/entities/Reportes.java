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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r"),
    @NamedQuery(name = "Reportes.findByIdReporte", query = "SELECT r FROM Reportes r WHERE r.idReporte = :idReporte"),
    @NamedQuery(name = "Reportes.findByNombreReporte", query = "SELECT r FROM Reportes r WHERE r.nombreReporte = :nombreReporte"),
    @NamedQuery(name = "Reportes.findByFechaReporte", query = "SELECT r FROM Reportes r WHERE r.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "Reportes.findByFechaInicio", query = "SELECT r FROM Reportes r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Reportes.findByFechaTermino", query = "SELECT r FROM Reportes r WHERE r.fechaTermino = :fechaTermino")})
public class Reportes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REPORTE")
    private Integer idReporte;
    @Size(max = 80)
    @Column(name = "NOMBRE_REPORTE")
    private String nombreReporte;
    @Column(name = "FECHA_REPORTE")
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.DATE)
    private Date fechaTermino;
    @JoinColumn(name = "RUT", referencedColumnName = "RUT")
    @ManyToOne
    private JefePlanta rut;

    public Reportes() {
    }

    public Reportes(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public JefePlanta getRut() {
        return rut;
    }

    public void setRut(JefePlanta rut) {
        this.rut = rut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reportes[ idReporte=" + idReporte + " ]";
    }
    
}
