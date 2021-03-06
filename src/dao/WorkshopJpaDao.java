/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.persistence.EntityTransaction;
import model.Workshop;

/**
 *
 * @author richou
 */
public class WorkshopJpaDao extends JpaDao<Workshop> implements WorkshopDao {
    
    private static WorkshopJpaDao instance;
    
    private WorkshopJpaDao() {
        
    }
    
    protected static WorkshopJpaDao getInstance() {
        if(instance == null)
            instance = new WorkshopJpaDao();
        return instance;
    }

    @Override
    public Workshop find(long id) {
        return em.find(Workshop.class, Integer.valueOf((int) id));
    }

    @Override
    public Collection<Workshop> findAll() {        
        return em.createNamedQuery("Workshop.findAll").getResultList();
    }

    @Override
    public void deleteAll() {
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.createNamedQuery("Workshop.deleteAll").executeUpdate();
        et.commit();
    }

    @Override
    public void close() {
        em.close();
    }

    @Override
    public Workshop findFirstAvailable() {
        return (Workshop) em.createNamedQuery("Workshop.findAllOrderedByAvailable").setMaxResults(1).getResultList().get(0);
    }
}
