/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aldo
 */
@Named(value = "messaegeController")
@RequestScoped
public class MMessaegeController {

    /**
     * Creates a new instance of MMessaegeController
     */
    public void mensajeRetroalimentacion(String texto1, String texto2) {  
        
        FacesContext context = FacesContext.getCurrentInstance();  
          
        context.addMessage(null, new FacesMessage(texto1,texto2));  
          
    }  
    
    
}
