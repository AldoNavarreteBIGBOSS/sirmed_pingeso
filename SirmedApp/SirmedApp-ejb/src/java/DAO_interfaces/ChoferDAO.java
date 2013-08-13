/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Chofer;
import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface ChoferDAO extends GenericDAO<Chofer>{
    
    public Chofer buscarPorRut(String rut);
    public Collection<Chofer> buscarPorMunicipalidad(String municipalidad);
}
