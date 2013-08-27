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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessionBeans.AuditoriaLocal;
import sessionBeans.CrudUsuarioLocal;
import sessionBeans.MensajeriaLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "autentificador")
@SessionScoped
public class MAutentificador implements Serializable {

    @EJB
    private AuditoriaLocal auditoria;
    @EJB
    private CrudUsuarioLocal crudUsuario;
    @EJB
    private MensajeriaLocal mensajeria;
    @Inject
     private MAccionesGenerales ag;
    @Inject
     private MMessaegeController mc;
    
    private String rutRecuperar;
    
   

   

    

  

    

    

    public String getRutRecuperar() {
        return rutRecuperar;
    }

    public void setRutRecuperar(String rutRecuperar) {
        this.rutRecuperar = rutRecuperar;
    }

    public MAutentificador() {
    }

    public boolean login(String username, String password) {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {

            if (crudUsuario.analizarHabilitado(username) == true) {
                if(!isLogued()){
                    request.login(username, password);
                    registrarAccion("Inicio de sesión");
                }
                
                
                return true;
            } 
        } catch (ServletException e) {
            mc.mensajeRetroalimentacion("Error", "Usuario y/o contraseña incorrecta");
        } catch (Exception ex) {
            mc.mensajeRetroalimentacion("Error", ex.getMessage());
        }
            return false;
    }

    public void logout() {
        registrarAccion("Cierre de sesión");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        try {

            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MAutentificador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarPassword() {

        try {
            mensajeria.recuperarContraseña(rutRecuperar);
            rutRecuperar = null;

            mc.mensajeRetroalimentacion("Operación Exitosa", "Correo enviado");
        } catch (Exception e) {
            rutRecuperar = null;

            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }

    }

    public void registrarAccion(String descripcion) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String username = request.getRemoteUser();
        try {
            String name = crudUsuario.entregarNombre(username);
            auditoria.registrarAccion(descripcion, name);
        } catch (Exception e) {
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }

    public boolean isLogued(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        if(request.getRemoteUser()==null){
            return false;
        }
        return true;
    }
}
