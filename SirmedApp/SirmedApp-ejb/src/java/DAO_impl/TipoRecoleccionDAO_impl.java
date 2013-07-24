/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.TipoRecoleccionDAO;
import entities.TipoRecoleccion;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class TipoRecoleccionDAO_impl extends GenericDAO_impl<TipoRecoleccion> implements TipoRecoleccionDAO{
    public TipoRecoleccionDAO_impl(EntityManager em){
        super(TipoRecoleccion.class, em);
}
}
