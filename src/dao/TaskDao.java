/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import model.Task;

/**
 *
 * @author richou
 */
public interface TaskDao extends Dao<Task> {
    
    public Collection<Task> findAllNotScheduled();
}
