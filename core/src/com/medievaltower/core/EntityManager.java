package com.medievaltower.core;

import java.util.ArrayList;

public class EntityManager<T extends Entity> {

    private static EntityManager instance;
    private ArrayList<T> entities;

    public EntityManager() {
        entities = new ArrayList<T>();
        instance = this;
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public void newEntitie(T entity) {
        entities.add(entity);
    }

    public void remove(T entity) {
        entities.remove(entity);
    }

    public ArrayList<T> getEntities() {
        return entities;
    }

    public void update() {
        for (T entity : entities) {
            entity.update();
        }
    }
}
