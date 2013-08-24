/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.CamionDAO;
import entities.Camion;
import java.util.Collection;
import java.util.LinkedList;
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
    
    @Override
    public Collection<Camion> buscarPorMunicipalidad(String municipalidad){
    
        try{
            
            Collection<Camion> c;
            
            Query q = getEntityManager().createNamedQuery("Camion.findByMunicipalidad");
           
            q.setParameter("nombreMunicipalidad", municipalidad);
         
            c = q.getResultList();
           
            
            return c;
        }
        catch(Exception e){
            
            return null;
        }
    }
}