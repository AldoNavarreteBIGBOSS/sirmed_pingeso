/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.Camion;
import entities.Municipalidad;
import entities.TipoCamion;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudCamion implements CrudCamionLocal {

   @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

   @Override
   public void crearCamion(String patente, String municipalidad, int tipoCamion){
       Camion c = new Camion();
       TipoCamion tc = new TipoCamion(tipoCamion);
       Municipalidad m = new Municipalidad(municipalidad);
       c.setPatente(patente);
       c.setIdTc(tc);
       c.setNombreMunicipalidad(m);
       
       
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       dF.getCamionDAO().insert(c);
   }
   
   @Override
   public Collection<Camion> listaCamiones(){
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       return dF.getCamionDAO().findAll();
   }

   @Override
   public void editarCamion(String patente, String municipalidad, int tipoCamion){
       Camion c = new Camion();
       TipoCamion tc = new TipoCamion(tipoCamion);
       Municipalidad m = new Municipalidad(municipalidad);
       c.setPatente(patente);
       c.setIdTc(tc);
       c.setNombreMunicipalidad(m);
       
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       dF.getCamionDAO().update(c);
   }
   
   @Override
   public void eliminarCamion(String patente, String municipalidad, int tipoCamion){
       Camion c = new Camion();
       TipoCamion tc = new TipoCamion(tipoCamion);
       Municipalidad m = new Municipalidad(municipalidad);
       c.setPatente(patente);
       c.setIdTc(tc);
       c.setNombreMunicipalidad(m);
       
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       dF.getCamionDAO().delete(c);
   }
   
   
}
