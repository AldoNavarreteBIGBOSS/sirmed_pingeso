/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.TipoRecoleccion;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudTipoRecoleccionLocal {

    public Collection<TipoRecoleccion> listaTipoRecoleccion();
    
}
