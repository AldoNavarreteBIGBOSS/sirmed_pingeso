/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.PuntoRecoleccion;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface CrudRecoleccionLocal {

    public void crearPuntoRecoleccion(String direccion, String nombrePunto, String descripcion);

    public Collection<PuntoRecoleccion> listaPuntosRecoleccion();

    public void editarPuntoRecoleccion(String direccion, String nombrePunto, String descripcion);

    public void eliminarPuntoRecoleccion(String direccion, String nombrePunto, String descripcion);
    
}
