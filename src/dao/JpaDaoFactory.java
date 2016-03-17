/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author richou
 */
public class JpaDaoFactory extends DaoFactory {
    
    protected JpaDaoFactory() {
        
    }
    
    @Override
    public WorkshopJpaDao getWorkshopDao() {
        return WorkshopJpaDao.getInstance();
    }
    
    @Override
    public MachineJpaDao getMachineDao() {
        return MachineJpaDao.getInstance();
    }
    
    @Override
    public TaskJpaDao getTaskDao() {
        return TaskJpaDao.getInstance();
    }
}
