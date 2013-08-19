/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.JefePlantaDAO;
import DAO_interfaces.UsuarioDAO;
import entities.JefePlanta;
import entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudJefePlanta implements CrudJefePlantaLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    @Override
    public JefePlanta entregarPorRut(String rut)throws Exception{
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        JefePlantaDAO jpdao = dF.getJefePlantaDAO();
        JefePlanta jp = jpdao.buscarPorRut(rut);
        
        if(jp != null){
            
            return jp;
            
        }
        else{
            throw new Exception("Error");
        }
        
        
        
    }
    
    @Override
    public void actualizarDatosPersonales(String rut, String nombre, String apellido, String telefono, String email)throws Exception{
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        JefePlantaDAO jpdao = dF.getJefePlantaDAO();
        UsuarioDAO udao = dF.getUsuarioDAO();
        
        JefePlanta jp = jpdao.buscarPorRut(rut);
        Usuario u = udao.buscarPorRut(rut);
        
        if(jp!=null && u!=null){
            jp.setNombreJp(nombre);
            jp.setApellidoJp(apellido);
            jp.setTelefonoJp(telefono);
            u.setMail(email);
            
            jpdao.update(jp);
            udao.update(u);
        
        }
        else{
            throw new Exception("Error al actualizar");
        }
        
    
    }

}
