/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.PuntoRecoleccionDAO;
import DAO_interfaces.TipoRecoleccionDAO;
import entities.Municipalidad;
import entities.PuntoRecoleccion;
import entities.TipoRecoleccion;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
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
    public void crearPuntoRecoleccion(String direccion, String nombrePunto, String descripcion, String municipalidad, Collection<String> tipoRecoleccion) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        PuntoRecoleccionDAO prdao = dF.getPuntoRecoleccionDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        PuntoRecoleccion pr = prdao.buscarPorDireccionLike(direccion);
                
        if(pr == null){          
            pr = new PuntoRecoleccion();
            
            Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
            Collection<TipoRecoleccion> ctr = buscaTipoRecolecciones(tipoRecoleccion);
            
            pr.getTipoRecoleccionCollection().addAll(ctr);
            pr.setNombrePunto(nombrePunto);          
            pr.setDireccionPunto(direccion);           
            pr.setNombreMunicipalidad(m);
            pr.setDescripcionPunto(descripcion);
            for (TipoRecoleccion tRec : ctr) {
                tRec.getPuntoRecoleccionCollection().add(pr);
            }
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
    public void editarPuntoRecoleccion(Integer idTipo, String direccion, String nombrePunto, String descripcion, String municipalidad, Collection<String> tipoRecoleccion) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        PuntoRecoleccionDAO prdao = dF.getPuntoRecoleccionDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        PuntoRecoleccion pr = prdao.find(idTipo);
        
        
       
        if(pr != null){
           
            Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
            Collection<TipoRecoleccion> ctr = buscaTipoRecolecciones(tipoRecoleccion);
            for (TipoRecoleccion tRec : pr.getTipoRecoleccionCollection()) {
                tRec.getPuntoRecoleccionCollection().remove(pr);
            }
            pr.getTipoRecoleccionCollection().clear();
            pr.getTipoRecoleccionCollection().addAll(ctr);
            pr.setNombrePunto(nombrePunto);
            pr.setDireccionPunto(direccion);
            pr.setDescripcionPunto(descripcion);
            pr.setNombreMunicipalidad(m);
            for (TipoRecoleccion tRec : ctr) {
                tRec.getPuntoRecoleccionCollection().add(pr);
            }
            prdao.update(pr);
            
        }
        else{
            
            throw new Exception("Punto de recolección ya existe");
        }
    }
    
    @Override
    public void eliminarPuntoRecoleccion(Integer idTipo) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        PuntoRecoleccionDAO prdao = dF.getPuntoRecoleccionDAO();
        PuntoRecoleccion pr = prdao.find(idTipo);
         
        if(pr != null){
        
            for (TipoRecoleccion tRec : pr.getTipoRecoleccionCollection()) {
                tRec.getPuntoRecoleccionCollection().remove(pr);
            }
            pr.getTipoRecoleccionCollection().clear();
            prdao.delete(pr);
            
        }
        else{
            throw  new  Exception("No existe punto de recoleccion");
        }
    }

    private Collection<TipoRecoleccion> buscaTipoRecolecciones(Collection<String> tiposRecolecciones){
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        TipoRecoleccionDAO tdao = dF.getTipoRecoleccionDAO();
        TipoRecoleccion tmp;
        LinkedList<TipoRecoleccion> resultado = new LinkedList();
        Integer i;
        String i_str;
        for (Iterator<String> it = tiposRecolecciones.iterator(); it.hasNext();) {
            i_str = it.next();
            try {
                i = Integer.parseInt(i_str);
                tmp = tdao.find(i);
                if (tmp != null) {
                    resultado.add(tmp);
                }
            } catch (NumberFormatException n) {
            }


        }

        return resultado;
        
    
    }
    
    @Override
    public Collection<PuntoRecoleccion> listarPuntoRecoleccionMunicipalidad(String municipalidad){
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        PuntoRecoleccionDAO cdao = dF.getPuntoRecoleccionDAO();
        
        return cdao.buscarPorMunicipalidad(municipalidad);
    
    }
}
