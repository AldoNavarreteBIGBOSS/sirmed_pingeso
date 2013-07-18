/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.TurnoTrabajo;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface ListaTurnosLocal {
    
    public Collection<TurnoTrabajo> listaTurnos();
}
