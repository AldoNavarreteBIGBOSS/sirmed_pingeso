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
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import sessionBeans.MensajeriaLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "autentificador")
@SessionScoped
public class MAutentificador implements Serializable {
    
    @EJB
    private MensajeriaLocal mensajeria;

    
    private String username;
    private String password;
    private String rutRecuperar;
    private MAccionesGenerales ag;
    private MMessaegeController mc;

   
    
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

    public String getRutRecuperar() {
        return rutRecuperar;
    }

    public void setRutRecuperar(String rutRecuperar) {
        this.rutRecuperar = rutRecuperar;
    }
    
    
    public MAutentificador() {
    }
    
    public void login(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        ag = new MAccionesGenerales();
        try {
            request.login(username, password);
            
            if (request.isUserInRole("JefePlanta")) {
                ag.goToPage("/faces/jefePlanta/ingresarBasculista.xhtml");
            }
            if(request.isUserInRole("Basculista")){
                ag.goToPage("/faces/Basculista/ingresarBasculista.xhtml");
            }
        } catch (Exception e) {
            mc.mensajeRetroalimentacion("Error", "Usuario y/o contraseña incorrecta");
        }   
    }
    
    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MAutentificador.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    public void recuperarPassword(){
        
        try{
            mensajeria.recuperarContraseña(rutRecuperar);
            rutRecuperar = null;
            mc = new MMessaegeController();
            mc.mensajeRetroalimentacion("Operación Exitosa", "Correo enviado");
        }
        catch(Exception e){
            rutRecuperar = null;
            mc = new MMessaegeController();
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
            
    }
    
    
}
