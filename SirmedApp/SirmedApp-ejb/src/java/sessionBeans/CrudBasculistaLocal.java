/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Basculista;
import entities.TurnoTrabajo;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudBasculistaLocal {

    void crearBasculista(String rut, String turno, String nombre, String apellido, String telefono);
    Collection<Basculista> listaBasculistas();
    
}
