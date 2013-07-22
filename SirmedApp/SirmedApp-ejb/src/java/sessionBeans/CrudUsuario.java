/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.TipoUsuario;
import entities.Usuario;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class CrudUsuario implements CrudUsuarioLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

    @Override
    public void crearUsuario(String rut, String email) {
        Usuario u = new Usuario();
        u.setRut(rut);
        u.setEmail(email);
        TipoUsuario tipoUsuario = new TipoUsuario(1);
        u.setIdTipo(tipoUsuario);
        try {
            try {
                
                String password = crearPassword(rut);
                u.setPassword(password);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CrudUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CrudUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getUsuarioDAO().insert(u);
        
    }

    private String crearPassword(String rut) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(rut.getBytes("UTF-8"));
        
        byte[] digest = md.digest();
        BigInteger bigInteger = new BigInteger(1, digest);
        return bigInteger.toString(16);
        
    }
    
    @Override
    public void eliminarUsuario(String rut){
    
        Usuario u = new Usuario(rut);
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getUsuarioDAO().delete(u);
    }
    
    

}
