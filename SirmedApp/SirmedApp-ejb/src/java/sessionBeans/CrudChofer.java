/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.ChoferDAO;
import DAO_interfaces.MunicipalidadDAO;
import entities.Chofer;
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
public class CrudChofer implements CrudChoferLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    
    @Override
    public void crearChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        ChoferDAO cdao = dF.getChoferDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        
        Chofer c = cdao.buscarPorRut(rut);
        
        if(c == null){
            c = new Chofer();
            Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
            c.setRutChofer(rut);
            c.setNombreChofer(nombre);
            c.setApellidoChofer(apellido);
            c.setNombreMunicipalidad(m);
            c.setMailChofer(email);
            c.setTelefonoChofer(telefono);
            cdao.insert(c);
        }
        else{
            throw new Exception("El chofer ya existe");
        }
    
    }

    @Override
    public Collection<Chofer> listaChoferes(){
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        return dF.getChoferDAO().findAll();
    }
    
    @Override
    public void editarChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad) throws Exception{
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        ChoferDAO cdao = dF.getChoferDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        
        Chofer c = cdao.buscarPorRut(rut);
        
        if(c!=null){
            Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
            c.setRutChofer(rut);
            c.setNombreChofer(nombre);
            c.setApellidoChofer(apellido);
            c.setTelefonoChofer(telefono);
            c.setMailChofer(email);
            c.setNombreMunicipalidad(m);
            cdao.update(c);
        
        }
        else{
            throw new Exception("No existe el chofer");
        }
        
    }
    
    @Override
    public void eliminarChofer(String rut) throws Exception{
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        ChoferDAO cdao = dF.getChoferDAO();
        
        Chofer c = cdao.buscarPorRut(rut);
        
        if(c != null){
            cdao.delete(c);
        }
        else{
            throw new Exception("No existe el chofer");
        }
        
    
    }
}
