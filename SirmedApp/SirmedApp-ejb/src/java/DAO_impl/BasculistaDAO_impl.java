/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.BasculistaDAO;
import entities.Basculista;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class BasculistaDAO_impl extends GenericDAO_impl<Basculista> implements BasculistaDAO{
    public BasculistaDAO_impl(EntityManager em){
        super(Basculista.class, em);
     }  
    
    @Override
    public Basculista buscarPorRut(String rut){
        
        Basculista b;
        Query q = getEntityManager().createNamedQuery("Basculista.findByRut");
        q.setParameter("rut", rut);
        b = (Basculista) q.getResultList().get(0);
        
        if(b != null){
            return b;
        }
        else{
            return null;
        }
        
    }

}