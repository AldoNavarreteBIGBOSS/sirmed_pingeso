/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Aldo
 */
@Singleton
public class InformeMail implements InformeMailLocal {
    @EJB
    private AuditoriaLocal auditoria;

    @EJB
    private RegistrosLocal registros;
    private Properties props;
    private Session session;
    private Timer timer;
    private Date date;
  
    @Resource TimerService timerService;
    
    @PostConstruct
    public void inicioConfiguracion(){
        date =  new Date();
        props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "SIRMED");
        props.setProperty("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(props);
        session.setDebug(true);          
    }
    
    
    
    @Override
    public void determinarHora(String h1, String h2){
    
        
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.minute("0");
        schedule.hour(h1+","+h2);
        schedule.second("0");
        try {
            auditoria.registrarAccion("Hora de envio de informe configurada", "Hora 1: "+h1+" Hora 2: "+h2);
        } catch (Exception ex) {
            Logger.getLogger(InformeMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer = (Timer) timerService.createCalendarTimer(schedule);
    }
    
    @Timeout
    private void enviarMail(Timer timer) throws MessagingException, Exception {
       
        String mensaje = null;       
        Integer hora = date.getHours();
        mensaje = registros.generarInforme(hora);
               
        MimeMessage message = new MimeMessage(session); 
        message.setFrom(new InternetAddress("arden.papifunk@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("arden.papifunk@gmail.com"));
        message.setSubject("SIRMED: Informe de registros");
        message.setText(mensaje);

        Transport t = session.getTransport("smtp");
        t.connect("arden.papifunk@gmail.com", "2850326");
        t.sendMessage(message, message.getAllRecipients());
        auditoria.registrarAccion("Envio de informe", hora+":00 hrs.");
        message = null;
        t.close();
    }

    
}
