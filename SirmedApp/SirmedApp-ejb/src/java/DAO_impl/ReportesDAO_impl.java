/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.ReportesDAO;
import entities.Reportes;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class ReportesDAO_impl extends GenericDAO_impl <Reportes> implements ReportesDAO{
 public ReportesDAO_impl(EntityManager em){
        super(Reportes.class, em);   
}
}
