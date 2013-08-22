/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Local;

/**
 *
 * @author Aldo
 */
@Local
public interface ReporteLocal {

   

    public String generarReporteExcelFecha(String fecha1, String fecha2, String nombreArchivo) throws Exception;
    
}
