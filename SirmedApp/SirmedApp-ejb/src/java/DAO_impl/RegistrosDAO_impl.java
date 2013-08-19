/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.RegistrosDAO;
import entities.Registro;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class RegistrosDAO_impl extends GenericDAO_impl <Registro> implements RegistrosDAO{
    
    public RegistrosDAO_impl(EntityManager em){
        super(Registro.class, em);
}
    @Override
    public Collection<Registro> generarInforme(String fechaActual, String hora){
    try{
        Query q = getEntityManager().createNamedQuery("Registro.findByFechaRegistroLike");
        q.setParameter("fecha", fechaActual);
        
        
        return q.getResultList();
    }
    catch(Exception e){
        return null;
    }
    }
    
    @Override
    public Collection<Registro> listarRegistrosPorFecha(String fechaInicio, String fechaFin){
        
        try{
            Query q = getEntityManager().createNamedQuery("Registro.findByFechaRegistroBetween");
            q.setParameter("fechaInicio", fechaInicio);
            
            q.setParameter("fechaFin", fechaFin);
            return q.getResultList();
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public Collection<Registro> listarRegistrosPorFechaMunicipalidad(String fechaInicio, String fechaFin, String municipalidad){
    
    try{
            Query q = getEntityManager().createNamedQuery("Registro.findByFechaRegistroBetweenMuni");
            q.setParameter("fechaInicio", fechaInicio);       
            q.setParameter("fechaFin", fechaFin);
            q.setParameter("municipalidad", municipalidad);
            return q.getResultList();
        }
        catch(Exception e){
            return null;
        }
    }
}
