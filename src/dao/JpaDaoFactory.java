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
public class JpaDaoFactory {
    
    public static WorkshopJpaDao getWorkshopDao() {
        return WorkshopJpaDao.getInstance();
    }
    
    public static MachineJpaDao getMachineDao() {
        return MachineJpaDao.getInstance();
    }
    
    public static TaskJpaDao getTaskDao() {
        return TaskJpaDao.getInstance();
    }
}
