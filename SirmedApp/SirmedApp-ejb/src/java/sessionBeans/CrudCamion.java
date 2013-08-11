/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.CamionDAO;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.TipoCamionDAO;
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
   public void crearCamion(String patente, String municipalidad, int tipoCamion)throws Exception{
       
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       CamionDAO cdao = dF.getCamionDAO();
       TipoCamionDAO tcdao = dF.getTipoCamionDAO();
       MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
       
       Camion c = cdao.buscarPorPatente(patente);
       
       if(c == null){
           c = new Camion();
           TipoCamion tc = tcdao.find(tipoCamion);
           Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
           c.setPatente(patente);
           c.setIdTc(tc);
           c.setNombreMunicipalidad(m);
           cdao.insert(c);
       }
       else{
           throw new Exception("El camion ya existe");
       }
       
   }
   
   @Override
   public Collection<Camion> listaCamiones(){
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       return dF.getCamionDAO().findAll();
   }

   @Override
   public void editarCamion(String patente, String municipalidad, int tipoCamion) throws Exception{
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       CamionDAO cdao = dF.getCamionDAO();
       MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
       TipoCamionDAO tcdao = dF.getTipoCamionDAO();
       Camion c = cdao.buscarPorPatente(patente);
       
       if(c!=null){

           Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
           TipoCamion tc = tcdao.find(tipoCamion);
           c.setPatente(patente);
           c.setNombreMunicipalidad(m);
           c.setIdTc(tc);
           cdao.update(c);
       }
       else{
           throw new Exception("Camión no registrado");
       }
   }
   
   @Override
   public void eliminarCamion(String patente)throws Exception{
       
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       CamionDAO cdao = dF.getCamionDAO();
       Camion c = cdao.buscarPorPatente(patente);
       
       if(c != null){
           cdao.delete(c);
       }
       else{
           throw new Exception("Camión no registrado");
       }
   }
   
   
}
