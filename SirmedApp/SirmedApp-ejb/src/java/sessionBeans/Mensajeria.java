/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.UsuarioDAO;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class Mensajeria implements MensajeriaLocal {
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

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

    @Override
    public void recuperarContraseña(String rut) throws Exception{
        String mail;
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO udao = dF.getUsuarioDAO();
        
        mail = udao.recuperarMail(rut);
        
        if(mail != null){
            crudUsuario.actualizarUsuario(rut, mail, rut);
            enviarNuevaContraseña(mail, rut);
        }
        else{
            
            throw new Exception("Usuario no registrado");
        }
        
        
    }
    
    private void enviarNuevaContraseña(String mail, String contraseña) throws AddressException, MessagingException{
        
        String mensaje = "Tu contraseña ha sido reestablecida, el nuevo password es: "+contraseña+"";
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
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        message.setSubject("SIRMED: Contraseña Reestablecida");
        message.setText(mensaje);

        Transport t = session.getTransport("smtp");
        t.connect("arden.papifunk@gmail.com", "2850326");
        t.sendMessage(message, message.getAllRecipients());
        t.close();
    }
}
