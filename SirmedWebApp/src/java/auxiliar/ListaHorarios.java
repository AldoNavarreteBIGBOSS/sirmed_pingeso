/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author Aldo
 */
public class ListaHorarios {
    
    private String valor;
    private String etiqueta;

    public ListaHorarios(){}

    public ListaHorarios(String valor, String etiqueta) {
        this.valor = valor;
        this.etiqueta = etiqueta;
    }
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
}
