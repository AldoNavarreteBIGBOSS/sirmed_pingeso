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
import javax.persistence.Lob;
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
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "Historial.findByFechaHistorial", query = "SELECT h FROM Historial h WHERE h.fechaHistorial = :fechaHistorial"),
    @NamedQuery(name = "Historial.findByHoraHistorial", query = "SELECT h FROM Historial h WHERE h.horaHistorial = :horaHistorial"),
    @NamedQuery(name = "Historial.findByInfoHistorial", query = "SELECT h FROM Historial h WHERE h.infoHistorial = :infoHistorial")})
public class Historial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HISTORIAL")
    private Integer idHistorial;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRP_HISTORIAL")
    private String descrpHistorial;
    @Column(name = "FECHA_HISTORIAL")
    @Temporal(TemporalType.DATE)
    private Date fechaHistorial;
    @Column(name = "HORA_HISTORIAL")
    @Temporal(TemporalType.TIME)
    private Date horaHistorial;
    @Size(max = 150)
    @Column(name = "INFO_HISTORIAL")
    private String infoHistorial;

    public Historial() {
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getDescrpHistorial() {
        return descrpHistorial;
    }

    public void setDescrpHistorial(String descrpHistorial) {
        this.descrpHistorial = descrpHistorial;
    }

    public Date getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(Date fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public Date getHoraHistorial() {
        return horaHistorial;
    }

    public void setHoraHistorial(Date horaHistorial) {
        this.horaHistorial = horaHistorial;
    }

    public String getInfoHistorial() {
        return infoHistorial;
    }

    public void setInfoHistorial(String infoHistorial) {
        this.infoHistorial = infoHistorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historial[ idHistorial=" + idHistorial + " ]";
    }
    
}
