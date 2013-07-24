/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.Municipalidad;
import entities.PuntoRecoleccion;
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
public class CrudRecoleccion implements CrudRecoleccionLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    
    @Override
    public void crearPuntoRecoleccion(String direccion, String municipalidad, String nombreTipo, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        Municipalidad m = new Municipalidad(municipalidad);
        TipoRecoleccion tr = new TipoRecoleccion(nombreTipo);
        pr.setDireccionPunto(direccion);
        pr.setNombreMunicipalidad(m);
        pr.setNombreTipoRecoleccion(tr);
        pr.setDescrpPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().insert(pr);
    }
    
    @Override
    public Collection<PuntoRecoleccion> listaPuntosRecoleccion(){
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        return dF.getPuntoRecoleccionDAO().findAll();
    }
    
    @Override
    public void editarPuntoRecoleccion(String direccion, String municipalidad, String nombreTipo, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        Municipalidad m = new Municipalidad(municipalidad);
        TipoRecoleccion tr = new TipoRecoleccion(nombreTipo);
        pr.setDireccionPunto(direccion);
        pr.setNombreMunicipalidad(m);
        pr.setNombreTipoRecoleccion(tr);
        pr.setDescrpPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().update(pr);
    }
    
    @Override
    public void eliminarPuntoRecoleccion(String direccion, String municipalidad, String nombreTipo, String descripcion){
        PuntoRecoleccion pr = new PuntoRecoleccion();
        Municipalidad m = new Municipalidad(municipalidad);
        TipoRecoleccion tr = new TipoRecoleccion(nombreTipo);
        pr.setDireccionPunto(direccion);
        pr.setNombreMunicipalidad(m);
        pr.setNombreTipoRecoleccion(tr);
        pr.setDescrpPunto(descripcion);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getPuntoRecoleccionDAO().delete(pr);
    }

}
