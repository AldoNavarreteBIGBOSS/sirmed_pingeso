/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudUsuarioLocal {

    void crearUsuario(String rut, String email);
    
}
