/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.RegistrosDAO;
import entities.Registros;
import entities.Usuario;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class RegistrosDAO_impl extends GenericDAO_impl <Registros> implements RegistrosDAO{
    
    public RegistrosDAO_impl(EntityManager em){
        super(Registros.class, em);
}
}
