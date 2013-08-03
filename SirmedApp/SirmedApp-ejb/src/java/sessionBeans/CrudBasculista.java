/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.Basculista;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudBasculista implements CrudBasculistaLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

    @Override
    public void crearBasculista(String rut, String nombre, String apellido, String telefono) {
        Basculista b = new Basculista();
        b.setRut(rut);
        b.setTelefonoB(telefono);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);

        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().insert(b);
    }
    
    @Override
    public Collection<Basculista> listaBasculistas(){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        
        return dF.getBasculistaDAO().findAll();
    }
    
    @Override
    public void editarBasculista(String rut, String nombre, String apellido, String telefono){
        
        Basculista b = new Basculista();
        b.setRut(rut);
        b.setTelefonoB(telefono);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().update(b);
    }
    
    @Override
    public void eliminarBasculista(String rut, String nombre, String apellido, String telefono){
        
        Basculista b = new Basculista();
        b.setRut(rut);
        b.setTelefonoB(telefono);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().delete(b);
    }
}
