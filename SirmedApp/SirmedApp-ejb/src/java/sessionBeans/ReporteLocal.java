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

   

    public String generarReporteExcelFecha(String fecha1, String fecha2, String nombreArchivo, String municipalidad) throws Exception;

    public String generarReporteExcelTemporada(String año, String temporada, String nombreArchivo, String municipalidad)throws Exception;

    public void registrarReporte(String rut) throws Exception;
    
}
