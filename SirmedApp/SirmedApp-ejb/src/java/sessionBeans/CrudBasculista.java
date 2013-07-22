/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DAO.DAOFactory;
import entities.Basculista;
import entities.TurnoTrabajo;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aldo
 */
@Stateless
public class CrudBasculista implements CrudBasculistaLocal {

    @PersistenceContext(unitName = "SirmedApp-ejbPU")
    private EntityManager em;

    @Override
    public void crearBasculista(String rut, String turno, String nombre, String apellido, String telefono) {
        Basculista b  = new Basculista();
        b.setRut(rut);
        TurnoTrabajo trabajo = new TurnoTrabajo(turno);
        b.setNombreTurno(trabajo);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        b.setTelefonoB(telefono);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().insert(b);
    }
    
    @Override
    public Collection<Basculista> listaBasculistas(){
    
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        
        return dF.getBasculistaDAO().findAll();
    }
    
    @Override
    public void editarBasculista(String rut, String turno, String nombre, String apellido, String telefono){
        Basculista b = new Basculista();
        b.setRut(rut);
        TurnoTrabajo trabajo = new TurnoTrabajo(turno);
        b.setNombreTurno(trabajo);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        b.setTelefonoB(telefono);
        
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().update(b);
    }
    
    @Override
    public void eliminarBasculista(String rut, String turno, String nombre, String apellido, String telefono){
        Basculista b = new Basculista();
        b.setRut(rut);
        TurnoTrabajo trabajo = new TurnoTrabajo(turno);
        b.setNombreTurno(trabajo);
        b.setNombreB(nombre);
        b.setApellidoB(apellido);
        b.setTelefonoB(telefono);
        DAOFactory dF = DAOFactory.getDAOFactory(DAOFactory.MYSQL, em);
        dF.getBasculistaDAO().delete(b);
    }
}
