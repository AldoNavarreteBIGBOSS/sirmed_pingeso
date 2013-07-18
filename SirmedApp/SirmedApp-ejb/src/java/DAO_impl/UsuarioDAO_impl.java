/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.UsuarioDAO;
import entities.Usuario;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class UsuarioDAO_impl extends GenericDAO_impl<Usuario> implements UsuarioDAO{
    public UsuarioDAO_impl(EntityManager em){
        super(Usuario.class, em);
    }
}
