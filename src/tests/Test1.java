/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Date;
import model.Machine;
import model.Task;
import model.Workshop;

/**
 *
 * @author richou
 */
public class Test1 {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        
        Workshop w1 = new Workshop();
        w1.setId(1);
        Machine m1 = new Machine();
        m1.setId(1);
        Machine m2 = new Machine();
        m2.setId(2);
        Task t1 = new Task(45, new Date(time+120*60000), 5);
        t1.setId(1);
        Task t2 = new Task(120, new Date(time+150*60000), 10);
        t2.setId(2);
        Task t3 = new Task(70, new Date(time+180*60000), 4);
        t3.setId(3);
        Task t4 = new Task(60, new Date(time+300*60000), 12);
        t4.setId(4);
        
        if(w1.addMachine(m1))
            System.out.println("Added m1 to w1.");
        else
            System.out.println("Couldn't add m1 to w1.");
        
        if(w1.addMachine(m2))
            System.out.println("Added m2 to w1.");
        else
            System.out.println("Couldn't add m2 to w1.");
        
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
        
        System.out.println(w1);
    }
}
