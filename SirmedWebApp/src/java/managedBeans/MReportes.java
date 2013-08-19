/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import auxiliar.ListaHorarios;
import entities.Municipalidad;
import entities.Registro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.Pojo.RegistroPojo;
import sessionBeans.MunicipalidadesLocal;
import sessionBeans.RegistrosLocal;
import sessionBeans.ReporteLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "mReportes")
@RequestScoped
public class MReportes {
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
    private List<ListaHorarios> listaTemporada;
    private List<ListaHorarios> listaAño;
    private MMessaegeController mc;
    private MAccionesGenerales ag;
    private String filtroMunicipalidad;
    private String filtroMunicipalidad2;
    private String filtroTemporada;
    private String filtroAño;

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

    public List<ListaHorarios> getListaTemporada() {
        return listaTemporada;
    }

    public void setListaTemporada(List<ListaHorarios> listaTemporada) {
        this.listaTemporada = listaTemporada;
    }

    public List<ListaHorarios> getListaAño() {
        return listaAño;
    }

    public void setListaAño(List<ListaHorarios> listaAño) {
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
        System.out.println(filtroMunicipalidad);
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
        listaTemporada = new LinkedList<ListaHorarios>();
        ListaHorarios verano = new ListaHorarios("verano", "Verano");
        ListaHorarios invierno = new ListaHorarios("invierno", "Invierno");
        ListaHorarios primavera = new ListaHorarios("primavera", "Primavera");
        ListaHorarios otoño = new ListaHorarios("otoño", "Otoño");
        listaTemporada.add(otoño);
        listaTemporada.add(primavera);
        listaTemporada.add(invierno);
        listaTemporada.add(verano);
        
        listaAño = new LinkedList<ListaHorarios>();
        for(Integer i = 13; i <= 30; i++){
            ListaHorarios lh = new ListaHorarios();
            lh.setEtiqueta("20"+i.toString());
            lh.setValor("20"+i.toString());
            listaAño.add(lh);
        }
    }
    
    
    public MReportes() {
    }
}
