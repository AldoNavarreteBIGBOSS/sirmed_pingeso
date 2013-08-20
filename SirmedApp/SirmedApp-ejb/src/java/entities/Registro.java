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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findByIdRegistro", query = "SELECT r FROM Registro r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "Registro.findByPesajeRegistro", query = "SELECT r FROM Registro r WHERE r.pesajeRegistro = :pesajeRegistro"),
    @NamedQuery(name = "Registro.findByFechaRegistro", query = "SELECT r FROM Registro r WHERE r.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Registro.findByFechaRegistroLike", query = "SELECT r FROM Registro r WHERE SUBSTRING(r.fechaRegistro, 1, 10) = :fecha"),
    @NamedQuery(name = "Registro.findByFechaRegistroBetween", query = "SELECT r FROM Registro r WHERE SUBSTRING(r.fechaRegistro, 1, 10) BETWEEN :fechaInicio AND :fechaFin ORDER BY r.fechaRegistro"),
    @NamedQuery(name = "Registro.findByFechaRegistroBetweenMuni", query = "SELECT r FROM Registro r WHERE r.nombreMunicipalidad.nombreMunicipalidad = :municipalidad AND SUBSTRING(r.fechaRegistro, 1, 10) BETWEEN :fechaInicio AND :fechaFin  ")
})
public class Registro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;
    @Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIO_REGISTRO")
    private String comentarioRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESAJE_REGISTRO")
    private float pesajeRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Size(max = 65535)
    @Column(name = "DETALLE_REGISTRO")
    private String detalleRegistro;
    @JoinColumn(name = "RUT_CHOFER", referencedColumnName = "RUT_CHOFER")
    @ManyToOne(optional = false)
    private Chofer rutChofer;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne(optional = false)
    private Municipalidad nombreMunicipalidad;
    @JoinColumn(name = "PATENTE", referencedColumnName = "PATENTE")
    @ManyToOne(optional = false)
    private Camion patente;
    @JoinColumn(name = "RUT", referencedColumnName = "RUT")
    @ManyToOne
    private Basculista rut;

    public Registro() {
    }

    public Registro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registro(Integer idRegistro, float pesajeRegistro, Date fechaRegistro) {
        this.idRegistro = idRegistro;
        this.pesajeRegistro = pesajeRegistro;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getComentarioRegistro() {
        return comentarioRegistro;
    }

    public void setComentarioRegistro(String comentarioRegistro) {
        this.comentarioRegistro = comentarioRegistro;
    }

    public float getPesajeRegistro() {
        return pesajeRegistro;
    }

    public void setPesajeRegistro(float pesajeRegistro) {
        this.pesajeRegistro = pesajeRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDetalleRegistro() {
        return detalleRegistro;
    }

    public void setDetalleRegistro(String detalleRegistro) {
        this.detalleRegistro = detalleRegistro;
    }

    public Chofer getRutChofer() {
        return rutChofer;
    }

    public void setRutChofer(Chofer rutChofer) {
        this.rutChofer = rutChofer;
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public Camion getPatente() {
        return patente;
    }

    public void setPatente(Camion patente) {
        this.patente = patente;
    }

    public Basculista getRut() {
        return rut;
    }

    public void setRut(Basculista rut) {
        this.rut = rut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Registro[ idRegistro=" + idRegistro + " ]";
    }

    public void setFechaRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
