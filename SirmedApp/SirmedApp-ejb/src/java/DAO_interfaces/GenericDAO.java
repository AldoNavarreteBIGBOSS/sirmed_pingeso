/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interfaces;

import java.util.Collection;

/**
 *
 * @author Aldo
 */
public interface GenericDAO<T> {
    
    public void insert(T newData);
    public void update (T actData);
    public void delete (T delData);
    public Collection<T> findAll();
    public T find(int id);
}
