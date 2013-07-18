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
@Table(name = "turno_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoTrabajo.findAll", query = "SELECT t FROM TurnoTrabajo t"),
    @NamedQuery(name = "TurnoTrabajo.findByNombreTurno", query = "SELECT t FROM TurnoTrabajo t WHERE t.nombreTurno = :nombreTurno")})
public class TurnoTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBRE_TURNO")
    private String nombreTurno;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRP_TURNO")
    private String descrpTurno;
    @OneToMany(mappedBy = "nombreTurno")
    private Collection<Basculista> basculistaCollection;

    public TurnoTrabajo() {
    }

    public TurnoTrabajo(String nombreTurno) {
        this.nombreTurno = nombreTurno;
    }

    public String getNombreTurno() {
        return nombreTurno;
    }

    public void setNombreTurno(String nombreTurno) {
        this.nombreTurno = nombreTurno;
    }

    public String getDescrpTurno() {
        return descrpTurno;
    }

    public void setDescrpTurno(String descrpTurno) {
        this.descrpTurno = descrpTurno;
    }

    @XmlTransient
    public Collection<Basculista> getBasculistaCollection() {
        return basculistaCollection;
    }

    public void setBasculistaCollection(Collection<Basculista> basculistaCollection) {
        this.basculistaCollection = basculistaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreTurno != null ? nombreTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoTrabajo)) {
            return false;
        }
        TurnoTrabajo other = (TurnoTrabajo) object;
        if ((this.nombreTurno == null && other.nombreTurno != null) || (this.nombreTurno != null && !this.nombreTurno.equals(other.nombreTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TurnoTrabajo[ nombreTurno=" + nombreTurno + " ]";
    }
    
}
