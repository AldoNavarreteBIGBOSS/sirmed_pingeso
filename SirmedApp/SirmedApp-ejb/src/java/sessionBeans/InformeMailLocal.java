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
public interface InformeMailLocal {

    public void determinarHora(String h1, String h2);
    
}
