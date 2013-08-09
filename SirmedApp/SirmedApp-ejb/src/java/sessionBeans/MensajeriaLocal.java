/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 *
 * @author Aldo
 */
@Local
public interface MensajeriaLocal {

    public void enviarMail() throws MessagingException;

    public void recuperarContraseña(String rut) throws Exception;
    
}
