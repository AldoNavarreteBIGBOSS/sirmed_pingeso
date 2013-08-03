/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Camion;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudCamionLocal {

    public Collection<Camion> listaCamiones();

    public void crearCamion(String patente, String municipalidad, int tipoCamion);

    public void editarCamion(String patente, String municipalidad, int tipoCamion);

    public void eliminarCamion(String patente, String municipalidad, int tipoCamion);

    
    
}
