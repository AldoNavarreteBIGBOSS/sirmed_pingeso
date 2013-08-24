/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.BasculistaDAO;
import DAO_interfaces.UsuarioDAO;
import entities.Basculista;
import entities.Usuario;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
        b.setHabilitado(true);
        
                
        bdao.insert(b);      
        
        
    }
    
    @Override
    public Collection<Basculista> listaBasculistas(){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        Collection<Basculista> cb = dF.getBasculistaDAO().findAll();
        Collection<Basculista> cbE = new LinkedList<Basculista>();
        
        for(Basculista b: cb){
            if(b.getHabilitado() == false){
                cbE.add(b);
            }
        }
        
        cb.removeAll(cbE);
        
        return cb;
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
       b.setHabilitado(false);
       
       if(b != null){
           bdao.update(b);
       }
       else{
           throw  new Exception("No existe el basculista");
       }
        
        
    }
    
    @Override
    public void activarBasculistaUsuario(String rut)throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        BasculistaDAO bdao = dF.getBasculistaDAO();
        UsuarioDAO udao = dF.getUsuarioDAO();
        
        Usuario u = udao.buscarPorRut(rut);
        Basculista b = bdao.buscarPorRut(rut);
        
        
        if(u != null && b!= null){
            if(u.getHabilitado() == false && b.getHabilitado()==false){
                
                b.setHabilitado(true);
                u.setHabilitado(true);
                
                bdao.update(b);
                udao.update(u);
            }
            else{
                throw new  Exception("El basculista se encuentra habilitado");
            }
        }
        else{
            throw new  Exception("El basculista no existe");
        }
        
    
    }
}
