/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.PuntoRecoleccion;
import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface PuntoRecoleccionDAO extends GenericDAO<PuntoRecoleccion>{
    
    public PuntoRecoleccion buscarPorDireccionLike(String direccion);
    public Collection<PuntoRecoleccion> buscarPorMunicipalidad(String municipalidad);
}
