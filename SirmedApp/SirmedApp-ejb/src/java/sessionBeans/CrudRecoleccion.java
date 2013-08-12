/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.PuntoRecoleccionDAO;
import DAO_interfaces.RegistrosDAO;
import entities.Municipalidad;
import entities.PuntoRecoleccion;
import entities.Registro;
import entities.TipoRecoleccion;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudRecoleccion implements CrudRecoleccionLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    
    @Override
    public void crearPuntoRecoleccion(String direccion, String nombrePunto, String descripcion, String municipalidad, Collection<TipoRecoleccion> tipoRecoleccion) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        PuntoRecoleccionDAO prdao = dF.getPuntoRecoleccionDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        
        PuntoRecoleccion pr = prdao.buscarPorDireccionLike(direccion);
        
        
        if(pr == null){
            
           
            
            pr = new PuntoRecoleccion();
            
            Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
            
            
            
            pr.setNombrePunto(nombrePunto);
            
            pr.setDireccionPunto(direccion);
           
            pr.setNombreMunicipalidad(m);
            
            pr.setTipoRecoleccionCollection(tipoRecoleccion);
            
            prdao.insert(pr);
            
        }
        else{
            throw new Exception("Punto de recolección ya existe");
        }
       
        
      
        
        
        
        
        
    }
    
    @Override
    public Collection<PuntoRecoleccion> listaPuntosRecoleccion(){
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        return dF.getPuntoRecoleccionDAO().findAll();
    }
    
    @Override
    public void editarPuntoRecoleccion(String direccion, String nombrePunto, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        pr.setDireccionPunto(direccion);
        pr.setNombrePunto(nombrePunto);
        pr.setDescripcionPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().update(pr);
    }
    
    @Override
    public void eliminarPuntoRecoleccion(String direccion, String nombrePunto, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        pr.setDireccionPunto(direccion);
        pr.setNombrePunto(nombrePunto);
        pr.setDescripcionPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().delete(pr);
    }

}
