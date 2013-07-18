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
@Table(name = "jefe_planta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JefePlanta.findAll", query = "SELECT j FROM JefePlanta j"),
    @NamedQuery(name = "JefePlanta.findByRut", query = "SELECT j FROM JefePlanta j WHERE j.rut = :rut"),
    @NamedQuery(name = "JefePlanta.findByNombreJp", query = "SELECT j FROM JefePlanta j WHERE j.nombreJp = :nombreJp"),
    @NamedQuery(name = "JefePlanta.findByApellidoJp", query = "SELECT j FROM JefePlanta j WHERE j.apellidoJp = :apellidoJp"),
    @NamedQuery(name = "JefePlanta.findByTelefonoJp", query = "SELECT j FROM JefePlanta j WHERE j.telefonoJp = :telefonoJp")})
public class JefePlanta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "RUT")
    private String rut;
    @Size(max = 80)
    @Column(name = "NOMBRE_JP")
    private String nombreJp;
    @Size(max = 80)
    @Column(name = "APELLIDO_JP")
    private String apellidoJp;
    @Size(max = 15)
    @Column(name = "TELEFONO_JP")
    private String telefonoJp;
    @JoinColumn(name = "RUT", referencedColumnName = "RUT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "rut")
    private Collection<Reportes> reportesCollection;

    public JefePlanta() {
    }

    public JefePlanta(String rut) {
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreJp() {
        return nombreJp;
    }

    public void setNombreJp(String nombreJp) {
        this.nombreJp = nombreJp;
    }

    public String getApellidoJp() {
        return apellidoJp;
    }

    public void setApellidoJp(String apellidoJp) {
        this.apellidoJp = apellidoJp;
    }

    public String getTelefonoJp() {
        return telefonoJp;
    }

    public void setTelefonoJp(String telefonoJp) {
        this.telefonoJp = telefonoJp;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Reportes> getReportesCollection() {
        return reportesCollection;
    }

    public void setReportesCollection(Collection<Reportes> reportesCollection) {
        this.reportesCollection = reportesCollection;
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
        if (!(object instanceof JefePlanta)) {
            return false;
        }
        JefePlanta other = (JefePlanta) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JefePlanta[ rut=" + rut + " ]";
    }
    
}
