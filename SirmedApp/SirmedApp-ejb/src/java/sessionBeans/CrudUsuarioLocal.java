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

    public void eliminarUsuario(String rut);

    public void actualizarUsuario(String rut, String email, String newPassword);
    
    
    
}
