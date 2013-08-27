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
public class AuditoriaPojo {
    
    private String fecha;
    private String descripción;
    private String informacion;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        this.fecha = df.format(fecha);
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public AuditoriaPojo() {
    }
    
    
    
}
