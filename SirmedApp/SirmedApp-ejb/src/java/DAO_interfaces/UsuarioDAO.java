/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.Usuario;

/**
 *
 * @author Aldo
 */
public interface UsuarioDAO extends GenericDAO <Usuario> {
    
    public String recuperarMail(String rut);
    public Usuario buscarPorRut(String rut);
    public boolean estarHabilitado(String rut)throws Exception;
}
