/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.CamionDAO;
import entities.Camion;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class CamionDAO_impl extends GenericDAO_impl<Camion> implements CamionDAO{
    public CamionDAO_impl(EntityManager em){
        super(Camion.class, em);
}
}