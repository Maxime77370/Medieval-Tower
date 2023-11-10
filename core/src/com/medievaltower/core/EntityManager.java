package com.medievaltower.core;

import java.util.ArrayList;

/**
 * Class that manages the entities
 */
public class EntityManager<T extends Entity> {

    private static EntityManager instance;
    private ArrayList<T> entities;

    /**
     * Constructor
     * Creates an empty list of entities
     * Sets the instance of the class
     * @see EntityManager#instance
     * @see EntityManager#getInstance()
     * @see EntityManager#entities
     * @see EntityManager#newEntitie(Entity)
     * @see EntityManager#remove(Entity)
     * @see EntityManager#getEntities()
     * @see EntityManager#update()
     * @see Entity
     */
    public EntityManager() {
        entities = new ArrayList<T>();
        instance = this;
    }

    /**
     * Get the instance of the class
     * @return the instance of the class
     */
    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    /**
     * Add an entity to the list of entities
     * @param entity : the entity to add
     */
    public void newEntity(T entity) {
        entities.add(entity);
    }

     /**
     * Remove an entity from the list of entities
     * @param entity : the entity to remove
     */
    public void removeEntity(T entity) {
        entities.remove(entity);
    }

    /**
     * Get the list of entities
     * @return the list of entities
     */
    public ArrayList<T> getEntities() {
        return entities;
    }

    /**
     * Update all the entities
     */
    public void update() {
        for (T entity : entities) {
            entity.update();
        }
    }
}
