/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.AuditoriaDAO;
import entities.Auditoria;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class AuditoriaDAO_impl extends GenericDAO_impl<Auditoria> implements AuditoriaDAO{
    public AuditoriaDAO_impl(EntityManager em){
        super(Auditoria.class, em);
    }
    
    @Override
    public Collection<Auditoria> listarAuditoriaPorFecha(String fecha){
        try{
            Query q = getEntityManager().createNamedQuery("Auditoria.findByFechaAuditoria");
            q.setParameter("fechaAuditoria", fecha);
            return q.getResultList();
        }
        catch(Exception e){
            return null;
        }
    }
}
