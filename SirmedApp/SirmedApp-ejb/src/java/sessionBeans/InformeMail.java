/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.util.Date;
import java.util.Properties;
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
    private RegistrosLocal registros;
    private Properties props;
    private Session session;
    private Timer timer;
    private Integer ultimo = null;
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
    /*Modificar cuando se haga la vista del Jefe de Planta Configiración*/
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.minute("*");
        schedule.hour("*");
        schedule.second(20);
        
        timer = (Timer) timerService.createCalendarTimer(schedule);
    }
    
    @Timeout
    private void enviarMail(Timer timer) throws MessagingException {
       
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
        message = null;
        t.close();
    }

    
}
