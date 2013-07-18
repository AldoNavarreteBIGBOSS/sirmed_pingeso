/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.TurnoTrabajoDAO;
import entities.TurnoTrabajo;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class TurnoTrabajoDAO_impl extends GenericDAO_impl<TurnoTrabajo> implements TurnoTrabajoDAO{
    public TurnoTrabajoDAO_impl(EntityManager em){
        super(TurnoTrabajo.class, em);
}
    
}
