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
@Table(name = "registros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registros.findAll", query = "SELECT r FROM Registros r"),
    @NamedQuery(name = "Registros.findByIdRegistro", query = "SELECT r FROM Registros r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "Registros.findByPesajeRegistro", query = "SELECT r FROM Registros r WHERE r.pesajeRegistro = :pesajeRegistro"),
    @NamedQuery(name = "Registros.findByFechaRegistro", query = "SELECT r FROM Registros r WHERE r.fechaRegistro = :fechaRegistro")})
public class Registros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;
    @Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIO_REGISTRO")
    private String comentarioRegistro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESAJE_REGISTRO")
    private Float pesajeRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Size(max = 65535)
    @Column(name = "DETALLE_REGISTRO")
    private String detalleRegistro;
    @JoinColumn(name = "RUT", referencedColumnName = "RUT")
    @ManyToOne
    private Basculista rut;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne
    private Municipalidad nombreMunicipalidad;
    @JoinColumn(name = "RUT_CHOFER", referencedColumnName = "RUT_CHOFER")
    @ManyToOne
    private Chofer rutChofer;
    @JoinColumn(name = "PATENTE_CAMION", referencedColumnName = "PATENTE_CAMION")
    @ManyToOne
    private Camion patenteCamion;

    public Registros() {
    }

    public Registros(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registros(Integer idRegistro, Date fechaRegistro) {
        this.idRegistro = idRegistro;
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

    public Float getPesajeRegistro() {
        return pesajeRegistro;
    }

    public void setPesajeRegistro(Float pesajeRegistro) {
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

    public Basculista getRut() {
        return rut;
    }

    public void setRut(Basculista rut) {
        this.rut = rut;
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public Chofer getRutChofer() {
        return rutChofer;
    }

    public void setRutChofer(Chofer rutChofer) {
        this.rutChofer = rutChofer;
    }

    public Camion getPatenteCamion() {
        return patenteCamion;
    }

    public void setPatenteCamion(Camion patenteCamion) {
        this.patenteCamion = patenteCamion;
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
        if (!(object instanceof Registros)) {
            return false;
        }
        Registros other = (Registros) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Registros[ idRegistro=" + idRegistro + " ]";
    }
    
}
