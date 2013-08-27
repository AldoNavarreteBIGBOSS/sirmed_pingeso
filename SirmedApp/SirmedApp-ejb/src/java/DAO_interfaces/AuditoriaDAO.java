/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Auditoria;
import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface AuditoriaDAO extends GenericDAO<Auditoria>{
    
    public Collection<Auditoria> listarAuditoriaPorFecha(String fecha);
}
