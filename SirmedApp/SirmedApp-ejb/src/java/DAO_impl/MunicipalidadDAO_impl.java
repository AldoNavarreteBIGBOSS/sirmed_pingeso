/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.MunicipalidadDAO;
import entities.Municipalidad;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class MunicipalidadDAO_impl extends GenericDAO_impl<Municipalidad> implements MunicipalidadDAO{
    public MunicipalidadDAO_impl(EntityManager em){
        super(Municipalidad.class, em);
}
    
    @Override
    public Municipalidad buscarPorMunicipalidad(String municipalidad){
        
        try{
            Municipalidad m = new Municipalidad();
            Query q = getEntityManager().createNamedQuery("Municipalidad.findByNombreMunicipalidad");
            q.setParameter("nombreMunicipalidad", municipalidad);
            m = (Municipalidad) q.getResultList().get(0);
            return m;
        }
        catch(Exception e){
            return null;
        }
    }
}
