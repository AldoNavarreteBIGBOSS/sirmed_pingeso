/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.AuditoriaDAO;
import entities.Auditoria;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class AuditoriaDAO_impl extends GenericDAO_impl<Auditoria> implements AuditoriaDAO{
    public AuditoriaDAO_impl(EntityManager em){
        super(Auditoria.class, em);
    }
    
}
