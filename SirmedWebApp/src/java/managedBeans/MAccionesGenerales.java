/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aldo
 */
@Named(value = "accionesGenerales")
@RequestScoped
public class MAccionesGenerales {

    
    public MAccionesGenerales() {
    }
    
    public void actualizarPagina () throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void goToPage(String url){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + url);
        } catch (IOException ex) {
            Logger.getLogger(MAccionesGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String devolverUsername(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
         
         return request.getRemoteUser();
        
    
    }
    
    
}
