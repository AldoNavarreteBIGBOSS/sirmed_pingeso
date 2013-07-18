/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.BasculistaDAO;
import entities.Basculista;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class BasculistaDAO_impl extends GenericDAO_impl<Basculista> implements BasculistaDAO{
    public BasculistaDAO_impl(EntityManager em){
        super(Basculista.class, em);
}
}