/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Registro;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
    public String generarReporteExcelFecha(String fecha1, String fecha2, String nombreArchivo, String municipalidad) throws Exception {

        try {
            Collection<Registro> rs;
            if(municipalidad == null){
                rs = registros.listarRegistroPorFechas(fecha1, fecha2);
               
            }
            else{
                rs = registros.listarRegistroPorFechasMunicipalidad(fecha1, fecha2, municipalidad);
            }
            Integer count = 1;
            HSSFWorkbook libroReporte = new HSSFWorkbook();
            HSSFSheet hojaReporte = libroReporte.createSheet();
            HSSFCellStyle style = libroReporte.createCellStyle();

            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFRow cfilaReporteCabecera = hojaReporte.createRow(0);
            HSSFCell Id = cfilaReporteCabecera.createCell(0);
            HSSFCell Muni = cfilaReporteCabecera.createCell(1);
            HSSFCell Bas = cfilaReporteCabecera.createCell(2);
            HSSFCell Chf = cfilaReporteCabecera.createCell(3);
            HSSFCell Cam = cfilaReporteCabecera.createCell(4);
            HSSFCell Pso = cfilaReporteCabecera.createCell(5);
            HSSFCell Dte = cfilaReporteCabecera.createCell(6);
            HSSFCell Det = cfilaReporteCabecera.createCell(7);

            Id.setCellValue("N° REGISTRO");
            Muni.setCellValue("MUNICIPALIDAD");
            Bas.setCellValue("BASCULISTA");
            Chf.setCellValue("CHOFER");
            Cam.setCellValue("PATENTE CAMIÓN");
            Pso.setCellValue("PESAJE");
            Dte.setCellValue("FECHA REGISTRO");
            Det.setCellValue("DETALLE REGISTRO");



            for (Registro r : rs) {

                HSSFRow filaReporteCabecera = hojaReporte.createRow(count);
                HSSFCell cId = filaReporteCabecera.createCell(0);
                HSSFCell cMuni = filaReporteCabecera.createCell(1);
                HSSFCell cBas = filaReporteCabecera.createCell(2);
                HSSFCell cChf = filaReporteCabecera.createCell(3);
                HSSFCell cCam = filaReporteCabecera.createCell(4);
                HSSFCell cPso = filaReporteCabecera.createCell(5);
                HSSFCell cDte = filaReporteCabecera.createCell(6);
                HSSFCell cDet = filaReporteCabecera.createCell(7);

                cId.setCellValue(r.getIdRegistro());
                cId.setCellStyle(style);
                cMuni.setCellValue(r.getNombreMunicipalidad().getNombreMunicipalidad());
                cMuni.setCellStyle(style);
                cBas.setCellValue(r.getRut().getNombreB() + " " + r.getRut().getApellidoB());
                cChf.setCellValue(r.getRutChofer().getNombreChofer() + " " + r.getRutChofer().getApellidoChofer());
                cCam.setCellValue(r.getPatente().getPatente());
                cCam.setCellStyle(style);
                cPso.setCellValue(r.getPesajeRegistro());
                cPso.setCellStyle(style);
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                cDte.setCellValue(df.format(r.getFechaRegistro()));
                cDte.setCellStyle(style);
                cDet.setCellValue(r.getDetalleRegistro());
               count++;
            }

            for (Integer i = 0; i <= 7; i++) {
                hojaReporte.autoSizeColumn(i);
            }
            
            
            
            try {

                String path = System.getProperty("java.home");
                System.out.println(path);
                path = path + File.separator + "reportes_sirmed" + File.separator;
                File carpetaUploads = new File(path);
                if (carpetaUploads.mkdir()) {
                    System.out.println("Creado el directorio para almacenar los uploads: " + path);
                }

                String url = path + nombreArchivo + Calendar.getInstance().getTimeInMillis() + ".xls";
                FileOutputStream archivo = new FileOutputStream(url);

                libroReporte.write(archivo);
                archivo.close();

                return url;

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }



        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
