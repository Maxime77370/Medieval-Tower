package com.medievaltower.core;

import com.badlogic.gdx.graphics.g2d.Batch;

import com.medievaltower.entities.monster.Monstre;

import java.util.ArrayList;

/**
 * Class that manages the entities
 */
public class EntityManager<T extends Entity> {

    private static EntityManager instance;
    private ArrayList<T> entities;
    private ArrayList<T> entitiesToAdd;
    private ArrayList<T> entitiesToRemove;

    /**
     * Constructor
     * Creates an empty list of entities
     * Sets the instance of the class
     * @see EntityManager#instance
     * @see EntityManager#getInstance()
     * @see EntityManager#entities
     * @see EntityManager#newEntity(Entity) (Entity)
     * @see EntityManager#removeEntity(Entity) (Entity)
     * @see EntityManager#getEntities()
     * @see EntityManager#update()
     * @see Entity
     */
    public EntityManager() {
        entities = new ArrayList<T>();
        entitiesToAdd = new ArrayList<T>();
        entitiesToRemove = new ArrayList<T>();
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
        entitiesToAdd.add(entity);
    }

     /**
     * Remove an entity from the list of entities
     * @param entity : the entity to remove
     */
    public void removeEntity(T entity) {
        entitiesToRemove.add(entity);
    }

    /**
     * Get the list of entities
     * @return the list of entities
     */
    public ArrayList<T> getEntities() {
            return entitiesToAdd;
    }

    /**
     * Update all the entities
     */
    public void update() {
        for (Entity entity : entitiesToAdd) {
            entities.add((T) entity);
        }
        entitiesToAdd.clear();
        for (Entity entity : entitiesToRemove) {
            entities.remove(entity);
        }
        for (T entity : entities) {
            entity.move();
            collide(entity);
            entity.checkCollidePlatform();
            entity.update();
        }
    }

    /**
     * Draw all the entities
     * @param batch
     * @see Batch
     * @see Entity#draw(Batch)
     * @see Entity#drawDebug(Batch)
     * @see Entity
     */
    public void draw(Batch batch) {
        batch.begin();
        for (T entity : entities) {
            entity.draw(batch);
        }
        batch.end();
    }

    /**
     * Draw the debug of all the entities
     * @see Entity#drawDebug(Batch)
     * @see Entity
     * @see Batch
     * @see Entity#draw(Batch)
     * @see Entity
     * @param batch
     */
    public void drawDebug(Batch batch) {
        batch.begin();
        for (T entity : entities) {
            entity.drawDebug(batch);
        }
        batch.end();
    }

    /**
     * Get the number of monsters in the list of entities
     * @return the number of monsters in the list of entities
     */
    public int getNumberOfMonsters() {
        int count = 0;
        for (T entity : entities) {
            if (entity instanceof Monstre) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if an entity collides with another entity
     * @param entity1
     */
    public void collide(Entity entity1) {
        for (T entity2 : entities) {
            if (entity2 != entity1 && entity2.getBoundingBox().overlaps(entity1.getBoundingBox())) {
                entity1.collide(entity2);}
        }
    }

    /**
     * Dispose all the entities
     * @see Entity#dispose()
     * @see Entity
     */
    public void dispose() {
        for (T entity : entities) {
            entity.dispose();
        }
    }

    /**
     * Reset the list of entities
     */
    public void reset(){
        entities.clear();
        entitiesToAdd.clear();
        entitiesToRemove.clear();
    }
}
