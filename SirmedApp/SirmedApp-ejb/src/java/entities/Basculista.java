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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "basculista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Basculista.findAll", query = "SELECT b FROM Basculista b"),
    @NamedQuery(name = "Basculista.findByNombreB", query = "SELECT b FROM Basculista b WHERE b.nombreB = :nombreB"),
    @NamedQuery(name = "Basculista.findByApellidoB", query = "SELECT b FROM Basculista b WHERE b.apellidoB = :apellidoB"),
    @NamedQuery(name = "Basculista.findByTelefonoB", query = "SELECT b FROM Basculista b WHERE b.telefonoB = :telefonoB"),
    @NamedQuery(name = "Basculista.findByRut", query = "SELECT b FROM Basculista b WHERE b.rut = :rut")})
public class Basculista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBRE_B")
    private String nombreB;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "APELLIDO_B")
    private String apellidoB;
    @Size(max = 20)
    @Column(name = "TELEFONO_B")
    private String telefonoB;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "RUT")
    private String rut;
    @JoinColumn(name = "RUT", referencedColumnName = "RUT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "rut")
    private Collection<Registro> registroCollection;

    public Basculista() {
    }

    public Basculista(String rut) {
        this.rut = rut;
    }

    public Basculista(String rut, String nombreB, String apellidoB) {
        this.rut = rut;
        this.nombreB = nombreB;
        this.apellidoB = apellidoB;
    }

    public String getNombreB() {
        return nombreB;
    }

    public void setNombreB(String nombreB) {
        this.nombreB = nombreB;
    }

    public String getApellidoB() {
        return apellidoB;
    }

    public void setApellidoB(String apellidoB) {
        this.apellidoB = apellidoB;
    }

    public String getTelefonoB() {
        return telefonoB;
    }

    public void setTelefonoB(String telefonoB) {
        this.telefonoB = telefonoB;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut != null ? rut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Basculista)) {
            return false;
        }
        Basculista other = (Basculista) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Basculista[ rut=" + rut + " ]";
    }
    
}
