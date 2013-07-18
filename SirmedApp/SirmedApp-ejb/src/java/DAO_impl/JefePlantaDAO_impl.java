/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.JefePlantaDAO;
import entities.JefePlanta;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class JefePlantaDAO_impl extends GenericDAO_impl<JefePlanta> implements JefePlantaDAO{
    
    public JefePlantaDAO_impl(EntityManager em){
        super(JefePlanta.class, em);
}
}