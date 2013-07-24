/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
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
    public void crearChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad){
        
        Chofer c = new  Chofer();
        Municipalidad m = new Municipalidad(municipalidad);
        c.setRutChofer(rut);
        c.setNombreChofer(nombre);
        c.setApellidoChofer(apellido);
        c.setTelefonoChofer(telefono);
        c.setEmailChofer(email);
        c.setNombreMunicipalidad(m);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getChoferDAO().insert(c);
    
    }

    @Override
    public Collection<Chofer> listaChoferes(){
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        return dF.getChoferDAO().findAll();
    }
    
    @Override
    public void editarChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad){
    
        Chofer c = new  Chofer();
        Municipalidad m = new Municipalidad(municipalidad);
        c.setRutChofer(rut);
        c.setNombreChofer(nombre);
        c.setApellidoChofer(apellido);
        c.setTelefonoChofer(telefono);
        c.setEmailChofer(email);
        c.setNombreMunicipalidad(m);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getChoferDAO().update(c);
    }
    
    @Override
    public void eliminarChofer(String rut, String nombre, String apellido, String telefono, String email, String municipalidad){
    
        Chofer c = new  Chofer();
        Municipalidad m = new Municipalidad(municipalidad);
        c.setRutChofer(rut);
        c.setNombreChofer(nombre);
        c.setApellidoChofer(apellido);
        c.setTelefonoChofer(telefono);
        c.setEmailChofer(email);
        c.setNombreMunicipalidad(m);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getChoferDAO().delete(c);
    
    }
}
