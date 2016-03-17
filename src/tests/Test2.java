/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

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
public class Test2 {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        
        Workshop w1 = new Workshop();
        Machine m1 = new Machine();
        Machine m2 = new Machine();
        Task t1 = new Task(45, new Date(time+120*60000), 5);
        Task t2 = new Task(120, new Date(time+150*60000), 10);
        Task t3 = new Task(70, new Date(time+180*60000), 4);
        Task t4 = new Task(60, new Date(time+300*60000), 12);

        WorkshopJpaDao wjd = WorkshopJpaDao.getInstance();
        MachineJpaDao mjd = MachineJpaDao.getInstance();
        TaskJpaDao tjd = TaskJpaDao.getInstance();
        
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
        
        for(Task t : tjd.findAllNotScheduled())
            System.out.println(t);
        
        if (m1.addTask(t1))
            System.out.println("Added t1 to m1.");
        else
            System.out.println("Couldn't add t1 to m1.");
        
        if(m1.addTask(t2))
            System.out.println("Added t2 to m1.");
        else
            System.out.println("Couldn't add t2 to m1.");
        
        if(m2.addTask(t3))
            System.out.println("Added t3 to m2.");
        else
            System.out.println("Couldn't add t3 to m2.");
        
        if(m2.addTask(t4))
            System.out.println("Added t4 to m2.");
        else
            System.out.println("Couldn't add t4 to m2.");
        
        tjd.update(t1);
        tjd.update(t2);
        tjd.update(t3);
        tjd.update(t4);
    }
}
