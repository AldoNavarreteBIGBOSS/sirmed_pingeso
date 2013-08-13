/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import DAO_interfaces.BasculistaDAO;
import DAO_interfaces.CamionDAO;
import DAO_interfaces.ChoferDAO;
import DAO_interfaces.MunicipalidadDAO;
import DAO_interfaces.RegistrosDAO;
import entities.Basculista;
import entities.Camion;
import entities.Chofer;
import entities.Municipalidad;
import entities.PuntoRecoleccion;
import entities.Registro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class Registros implements RegistrosLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;
    
    @Override
    public void crearRegistro(String rutBasculista, String municipalidad, String patenteCamion, String rutChofer, float pesaje, String comentario, Collection<PuntoRecoleccion> puntosSeleccionados) throws Exception{
        System.out.println(rutBasculista+" "+municipalidad+" "+patenteCamion);
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        RegistrosDAO rdao = dF.getRegistrosDAO();
        BasculistaDAO bdao = dF.getBasculistaDAO();
        MunicipalidadDAO mdao = dF.getMunicipalidadDAO();
        CamionDAO cdao = dF.getCamionDAO();
        ChoferDAO chdao = dF.getChoferDAO();
        
        Basculista b = bdao.buscarPorRut(rutBasculista);
        Municipalidad m = mdao.buscarPorMunicipalidad(municipalidad);
        Camion c = cdao.buscarPorPatente(patenteCamion);
        Chofer ch = chdao.buscarPorRut(rutChofer);
        
        if(b != null && m !=null && c != null && ch != null){
            Registro r = new Registro();
            r.setRut(b);
            r.setNombreMunicipalidad(m);
            r.setPatente(c);
            r.setRutChofer(ch);
            
            Date date = new Date();
            
            r.setFechaRegistro(date);
            
            r.setComentarioRegistro(comentario);
            
            r.setPesajeRegistro(pesaje);
            
            r.setDetalleRegistro(generarDetalle(puntosSeleccionados));
            
            rdao.insert(r);
            
            
        
        }
        else{
            System.out.println("CACA");
            throw new Exception("Error al ingresar registro");
        }
        
        
        
    
    }
    
    private String generarDetalle(Collection<PuntoRecoleccion> puntosRecoleccion){
        
        String detalle = null;
         String concat = null;
        for(PuntoRecoleccion pr: puntosRecoleccion){
            concat = detalle.concat(pr.getNombrePunto()+" "+pr.getDireccionPunto()+" "+pr.getNombreMunicipalidad().getNombreMunicipalidad()+", ");
            
        }
        return concat;
        
    }

}
