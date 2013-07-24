/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Chofer;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudChoferLocal {

    public void crearChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad);

    public Collection<Chofer> listaChoferes();

    public void editarChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad);

    public void eliminarChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad);
    
}
