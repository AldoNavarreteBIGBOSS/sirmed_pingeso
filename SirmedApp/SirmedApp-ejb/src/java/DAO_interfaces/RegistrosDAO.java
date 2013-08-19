/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Registro;
import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface RegistrosDAO extends GenericDAO <Registro> {
    
    public Collection<Registro> generarInforme(String fechaActual, String hora);
    
     public Collection<Registro> listarRegistrosPorFecha(String fechaInicio, String fechaFin);
     
     public Collection<Registro> listarRegistrosPorFechaMunicipalidad(String fechaInicio, String fechaFin, String municipalidad);
}
