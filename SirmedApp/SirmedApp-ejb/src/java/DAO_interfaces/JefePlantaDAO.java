/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import entities.JefePlanta;

/**
 *
 * @author Aldo
 */
public interface JefePlantaDAO extends GenericDAO<JefePlanta>{
    public JefePlanta buscarPorRut(String rut);
}
