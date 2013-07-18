/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aldo
 */
@Named(value = "autentificador")
@SessionScoped
public class Autentificador implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Autentificador() {
    }
    
    public void login(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            //verificaSiEstaLogueado();
            System.out.println(request.getAuthType()+" "+request.getRemoteUser());
            request.login(username, password);
            externalContext.redirect(externalContext.getRequestContextPath()+"/faces/ingresarBasculista.xhtml");
        }
        catch (Exception e) {
        System.out.println("MENSAJE DE EXCEPCIÓN: "+e.getMessage());
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario o contraseña incorrectos", ""));
        }

    
    }
    
    public void logout() {
  ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
  externalContext.invalidateSession();
        try {
            externalContext.redirect(externalContext.getRequestContextPath()+"/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Autentificador.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
