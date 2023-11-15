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
        for (Entity entity : entitiesToAdd) {
            entities.add((T) entity);
        }
        entitiesToAdd.clear();
        for (T entity : entities) {
            entity.update();
            entity.checkCollidePlatform();
        }
        collide();
    }


    public void draw(Batch batch) {
        batch.begin();
        for (T entity : entities) {
            entity.draw(batch);
        }
        batch.end();
    }

    public void drawDebug(Batch batch) {
        batch.begin();
        for (T entity : entities) {
            entity.drawDebug(batch);
        }
        batch.end();
    }

    public int getNumberOfMonsters() {
        int count = 0;
        for (T entity : entities) {
            if (entity instanceof Monstre) {
                count++;
            }
        }
        return count;
    }

    public void collide() {
        for (T entity1 : entities) {
            for (T entity2 : entities) {
                if (entity2 != entity1 && entity2.getBoundingBox().overlaps(entity1.getBoundingBox())) {
                    entity1.collide(entity2);
                }
            }
        }
    }

    public void dispose() {
        for (T entity : entities) {
            entity.dispose();
        }
    }
}
