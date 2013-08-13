/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.ChoferDAO;
import entities.Chofer;
import java.util.Collection;
import java.util.LinkedList;
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
    
    
    @Override
    public Collection<Chofer> buscarPorMunicipalidad(String municipalidad){
        
        try{
            Collection<Chofer> c = new LinkedList();
            Query q = getEntityManager().createNamedQuery("Chofer.findByMunicipalidad");
            q.setParameter("nombreMunicipalidad", municipalidad);
            c = q.getResultList();
            return c;
        }
        catch(Exception e){
            return null;
        }
    
    }
}