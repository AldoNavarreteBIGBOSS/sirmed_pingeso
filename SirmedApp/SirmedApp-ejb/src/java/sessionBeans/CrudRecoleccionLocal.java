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

    public void crearPuntoRecoleccion(String direccion, String nombrePunto, String descripcion, String municipalidad, Collection<String> tipoRecoleccion) throws Exception;

    public Collection<PuntoRecoleccion> listaPuntosRecoleccion();

    public void editarPuntoRecoleccion(Integer idTipo, String direccion, String nombrePunto, String descripcion, String municipalidad, Collection<String> tipoRecoleccion) throws Exception;

    public void eliminarPuntoRecoleccion(Integer idTipo) throws Exception;
    
}
