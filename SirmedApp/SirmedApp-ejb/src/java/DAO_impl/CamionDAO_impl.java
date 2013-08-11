/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.CamionDAO;
import entities.Camion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class CamionDAO_impl extends GenericDAO_impl<Camion> implements CamionDAO{
    public CamionDAO_impl(EntityManager em){
        super(Camion.class, em);
}
    
    @Override
    public Camion buscarPorPatente(String patente){
        
        try{
            Camion c = new Camion();
            Query q = getEntityManager().createNamedQuery("Camion.findByPatente");
            q.setParameter("patente", patente);
            c = (Camion) q.getResultList().get(0);
            return c;
        }
        catch(Exception e){
            return null;
        }
    }
}