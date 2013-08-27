/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.AuditoriaDAO;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class Auditoria implements AuditoriaLocal {

    
    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    @Override
    public void registrarAccion(String descripcion, String información) throws Exception{
    
        try{
            DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
            AuditoriaDAO udao = dF.getAuditoriaDAO();
            
            entities.Auditoria a = new entities.Auditoria();
            
            Date date = new Date();
            a.setFechaAuditoria(date);
            a.setInfoAuditoria(información);
            a.setDescripcionAuditoria(descripcion);
            
            udao.insert(a);
        }
        catch(Exception e){
            throw new Exception("Imposible realizar operación");
        }
    
    }
    
    @Override
    public Collection<entities.Auditoria> listarAcciones(String fecha){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        AuditoriaDAO adao = dF.getAuditoriaDAO();
        
        Collection<entities.Auditoria> la = adao.listarAuditoriaPorFecha(fecha);
        
        if(!la.isEmpty()){
            return la;
        }
        else{
            return null;
        }
        
        
    }
}
