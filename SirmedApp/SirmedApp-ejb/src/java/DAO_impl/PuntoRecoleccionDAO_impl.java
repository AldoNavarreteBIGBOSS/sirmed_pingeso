/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.PuntoRecoleccionDAO;
import entities.PuntoRecoleccion;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class PuntoRecoleccionDAO_impl extends GenericDAO_impl<PuntoRecoleccion> implements PuntoRecoleccionDAO{
    
    public PuntoRecoleccionDAO_impl(EntityManager em){
        super(PuntoRecoleccion.class, em);
}
}