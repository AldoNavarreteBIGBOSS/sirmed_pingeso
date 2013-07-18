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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "chofer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chofer.findAll", query = "SELECT c FROM Chofer c"),
    @NamedQuery(name = "Chofer.findByRutChofer", query = "SELECT c FROM Chofer c WHERE c.rutChofer = :rutChofer"),
    @NamedQuery(name = "Chofer.findByNombreChofer", query = "SELECT c FROM Chofer c WHERE c.nombreChofer = :nombreChofer"),
    @NamedQuery(name = "Chofer.findByApellidoChofer", query = "SELECT c FROM Chofer c WHERE c.apellidoChofer = :apellidoChofer"),
    @NamedQuery(name = "Chofer.findByTelefonoChofer", query = "SELECT c FROM Chofer c WHERE c.telefonoChofer = :telefonoChofer"),
    @NamedQuery(name = "Chofer.findByEmailChofer", query = "SELECT c FROM Chofer c WHERE c.emailChofer = :emailChofer")})
public class Chofer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_CHOFER")
    private String rutChofer;
    @Size(max = 80)
    @Column(name = "NOMBRE_CHOFER")
    private String nombreChofer;
    @Size(max = 80)
    @Column(name = "APELLIDO_CHOFER")
    private String apellidoChofer;
    @Size(max = 15)
    @Column(name = "TELEFONO_CHOFER")
    private String telefonoChofer;
    @Size(max = 100)
    @Column(name = "EMAIL_CHOFER")
    private String emailChofer;
    @JoinColumn(name = "NOMBRE_MUNICIPALIDAD", referencedColumnName = "NOMBRE_MUNICIPALIDAD")
    @ManyToOne
    private Municipalidad nombreMunicipalidad;
    @OneToMany(mappedBy = "rutChofer")
    private Collection<Registros> registrosCollection;

    public Chofer() {
    }

    public Chofer(String rutChofer) {
        this.rutChofer = rutChofer;
    }

    public String getRutChofer() {
        return rutChofer;
    }

    public void setRutChofer(String rutChofer) {
        this.rutChofer = rutChofer;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getApellidoChofer() {
        return apellidoChofer;
    }

    public void setApellidoChofer(String apellidoChofer) {
        this.apellidoChofer = apellidoChofer;
    }

    public String getTelefonoChofer() {
        return telefonoChofer;
    }

    public void setTelefonoChofer(String telefonoChofer) {
        this.telefonoChofer = telefonoChofer;
    }

    public String getEmailChofer() {
        return emailChofer;
    }

    public void setEmailChofer(String emailChofer) {
        this.emailChofer = emailChofer;
    }

    public Municipalidad getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(Municipalidad nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    @XmlTransient
    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutChofer != null ? rutChofer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chofer)) {
            return false;
        }
        Chofer other = (Chofer) object;
        if ((this.rutChofer == null && other.rutChofer != null) || (this.rutChofer != null && !this.rutChofer.equals(other.rutChofer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Chofer[ rutChofer=" + rutChofer + " ]";
    }
    
}
