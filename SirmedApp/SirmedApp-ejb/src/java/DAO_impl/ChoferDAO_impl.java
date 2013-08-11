/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.ChoferDAO;
import entities.Chofer;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class ChoferDAO_impl extends GenericDAO_impl <Chofer> implements ChoferDAO{
    public ChoferDAO_impl(EntityManager em){
        super(Chofer.class, em);
}
    @Override
    public Chofer buscarPorRut(String rut){
        try{
            Chofer c = new Chofer();
            Query q = getEntityManager().createNamedQuery("Chofer.findByRutChofer");
            q.setParameter("rutChofer", rut);
            c = (Chofer) q.getResultList().get(0);
            return c;
        }
        catch(Exception e){
            return null;
        }
    }
}