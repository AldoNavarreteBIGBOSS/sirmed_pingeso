/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.BasculistaDAO;
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
    public void editarBasculista(String rut, String nombre, String apellido, String telefono) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        BasculistaDAO bdao = dF.getBasculistaDAO();
        Basculista b = bdao.buscarPorRut(rut);
        
        if(b != null){
            b.setRut(rut);
            b.setNombreB(nombre);
            b.setApellidoB(apellido);
            b.setTelefonoB(telefono);
            bdao.update(b);
        }
        else{
            
                throw new Exception("No existe el basculista");
           
        }
    }
    
    @Override
    public void eliminarBasculista(String rut) throws Exception{
        
       DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       BasculistaDAO bdao = dF.getBasculistaDAO();
       Basculista b = bdao.buscarPorRut(rut);
       
       if(b != null){
           bdao.delete(b);
       }
       else{
           throw  new Exception("No existe el basculista");
       }
        
        
    }
}
