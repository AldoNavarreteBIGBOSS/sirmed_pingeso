/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.PuntoRecoleccionDAO;
import entities.PuntoRecoleccion;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class PuntoRecoleccionDAO_impl extends GenericDAO_impl<PuntoRecoleccion> implements PuntoRecoleccionDAO{
    
    public PuntoRecoleccionDAO_impl(EntityManager em){
        super(PuntoRecoleccion.class, em);
}
    @Override
    public PuntoRecoleccion buscarPorDireccionLike(String direccion){
    
        try{
            PuntoRecoleccion pr = new PuntoRecoleccion();
            Query q = getEntityManager().createNamedQuery("PuntoRecoleccion.finByDireccionLike");
            q.setParameter("direccionPunto", direccion);
            pr = (PuntoRecoleccion) q.getResultList().get(0);
            return pr;
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public Collection<PuntoRecoleccion> buscarPorMunicipalidad(String municipalidad){
    
        try{
            Collection<PuntoRecoleccion> c = new LinkedList();
            Query q = getEntityManager().createNamedQuery("PuntoRecoleccion.findByMunicipalidad");
            q.setParameter("nombreMunicipalidad", municipalidad);
            c = q.getResultList();
            return c;
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
   public Collection<PuntoRecoleccion> listarPuntosRecoleccionOrden(){
       try{
           Collection<PuntoRecoleccion> c = new LinkedList();
           Query q = getEntityManager().createNamedQuery("PuntoRecoleccion.findAll");
           c = q.getResultList();
           return c;
       }
       catch(Exception e){
           return null;
       }
   } 
}