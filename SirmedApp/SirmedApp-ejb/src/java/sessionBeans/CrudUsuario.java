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
                u.setHabilitado(true);
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
       b.setHabilitado(false);
       
       if(b != null){
           udao.update(b);
       }
       else{
           throw  new Exception("No existe el basculista");
       }
    }
    
    @Override
    public void actualizarUsuario(String rut, String newPassword) throws Exception{
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

    @Override
    public Usuario entregarPorRut(String rut) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO jpdao = dF.getUsuarioDAO();
        Usuario jp = jpdao.buscarPorRut(rut);
        
        if(jp != null){
            
            return jp;
        }
        else{
            throw new Exception("Error");
        }
    
    }
    
    @Override
    public boolean analizarContraseña(String rut, String oldPass) throws Exception{
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO udao = dF.getUsuarioDAO();
        
        Usuario u = udao.buscarPorRut(rut);
        
        if(u != null){
            String codePass = crearPassword(oldPass);
           
            if(codePass.compareTo(u.getPassword())==0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new Exception("El usuario no existe");
        }
        
    }
    
    @Override
    public boolean analizarHabilitado(String rut)throws Exception{
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        UsuarioDAO udao = dF.getUsuarioDAO();
        
        try{
            if(udao.estarHabilitado(rut)==true){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    
    }
    
    @Override
    public String entregarNombre(String rut)throws Exception{
        
        String nombre = null;
        
        Usuario u = entregarPorRut(rut);
        
        if(u.getIdTipo().getIdTipo() == 1){
            nombre = u.getBasculista().getNombreB() +" "+u.getBasculista().getApellidoB();
        }
        else{
            nombre = u.getJefePlanta().getNombreJp() +" "+ u.getJefePlanta().getApellidoJp();
        }
        
        return nombre;
    }
}
