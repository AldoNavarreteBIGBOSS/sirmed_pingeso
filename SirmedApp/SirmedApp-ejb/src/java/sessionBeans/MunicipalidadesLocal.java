/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Municipalidad;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface MunicipalidadesLocal {

    public Collection<Municipalidad> listaMunicipalidades();
    
}
