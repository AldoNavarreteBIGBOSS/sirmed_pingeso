/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.TurnoTrabajo;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class ListaTurnos implements ListaTurnosLocal {

   @PersistenceContext(unitName = "SirmedApp-ejbPU")
   private EntityManager em;
    
    public Collection<TurnoTrabajo> listaTurnos(){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       return dF.getTurnoTrabajoDAO().findAll();
        
    }

}
