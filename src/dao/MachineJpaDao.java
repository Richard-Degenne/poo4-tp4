/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityTransaction;
import model.Machine;
import model.Task;
import model.Workshop;

/**
 *
 * @author richou
 */
public class MachineJpaDao extends JpaDao<Machine> implements MachineDao {
    
    private static MachineJpaDao instance;
    
    private MachineJpaDao() {
        
    }
    
    protected static MachineJpaDao getInstance() {
        if(instance == null)
            instance = new MachineJpaDao();
        return instance;
    }

    @Override
    public Machine find(long id) {
        return em.find(Machine.class, (Integer.valueOf((int) id)));
    }

    @Override
    public Collection<Machine> findAll() {
        return em.createNamedQuery("Machine.findAll").getResultList();
    }

    @Override
    public void deleteAll() {
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.createNamedQuery("Machine.deleteAll").executeUpdate();
        et.commit();
    }

    @Override
    public void close() {
        em.close();
    }

    @Override
    public Machine findFirstAvailable() {
        return (Machine) em.createNamedQuery("Machine.findAllOrderedByAvailable").setMaxResults(1).getResultList().get(0);
    }
}
