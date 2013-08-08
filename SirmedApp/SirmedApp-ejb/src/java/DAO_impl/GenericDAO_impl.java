/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interfaces.GenericDAO;
import java.util.Collection;
import javax.persistence.EntityManager;

/**
 *
 * @author Aldo
 */
public abstract class GenericDAO_impl<T> implements GenericDAO<T> {
    
    private Class<T> entityClass;
    private EntityManager em;
    
    public GenericDAO_impl(Class<T> entityClass, EntityManager em){
        
        this.entityClass = entityClass;
        this.em = em;
    }    
    
    protected EntityManager getEntityManager(){
        
        return this.em;
    }
    
    @Override
    public void insert(T newData){
      
      getEntityManager().persist(newData);
    }
    
    @Override
    public void update(T actData){
        getEntityManager().merge(actData);
    }
    
    @Override
    public void delete(T delData){
        
        T aBorrar = getEntityManager().merge(delData);
        getEntityManager().remove(aBorrar);
        
    }
    
    @Override
    public Collection<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    @Override
    public T find(int id){
        return getEntityManager().find(entityClass, id);
    }
    
}
