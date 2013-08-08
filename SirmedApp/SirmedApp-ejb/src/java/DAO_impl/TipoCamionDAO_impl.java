/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.TipoCamionDAO;
import entities.TipoCamion;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class TipoCamionDAO_impl extends GenericDAO_impl<TipoCamion> implements TipoCamionDAO{
    public TipoCamionDAO_impl(EntityManager em){
        super(TipoCamion.class, em);
    }
}
