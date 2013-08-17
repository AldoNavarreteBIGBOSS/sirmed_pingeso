/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.UsuarioDAO;
import java.util.Properties;
import javax.ejb.Timer;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
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
    private Properties props;
    private Session session;
    
    
    @PostConstruct
    public void inicioConfiguracion(){
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
        MimeMessage message = new MimeMessage(session); 
        message.setSubject("SIRMED: Contraseña Reestablecida");
        message.setText(mensaje);
        message.setFrom(new InternetAddress("arden.papifunk@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        
        Transport t = session.getTransport("smtp");
        t.connect("arden.papifunk@gmail.com", "2850326");
        t.sendMessage(message, message.getAllRecipients());
        message = null;
        t.close();
    }  
    
    @Override
    public void enviarMensajeBienvenida(String mail, String contraseña, String nombre)throws AddressException, MessagingException{
    
        String mensaje = "Felicidades! "+nombre+"\n Eres parte de SIRMED tus datos son: \n Usuario: "+contraseña+"  \n Contraseña: "+contraseña+"";
        
        MimeMessage message = new MimeMessage(session); 
        message.setFrom(new InternetAddress("arden.papifunk@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        message.setSubject("SIRMED: Bienvenido!");
        message.setText(mensaje);

        Transport t = session.getTransport("smtp");
        t.connect("arden.papifunk@gmail.com", "2850326");
        t.sendMessage(message, message.getAllRecipients());
        message = null;
        t.close();
        
    
    }
}
