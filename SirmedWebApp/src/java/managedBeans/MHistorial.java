/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Auditoria;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.Pojo.AuditoriaPojo;
import sessionBeans.AuditoriaLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mHistorial")
@RequestScoped
public class MHistorial {
    
    @EJB
    private AuditoriaLocal auditoria;
   
    
    private Date fechaSeleccionada;
    private List<AuditoriaPojo> auditoriaPojos;
    

    public List<AuditoriaPojo> getAuditoriaPojos() {
        return auditoriaPojos;
    }

    public void setAuditoriaPojos(List<AuditoriaPojo> auditoriaPojos) {
        this.auditoriaPojos = auditoriaPojos;
    }
    
    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }
    
    @PostConstruct
    public void init(){
        cargarListaInicio();
    }
    
    public MHistorial() {
    }
    
    public void cargarListaInicio(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Collection<Auditoria> ca = auditoria.listarAcciones(df.format(date));
        
        auditoriaPojos = new LinkedList<AuditoriaPojo>();
        for(Auditoria a: ca){
            AuditoriaPojo ap = new AuditoriaPojo();
            ap.setFecha(a.getFechaAuditoria());
            ap.setDescripción(a.getDescripcionAuditoria());
            ap.setInformacion(a.getInfoAuditoria());
            auditoriaPojos.add(ap);
        }
    }
    
    public void cargarListaFecha(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Collection<Auditoria> ca = auditoria.listarAcciones(df.format(fechaSeleccionada));
        
        auditoriaPojos = new LinkedList<AuditoriaPojo>();
        if(ca != null){
        for(Auditoria a: ca){
            AuditoriaPojo ap = new AuditoriaPojo();
            ap.setFecha(a.getFechaAuditoria());
            ap.setDescripción(a.getDescripcionAuditoria());
            ap.setInformacion(a.getInfoAuditoria());
            auditoriaPojos.add(ap);
        }
        }
    }
}
