/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import dao.DaoFactory;
import dao.DaoFactory.PersistenceType;
import dao.MachineDao;
import dao.TaskDao;
import dao.WorkshopDao;
import model.Machine;
import model.Task;
import model.Workshop;

/**
 *
 * @author richou
 */
public class Scheduler {

    /**
     * Schedules all unscheduled tasks in the given persistence unit to the
     * machines so the available date is minimized.
     *
     * @param type
     */
    public static void schedule(PersistenceType type) {
        DaoFactory df = DaoFactory.getDaoFactory(type);
        MachineDao md = df.getMachineDao();
        TaskDao td = df.getTaskDao();

        for (Task t : td.findAllNotScheduled()) {
            Machine m = md.findFirstAvailable();
            m.addTask(t);
                        
            md.update(m);
            td.update(t);
        }
    }
}
