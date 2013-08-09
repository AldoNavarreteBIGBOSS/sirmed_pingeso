/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.TipoUsuarioDAO;
import DAO_interfaces.UsuarioDAO;
import entities.TipoUsuario;
import entities.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudUsuario implements CrudUsuarioLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

    @Override
    public void crearUsuario(String rut, String email)throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO udao = dF.getUsuarioDAO();
        TipoUsuarioDAO tudao = dF.getTipoUsuarioDAO(); 
        Usuario u = udao.buscarPorRut(rut);
        
        if(u==null){
            u = new Usuario();
            String password = crearPassword(rut);  
            TipoUsuario tipoUsuario = tudao.find(1);
            if(password != null){
                u.setRut(rut);
                u.setMail(email);
                u.setPassword(password);
                u.setIdTipo(tipoUsuario);
                udao.insert(u);
            }
            else{
                throw  new Exception("Error en generar password");
            }
        }
        else{
            throw  new Exception("Usuario ya existe");
        }
    }

    private String crearPassword(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInteger = new BigInteger(1, digest);
            return bigInteger.toString(16);
        } catch (Exception e) {
            return null;
        }
    }
         
    @Override
    public void eliminarUsuario(String rut) throws Exception{
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
       UsuarioDAO udao = dF.getUsuarioDAO();
       Usuario b = udao.buscarPorRut(rut);
       
       if(b != null){
           udao.delete(b);
       }
       else{
           throw  new Exception("No existe el basculista");
       }
    }
    
    @Override
    public void actualizarUsuario(String rut, String email, String newPassword) throws Exception{
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO udao = dF.getUsuarioDAO();
        Usuario b = udao.buscarPorRut(rut);
        
        if(b!=null){
            b.setPassword(crearPassword(newPassword));
            udao.update(b);
        }
        else{
            throw  new Exception("Usuario no existe");
        }
    }

}
