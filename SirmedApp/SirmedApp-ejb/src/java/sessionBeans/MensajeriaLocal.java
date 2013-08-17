/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Local;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author Aldo
 */
@Local
public interface MensajeriaLocal {

    

    public void recuperarContraseña(String rut) throws Exception;

    public void enviarMensajeBienvenida(String mail, String contraseña, String nombre)throws AddressException, MessagingException;

    
    
}
