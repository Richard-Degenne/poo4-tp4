/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.persistence.EntityTransaction;
import model.Machine;

/**
 *
 * @author richou
 */
public class MachineJpaDao extends JpaDao<Machine> implements MachineDao {

    @Override
    public Machine find(long id) {
        return em.find(Machine.class, id);
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
    
}
