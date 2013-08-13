/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Camion;
import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface CamionDAO extends GenericDAO<Camion>{
    
     public Camion buscarPorPatente(String patente);
      public Collection<Camion> buscarPorMunicipalidad(String municipalidad);
}
