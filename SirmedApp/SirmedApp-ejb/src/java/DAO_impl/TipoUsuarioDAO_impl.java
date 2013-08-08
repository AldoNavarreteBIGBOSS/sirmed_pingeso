/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.TipoUsuarioDAO;
import entities.TipoUsuario;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class TipoUsuarioDAO_impl extends GenericDAO_impl<TipoUsuario> implements TipoUsuarioDAO{
    public TipoUsuarioDAO_impl(EntityManager em){
        super(TipoUsuario.class, em);
     }  
}
