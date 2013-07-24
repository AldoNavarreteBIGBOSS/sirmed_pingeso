/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.TipoRecoleccion;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudTipoRecoleccion implements CrudTipoRecoleccionLocal {

   @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
   
   @Override
   public Collection<TipoRecoleccion> listaTipoRecoleccion(){
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       return dF.getTipoRecoleccionDAO().findAll();
   }

}
