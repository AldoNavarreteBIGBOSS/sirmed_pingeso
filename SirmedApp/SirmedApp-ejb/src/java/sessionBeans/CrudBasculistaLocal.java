/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Basculista;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudBasculistaLocal {

    void crearBasculista(String rut, String nombre, String apellido, String telefono);
    
    Collection<Basculista> listaBasculistas();

    public void editarBasculista(String rut, String nombre, String apellido, String telefono) throws Exception;

    public void eliminarBasculista(String rut) throws Exception;
    
   
    
}
