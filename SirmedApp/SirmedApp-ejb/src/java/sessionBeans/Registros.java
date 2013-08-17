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
import entities.TipoRecoleccion;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
    public void crearRegistro(String rutBasculista, String municipalidad, String patenteCamion, String rutChofer, float pesaje, String comentario, Collection<PuntoRecoleccion> puntosSeleccionados) throws Exception {

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

        if (b != null && m != null && c != null && ch != null) {
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
            r.setDetalleRegistro(generarDetalle(puntosSeleccionados));
            rdao.insert(r);
            
            
            
  
        } else {

            throw new Exception("Error al ingresar registro");
        }
    }

    private String generarDetalle(Collection<PuntoRecoleccion> puntosRecoleccion) {

        String concat = "";
        PuntoRecoleccion temp;
        for (PuntoRecoleccion pr : puntosRecoleccion) {
            temp = pr;
            concat += temp.getNombrePunto() + " " + temp.getDireccionPunto() + " " + returnStringCollection(pr.getTipoRecoleccionCollection()) + "\n";
        }
        return concat;

    }

    private String returnStringCollection(Collection<TipoRecoleccion> tipoRecoleccion) {

        String str = "";
        String tmp;
        for (Iterator<TipoRecoleccion> it = tipoRecoleccion.iterator(); it.hasNext();) {
            tmp = it.next().getNombreTipoRecoleccion();
            str += tmp + " - ";
        }
        return str;
    }
    
    @Override
    public String generarInforme(Integer hora){
        
    String informe = "";
    DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
    RegistrosDAO rdao = dF.getRegistrosDAO();
    Date date = new Date();
    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
    
    Collection<Registro> rs = rdao.generarInforme(dt1.format(date), hora.toString());
    
    if(!rs.isEmpty()){
        informe = escribirInforme(rs);
        return informe;
    }
    else{
        return "No hay registros";
    }
    
    
    
    
    }
    
    private String escribirInforme(Collection<Registro> datos){
    
        String data = "";
        Registro temp;
        for(Registro r: datos){
            temp = r;
            data += "N° Registro: "+temp.getIdRegistro()+"\n"+"Basculista: "+temp.getRut().getNombreB()+" "+temp.getRut().getApellidoB()+"\n"+"Chofer: "+temp.getRutChofer().getNombreChofer()+
                    " "+temp.getRutChofer().getApellidoChofer()+"\n"+"Municipalidad: "+temp.getNombreMunicipalidad().getNombreMunicipalidad()+"\n"+"Camión: "+temp.getPatente().getPatente()+"\n"+
                    "Pesaje: "+temp.getPesajeRegistro()+"\n"+"Fecha: "+temp.getFechaRegistro().toString()+"\n"+"Detalle: "+temp.getDetalleRegistro()+"\n"+"Comentarios: "+temp.getComentarioRegistro()+"\n"+
                    "________________________________________________________________________\n\n";
        }
        
        return data;
    }
}
