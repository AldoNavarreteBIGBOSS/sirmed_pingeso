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
public interface RegistrosLocal {

    public void crearRegistro(String rutBasculista, String municipalidad, String patenteCamion, String rutChofer, float pesaje, String comentario, Collection<PuntoRecoleccion> puntosSeleccionados) throws Exception;
    
}
