/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.BasculistaDAO;
import entities.Basculista;
import java.util.List;
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
        System.out.println(rut);
        
        Query q = getEntityManager().createNamedQuery("Basculista.findByRut");
        q.setParameter("rut", rut);
        List<Basculista> lb = (List<Basculista>) q.getResultList();
        if(lb.isEmpty()){
            return null;
        }
         return lb.get(0);
        
    }

}