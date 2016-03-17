/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.persistence.EntityTransaction;
import model.Task;

/**
 *
 * @author richou
 */
public class TaskJpaDao extends JpaDao<Task> implements TaskDao {
    
    private static TaskJpaDao instance;
    
    private TaskJpaDao() {
        
    }
    
    public static TaskJpaDao getInstance() {
        if(instance == null)
            instance = new TaskJpaDao();
        return instance;
    }

    @Override
    public Task find(long id) {
        return em.find(Task.class, id);
    }

    @Override
    public Collection<Task> findAll() {
        return em.createNamedQuery("Task.findAll").getResultList();
    }

    @Override
    public void deleteAll() {
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.createNamedQuery("Task.deleteAll").executeUpdate();
        et.commit();
    }

    @Override
    public void close() {
        em.close();
    }

    @Override
    public Collection<Task> findAllNotScheduled() {
        return em.createNamedQuery("Task.findAllNotScheduled").getResultList();
    }
    
}
