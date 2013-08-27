/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aldo
 */
@Named(value = "mVerificador")
@RequestScoped
public class MVerificador {

    @Inject
    private MAutentificador autentificador;
    @Inject
    private MAccionesGenerales ag;
    @Inject
    private MMessaegeController mc;
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

    
    
    
    
    @PostConstruct
    public void init() {
        if (autentificador.isLogued()) {

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

            if (request.isUserInRole("JefePlanta")) {
                ag.goToPage("/faces/jefePlanta/inicio.xhtml");
            }
            if (request.isUserInRole("Basculista")) {
                ag.goToPage("/faces/basculista/registros.xhtml");
            }
        }



    }

    public void login() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (autentificador.login(username, password)) {

            if (request.isUserInRole("JefePlanta")) {
                ag.goToPage("/faces/jefePlanta/inicio.xhtml");
            }
            if (request.isUserInRole("Basculista")) {
                ag.goToPage("/faces/basculista/registros.xhtml");
            }

        } else {
            mc.mensajeRetroalimentacion("Advertencia", "Usuario no habilitado");
        }

    }

    public MVerificador() {
    }
}
