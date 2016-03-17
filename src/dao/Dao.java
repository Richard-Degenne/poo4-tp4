/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;

/**
 *
 * @author richou
 * @param <T> Entity type
 */
public interface Dao<T> {
    /**
     * Creates a new object in the persistent data source.
     * 
     * @param t Object to create
     * @return True on success, false otherwise
     */
    public boolean create(T t);
    
    /**
     * Retrieves an object from the persistent data source.
     * 
     * @param id ID of the object to retrieve
     * @return The object if found, null otherwise
     */
    public T find(long id);
    
    /**
     * Retrieves all the objects available in the persistent data source.
     * 
     * @return A collection containing all the available objects.
     */
    public Collection<T> findAll();
    
    /**
     * Updates the object referred to by t.id in the persistent data source.
     * 
     * @param t The object to update (t.id *must* match an existing object)
     * @return True on success, false otherwise
     */
    public boolean update(T t);
    
    /**
     * Deletes an object from the persistent data source.
     * 
     * @param t The object to delete
     * @return True on success, false otherwise
     */
    public boolean delete(T t);
    
    /**
     * Deletes all the objects available in the persistent data source.
     */
    public void deleteAll();
    
    /**
     * Closes the connection to the database.
     * 
     * @see javax.persistence.EntityManager#close() 
     */
    public void close();
}
