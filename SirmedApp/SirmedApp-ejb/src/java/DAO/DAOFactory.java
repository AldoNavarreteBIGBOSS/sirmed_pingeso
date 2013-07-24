/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_interfaces.BasculistaDAO;
import DAO_interfaces.CamionDAO;
import DAO_interfaces.ChoferDAO;
import DAO_interfaces.JefePlantaDAO;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.PuntoRecoleccionDAO;
import DAO_interfaces.RegistrosDAO;
import DAO_interfaces.ReportesDAO;
import DAO_interfaces.TipoRecoleccionDAO;
import DAO_interfaces.TurnoTrabajoDAO;
import DAO_interfaces.UsuarioDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public abstract class DAOFactory {
    
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;
    
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract BasculistaDAO getBasculistaDAO();
    public abstract CamionDAO getCamionDAO();
    public abstract ChoferDAO getChoferDAO();
    public abstract JefePlantaDAO getJefePlantaDAO();
    public abstract PuntoRecoleccionDAO getPuntoRecoleccionDAO();
    public abstract RegistrosDAO getRegistrosDAO();
    public abstract ReportesDAO getReportesDAO();
    public abstract TurnoTrabajoDAO getTurnoTrabajoDAO();
    public abstract MunicipalidadDAO getMunicipalidadDAO();
    public abstract TipoRecoleccionDAO getTipoRecoleccionDAO();
    
    public static DAOFactory getDAOFactory(int whichFactory, EntityManager em){
    
        switch(whichFactory){
            
            case MYSQL:
                    return new MysqlDAOFactory(em);
             
                    default: return null;
        
        }
    
    }
}
