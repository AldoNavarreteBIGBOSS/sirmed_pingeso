/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.RegistrosDAO;
import entities.Registro;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class Reporte implements ReporteLocal {
    
    @EJB
    private RegistrosLocal registros;
    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    @Override
    public void generarReporteExcelFecha(String fecha1, String fecha2) throws Exception{
        
        try{
            Collection<Registro> rs = registros.listarRegistroPorFechas(fecha1, fecha2);
            
            
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }

}
