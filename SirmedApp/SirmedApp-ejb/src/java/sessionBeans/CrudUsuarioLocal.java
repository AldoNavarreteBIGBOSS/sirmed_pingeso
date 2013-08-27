/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudUsuarioLocal {

    public void crearUsuario(String rut, String email)throws Exception;

    public void eliminarUsuario(String rut)throws Exception;

    public void actualizarUsuario(String rut, String newPassword)throws Exception;

    public Usuario entregarPorRut(String rut) throws Exception;

    public boolean analizarContraseña(String rut, String oldPass) throws Exception;

    public boolean analizarHabilitado(String rut) throws Exception;

    public String entregarNombre(String rut) throws Exception;
    
    
    
}
