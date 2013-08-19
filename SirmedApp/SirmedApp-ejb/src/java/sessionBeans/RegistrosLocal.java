/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.PuntoRecoleccion;
import entities.Registro;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface RegistrosLocal {

    public void crearRegistro(String rutBasculista, String municipalidad, String patenteCamion, String rutChofer, float pesaje, String comentario, Collection<PuntoRecoleccion> puntosSeleccionados) throws Exception;

    public String generarInforme(Integer hora);

    public Collection<Registro> listarRegistroPorFechas(String fecha1, String fecha2) throws Exception;

    public Collection<Registro> listarRegistroPorFechasMunicipalidad(String fecha1, String fecha2, String municipalidad) throws Exception;
    
}
