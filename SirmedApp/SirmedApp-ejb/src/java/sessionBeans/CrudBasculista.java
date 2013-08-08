/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.BasculistaDAO;
import entities.Basculista;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        BasculistaDAO bdao = dF.getBasculistaDAO();
        
        Basculista b = new Basculista();
        b.setRut(rut);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        b.setTelefonoB(telefono);
        
        bdao.insert(b);
        
    }
    
    @Override
    public Collection<Basculista> listaBasculistas(){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        
        return dF.getBasculistaDAO().findAll();
    }
    
    @Override
    public void editarBasculista(String rut, String nombre, String apellido, String telefono){
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        
        Basculista b = dF.getBasculistaDAO().buscarPorRut(rut);
        
        if(b != null){
            b.setNombreB(nombre);
            b.setApellidoB(apellido);
            b.setTelefonoB(telefono);
            dF.getBasculistaDAO().update(b);
        }
        else{
            try {
                throw new Exception("No existe el basculista");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
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
