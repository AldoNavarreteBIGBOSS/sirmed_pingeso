/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Municipalidad;

/**
 *
 * @author Aldo
 */
public interface MunicipalidadDAO extends GenericDAO <Municipalidad>{
    
    public Municipalidad buscarPorMunicipalidad(String municipalidad);
}
