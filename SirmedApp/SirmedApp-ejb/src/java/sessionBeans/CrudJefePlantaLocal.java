/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.JefePlanta;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudJefePlantaLocal {

    public JefePlanta entregarPorRut(String rut) throws Exception;

    public void actualizarDatosPersonales(String rut, String nombre, String apellido, String telefono, String email) throws Exception;
    
}
