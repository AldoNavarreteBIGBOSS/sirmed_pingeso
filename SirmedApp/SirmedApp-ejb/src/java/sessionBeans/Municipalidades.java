/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.Municipalidad;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class Municipalidades implements MunicipalidadesLocal {

   @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
   
   @Override
   public Collection<Municipalidad> listaMunicipalidades(){
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       return dF.getMunicipalidadDAO().findAll();
   }

}
