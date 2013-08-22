/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import managedBeans.Pojo.ListasComboPojo;
import entities.Municipalidad;
import entities.Registro;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import managedBeans.Pojo.RegistroPojo;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sessionBeans.MunicipalidadesLocal;
import sessionBeans.RegistrosLocal;
import sessionBeans.ReporteLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mReportes")
@RequestScoped
public class MReportes implements Serializable{
    @EJB
    private MunicipalidadesLocal municipalidades;
    @EJB
    private ReporteLocal reporte;
    @EJB
    private RegistrosLocal registros;
    @Inject
    private MAutentificador autentificador;
   
    
    private String rutJP;
    private Date fechaInicio;
    private Date fechaFin;
    private List<RegistroPojo> listaRegistros;
    private Collection<Municipalidad> listaMunicipalidades;
    private List<ListasComboPojo> listaTemporada;
    private List<ListasComboPojo> listaAño;
    private MMessaegeController mc;
    private MAccionesGenerales ag;
    private String filtroMunicipalidad;
    private String filtroMunicipalidad2;
    private String filtroTemporada;
    private String filtroAño;
    private String nombreArchivo;
    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }    
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public String getFiltroMunicipalidad2() {
        return filtroMunicipalidad2;
    }
    
    public void setFiltroMunicipalidad2(String filtroMunicipalidad2) {
        this.filtroMunicipalidad2 = filtroMunicipalidad2;
    }   
    
    public String getFiltroAño() {
        return filtroAño;
    }

    public void setFiltroAño(String filtroAño) {
        this.filtroAño = filtroAño;
    }
    
    public Collection<Municipalidad> getListaMunicipalidades() {
        return listaMunicipalidades;
    }

    public void setListaMunicipalidades(Collection<Municipalidad> listaMunicipalidades) {
        this.listaMunicipalidades = listaMunicipalidades;
    }
    
    public String getFiltroMunicipalidad() {
        return filtroMunicipalidad;
    }

    public void setFiltroMunicipalidad(String filtroMunicipalidad) {
        this.filtroMunicipalidad = filtroMunicipalidad;
    }

    public String getFiltroTemporada() {
        return filtroTemporada;
    }

    public void setFiltroTemporada(String filtroTemporada) {
        this.filtroTemporada = filtroTemporada;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<RegistroPojo> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<RegistroPojo> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public List<ListasComboPojo> getListaTemporada() {
        return listaTemporada;
    }

    public void setListaTemporada(List<ListasComboPojo> listaTemporada) {
        this.listaTemporada = listaTemporada;
    }

    public List<ListasComboPojo> getListaAño() {
        return listaAño;
    }

    public void setListaAño(List<ListasComboPojo> listaAño) {
        this.listaAño = listaAño;
    }
    
    @PostConstruct
    public void init(){
       
        mc = new MMessaegeController();
        ag = new MAccionesGenerales();
        rutJP = autentificador.getUsername();
        listaMunicipalidades = municipalidades.listaMunicipalidades();
        cargarListas();
    }
    
    public void obtenerRegistros(){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String f1 = df.format(fechaInicio);
        String f2 = df.format(fechaFin);
        
       try{
           
           if(filtroMunicipalidad == null){
           Collection<Registro> cr = registros.listarRegistroPorFechas(f1, f2);
           cargarDatos(cr);
           
           }
           else{
           Collection<Registro> cr = registros.listarRegistroPorFechasMunicipalidad(f1, f2, filtroMunicipalidad);
           cargarDatos(cr);
           
           }
       }
       catch(Exception e){
           mc.mensajeRetroalimentacion("Error", e.getMessage());
       }
    }
    
    public void cargarDatos(Collection<Registro> registrosObtenidos){
    
        listaRegistros = new LinkedList<RegistroPojo>();
        Registro temp = new Registro();
        for(Registro r: registrosObtenidos){
            temp = r;
            RegistroPojo rpojo = new RegistroPojo();
            rpojo.setIdRegistro(temp.getIdRegistro());
            rpojo.setMunicipalidad(temp.getNombreMunicipalidad().getNombreMunicipalidad());
            rpojo.setBasculista(temp.getRut().getNombreB()+" "+temp.getRut().getApellidoB());
            rpojo.setChofer(temp.getRutChofer().getNombreChofer()+" "+temp.getRutChofer().getApellidoChofer());
            rpojo.setPatenteCamion(temp.getPatente().getPatente());
            rpojo.setPesajeCamion(temp.getPesajeRegistro());
            rpojo.setFechaRegistro(temp.getFechaRegistro());
            listaRegistros.add(rpojo);
        }
        
    }
    
    public void cargarListas(){
        listaTemporada = new LinkedList<ListasComboPojo>();
        ListasComboPojo verano = new ListasComboPojo("verano", "Verano");
        ListasComboPojo invierno = new ListasComboPojo("invierno", "Invierno");
        ListasComboPojo primavera = new ListasComboPojo("primavera", "Primavera");
        ListasComboPojo otoño = new ListasComboPojo("otoño", "Otoño");
        listaTemporada.add(otoño);
        listaTemporada.add(primavera);
        listaTemporada.add(invierno);
        listaTemporada.add(verano);
        
        listaAño = new LinkedList<ListasComboPojo>();
        for(Integer i = 13; i <= 30; i++){
            ListasComboPojo lh = new ListasComboPojo();
            lh.setEtiqueta("20"+i.toString());
            lh.setValor("20"+i.toString());
            listaAño.add(lh);
        }
    }
    
    public void obtenerRegistrosTemporada(){
     
        try{
            Collection<Registro> cr = registros.listarRegistroPorTemporada(filtroAño, filtroTemporada, filtroMunicipalidad2);
            cargarDatos(cr);
        }
        catch(Exception e){
            mc.mensajeRetroalimentacion("Error", e.getMessage());
        }
    }
    
    public void generarReporteFechasDescarga(){
        
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String f1 = df.format(fechaInicio);
        String f2 = df.format(fechaFin);
        String url = null;
        
        try{      
            if(filtroMunicipalidad == null){
                url =  reporte.generarReporteExcelFecha(f1, f2, nombreArchivo);
                InputStream stream = this.getClass().getResourceAsStream(url);
                file = new DefaultStreamedContent(stream, "application/xls", nombreArchivo);               
            }
            else{
                System.out.println(f1+" "+f2+" "+filtroMunicipalidad);
            }
        }
        catch(Exception e){}
    }
    
    public MReportes() {
    }
}
