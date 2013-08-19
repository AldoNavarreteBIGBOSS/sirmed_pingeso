/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.JefePlantaDAO;
import entities.JefePlanta;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class JefePlantaDAO_impl extends GenericDAO_impl<JefePlanta> implements JefePlantaDAO{
    
    public JefePlantaDAO_impl(EntityManager em){
        super(JefePlanta.class, em);
}
    
    @Override
    public JefePlanta buscarPorRut(String rut) {

        try {
            JefePlanta jp = new JefePlanta();
            Query q = getEntityManager().createNamedQuery("JefePlanta.findByRut");
            q.setParameter("rut", rut);
            jp = (JefePlanta) q.getResultList().get(0);
            
            return jp;
        } catch (Exception e) {
            return null;
        }

    }
}