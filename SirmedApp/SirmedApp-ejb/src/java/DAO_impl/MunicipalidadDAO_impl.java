/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.MunicipalidadDAO;
import entities.Municipalidad;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class MunicipalidadDAO_impl extends GenericDAO_impl<Municipalidad> implements MunicipalidadDAO{
    public MunicipalidadDAO_impl(EntityManager em){
        super(Municipalidad.class, em);
}
}
