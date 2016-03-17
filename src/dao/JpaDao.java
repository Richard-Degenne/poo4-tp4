/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author richou
 * @param <T>
 */
public abstract class JpaDao<T> implements Dao<T>{
    protected EntityManager em;
    
    protected final static String PERSISTENCE_UNIT = "POO_TP2PU";
    
    public JpaDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = emf.createEntityManager();
    }
    
    @Override
    public abstract T find(long id);
            
    @Override
    public boolean create(T t) {
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.persist(t);
            et.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(T t) {
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.merge(t);
            et.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(T t) {
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.remove(t);
            et.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
