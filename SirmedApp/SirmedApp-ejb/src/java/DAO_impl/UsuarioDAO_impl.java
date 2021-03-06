/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.UsuarioDAO;
import entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Aldo
 */
public class UsuarioDAO_impl extends GenericDAO_impl<Usuario> implements UsuarioDAO{
    public UsuarioDAO_impl(EntityManager em){
        super(Usuario.class, em);
    }
    
    @Override
    public String recuperarMail(String rut){
        
        String mail = null;
        try {
            Query q = getEntityManager().createNamedQuery("Usuario.findMailByRut");
            q.setParameter("rut", rut);
            mail = q.getResultList().get(0).toString();
            return mail;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Usuario buscarPorRut(String rut) {

        try {
            Usuario u = new Usuario();
            Query q = getEntityManager().createNamedQuery("Usuario.findByRut");
            q.setParameter("rut", rut);
            u = (Usuario) q.getResultList().get(0);
            
            return u;
        } catch (Exception e) {
            return null;
        }

    }
    
    @Override
    public boolean estarHabilitado(String rut)throws Exception{
    
        try {
            Usuario u = buscarPorRut(rut);
            if (u.getHabilitado() == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("No existe el usuario");
        }

    
    }
    
}
