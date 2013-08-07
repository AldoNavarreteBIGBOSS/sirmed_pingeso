/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.util.Properties;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
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
@Stateless
public class Mensajeria implements MensajeriaLocal {

    @Schedule(hour="*/5")
    @Override
    public void enviarMail() throws MessagingException {
        String mensaje = "Este es un mensaje de SIRMED cada 5 horas, por favor responder si llegó";
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "arden.papifunk@gmail.com");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("arden.papifunk@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("victor.floress@usach.cl"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("arden.papifunk@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("hialek@gmail.com"));
        message.setSubject("SIRMED: Prueba");
        message.setText(mensaje);

        Transport t = session.getTransport("smtp");
        t.connect("arden.papifunk@gmail.com", "2850326");
        t.sendMessage(message, message.getAllRecipients());
        t.close();
    }

}
