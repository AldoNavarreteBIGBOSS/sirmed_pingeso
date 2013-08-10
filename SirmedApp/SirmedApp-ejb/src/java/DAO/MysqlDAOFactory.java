/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_impl.AuditoriaDAO_impl;
import DAO_impl.BasculistaDAO_impl;
import DAO_impl.CamionDAO_impl;
import DAO_impl.ChoferDAO_impl;
import DAO_impl.JefePlantaDAO_impl;
import DAO_impl.MunicipalidadDAO_impl;
import DAO_impl.PuntoRecoleccionDAO_impl;
import DAO_impl.RegistrosDAO_impl;
import DAO_impl.ReportesDAO_impl;
import DAO_impl.TipoCamionDAO_impl;
import DAO_impl.TipoRecoleccionDAO_impl;
import DAO_impl.TipoUsuarioDAO_impl;
import DAO_impl.UsuarioDAO_impl;
import DAO_interfaces.AuditoriaDAO;
import DAO_interfaces.BasculistaDAO;
import DAO_interfaces.CamionDAO;
import DAO_interfaces.ChoferDAO;
import DAO_interfaces.JefePlantaDAO;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.PuntoRecoleccionDAO;
import DAO_interfaces.RegistrosDAO;
import DAO_interfaces.ReportesDAO;
import DAO_interfaces.TipoCamionDAO;
import DAO_interfaces.TipoRecoleccionDAO;
import DAO_interfaces.TipoUsuarioDAO;
import DAO_interfaces.UsuarioDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public class MysqlDAOFactory extends DAOFactory {
    
    private EntityManager em;

    public MysqlDAOFactory(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAO_impl(em);
    }
    
    @Override
    public BasculistaDAO getBasculistaDAO(){
        return new BasculistaDAO_impl(em);
    }

    @Override
    public CamionDAO getCamionDAO() {
        return new CamionDAO_impl(em);
    }

    @Override
    public ChoferDAO getChoferDAO() {
        return new ChoferDAO_impl(em);
    }

    @Override
    public JefePlantaDAO getJefePlantaDAO() {
        return new JefePlantaDAO_impl(em);
    }

    @Override
    public PuntoRecoleccionDAO getPuntoRecoleccionDAO() {
        return new PuntoRecoleccionDAO_impl(em);
    }

    @Override
    public RegistrosDAO getRegistrosDAO() {
        return new RegistrosDAO_impl(em);
    }

    @Override
    public ReportesDAO getReportesDAO() {
        return new ReportesDAO_impl(em);
    }
    
 
    
    @Override
        public MunicipalidadDAO getMunicipalidadDAO() {
        return new MunicipalidadDAO_impl(em);
    }
    
    @Override
        public TipoRecoleccionDAO getTipoRecoleccionDAO() {
        return new TipoRecoleccionDAO_impl(em);
    }
    
    @Override
        public TipoCamionDAO getTipoCamionDAO() {
        return new TipoCamionDAO_impl(em);
    }
    
    @Override
        public TipoUsuarioDAO getTipoUsuarioDAO() {
        return new TipoUsuarioDAO_impl(em);
    }
    
    @Override
        public AuditoriaDAO getAuditoriaDAO() {
        return new AuditoriaDAO_impl(em);
    }
}
