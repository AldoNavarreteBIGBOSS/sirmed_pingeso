/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.ChoferDAO;
import entities.Chofer;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class ChoferDAO_impl extends GenericDAO_impl <Chofer> implements ChoferDAO{
    public ChoferDAO_impl(EntityManager em){
        super(Chofer.class, em);
}
}