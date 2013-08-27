/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Auditoria;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface AuditoriaLocal {

    public void registrarAccion(String descripcion, String información) throws Exception;

    public Collection<Auditoria> listarAcciones(String fecha);
    
}
