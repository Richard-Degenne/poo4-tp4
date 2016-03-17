/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import client.Scheduler;
import dao.DaoFactory;
import dao.DaoFactory.PersistenceType;
import dao.JpaDaoFactory;
import dao.MachineJpaDao;
import dao.TaskJpaDao;
import dao.WorkshopJpaDao;
import java.util.Date;
import model.Machine;
import model.Task;
import model.Workshop;

/**
 *
 * @author richou
 */
public class TestSchedule {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        
        Workshop w1 = new Workshop();
        Machine m1 = new Machine();
        Machine m2 = new Machine();
        Task t1 = new Task(45, new Date(time+120*60000), 5);
        Task t2 = new Task(120, new Date(time+150*60000), 10);
        Task t3 = new Task(70, new Date(time+180*60000), 4);
        Task t4 = new Task(60, new Date(time+300*60000), 12);

        JpaDaoFactory jdf = (JpaDaoFactory) DaoFactory.getDaoFactory(PersistenceType.JPA);
        WorkshopJpaDao wjd = jdf.getWorkshopDao();
        MachineJpaDao mjd = jdf.getMachineDao();
        TaskJpaDao tjd = jdf.getTaskDao();
        
        tjd.deleteAll();
        mjd.deleteAll();
        wjd.deleteAll();
        
        if(w1.addMachine(m1))
            System.out.println("Added m1 to w1.");
        else
            System.out.println("Couldn't add m1 to w1.");
        
        if(w1.addMachine(m2))
            System.out.println("Added m2 to w1.");
        else
            System.out.println("Couldn't add m2 to w1.");
        
        wjd.create(w1);
        tjd.create(t1);
        tjd.create(t2);
        tjd.create(t3);
        tjd.create(t4);
        
        Scheduler.schedule(PersistenceType.JPA);
        
        System.out.println(w1);
    }
}
