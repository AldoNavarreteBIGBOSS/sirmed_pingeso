/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.Pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aldo
 */
public class RegistroPojo {
    
    private Integer idRegistro;
    private String municipalidad;
    private String fechaRegistro;
    private float pesajeCamion;
    private String patenteCamion;
    private String chofer;
    private String basculista;

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
        
        this.fechaRegistro = df.format(fechaRegistro);
    }

    public float getPesajeCamion() {
        return pesajeCamion;
    }

    public void setPesajeCamion(float pesajeCamion) {
        this.pesajeCamion = pesajeCamion;
    }

    public String getPatenteCamion() {
        return patenteCamion;
    }

    public void setPatenteCamion(String patenteCamion) {
        this.patenteCamion = patenteCamion;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getBasculista() {
        return basculista;
    }

    public void setBasculista(String basculista) {
        this.basculista = basculista;
    }

    public RegistroPojo() {
    }
    
    
}
