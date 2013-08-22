/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Registro;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    public String generarReporteExcelFecha(String fecha1, String fecha2, String nombreArchivo) throws Exception{
        
        try{
            Collection<Registro> rs = registros.listarRegistroPorFechas(fecha1, fecha2);
            
            HSSFWorkbook libroReporte = new HSSFWorkbook();
            HSSFSheet hojaReporte = libroReporte.createSheet();
            
            HSSFRow filaReporteCabecera = hojaReporte.createRow(4);
            
            HSSFCell celdaUno = filaReporteCabecera.createCell(0);
            HSSFCell celdaDos = filaReporteCabecera.createCell(1);
            HSSFCell celdaTres = filaReporteCabecera.createCell(2);
            
           
            
            celdaUno.setCellValue("textoUno");
            celdaDos.setCellValue("textoDos");
            celdaTres.setCellValue("textoTres");
            
            try{
                
                String path =System.getProperty("java.home");
                System.out.println(path);
                path = path + File.separator + "reportes_sirmed" + File.separator;
                File carpetaUploads = new File(path);
                if (carpetaUploads.mkdir()) {
                    System.out.println("Creado el directorio para almacenar los uploads: " + path);
                }
                
                String url = path+nombreArchivo+Calendar.getInstance().getTimeInMillis()+".xls";
                FileOutputStream archivo = new FileOutputStream(url);
                
                libroReporte.write(archivo);
                archivo.close();
                
                return url;
                
            }
            catch(Exception e){
                throw new Exception(e.getMessage());
            }
            
            
            
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }

}
