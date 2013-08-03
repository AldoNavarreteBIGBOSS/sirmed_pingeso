/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.PuntoRecoleccion;
import java.util.Collection;
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
    public void crearPuntoRecoleccion(String direccion, String nombrePunto, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        pr.setDireccionPunto(direccion);
        pr.setNombrePunto(nombrePunto);
        pr.setDescripcionPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().insert(pr);
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
