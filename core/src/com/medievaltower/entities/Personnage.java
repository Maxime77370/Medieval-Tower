package com.medievaltower.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Direction;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;

import java.util.WeakHashMap;

public class Personnage extends Entity implements MovableEntity, AttackableEntity {

    private int health = 3;
    private int speed = 1;
    private Weapon weaponEquipped = null;
    private Potion potionEquipped = null;
    private WeakHashMap<Weapon, Integer> weaponInventory = new WeakHashMap<>();
    private WeakHashMap<Potion, Integer> potionInventory = new WeakHashMap<>();
    private Direction currentDirection = Direction.NONE;

    public Personnage(int x, int y) {
        super(x, y, 50, 50, new Sprite());
    }

    public void move() {
        // Gérer le mouvement sans mettre à jour directement les coordonnées
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.currentDirection = Direction.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.currentDirection = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.currentDirection = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.currentDirection = Direction.RIGHT;
        } else {
            this.currentDirection = Direction.NONE;
        }
    }

    public void updatePosition() {
        // Mettre à jour la position en fonction de la direction
        if (this.currentDirection == Direction.UP) {
            this.y += this.speed;
        } else if (this.currentDirection == Direction.DOWN) {
            this.y -= this.speed;
        } else if (this.currentDirection == Direction.LEFT) {
            this.x -= this.speed;
        } else if (this.currentDirection == Direction.RIGHT) {
            this.x += this.speed;
        }
    }

    public void attack() {
    }

    public void receiveDamage(int damage) {
    }

    public void addWeapon(Weapon weapon) {
        this.weaponEquipped = weapon;
    }

    public void addPotion(Potion potion) {
        this.potionEquipped = potion;
    }

    public void addWeaponInventory(Weapon weapon) {
        // Si le personnage a moins de 2 armes, ajoute automatiquement l'arme dans l'inventaire avec l'id
        if (this.weaponInventory.size() < 2) {
            if (this.weaponInventory.containsKey(weapon)) {
                this.weaponInventory.put(weapon, this.weaponInventory.get(weapon) + 1);
            } else {
                this.weaponInventory.put(weapon, 1);
            }
            // Si le personnage n'a pas d'arme équipée, équipe automatiquement la nouvelle arme
            if (this.weaponEquipped == null) {
                this.weaponEquipped = weapon;
            }
        } else {
            // Sinon, remplace l'arme équipée par la nouvelle arme
            if (this.weaponInventory.containsKey(weapon)) {
                this.weaponInventory.put(weapon, this.weaponInventory.get(weapon) + 1);
                this.weaponInventory.remove(this.weaponEquipped);
                this.weaponEquipped = weapon;
            } else {
                // Si l'arme n'est pas dans l'inventaire, remplace l'arme actuellement équipée
                Weapon weaponToRemove = this.weaponEquipped;
                this.weaponInventory.remove(weaponToRemove);
                this.weaponInventory.put(weapon, 1);
                this.weaponEquipped = weapon;
            }
        }
    }

    public void addPotionInventory(Potion potion) {
        // Si le personnage a moins de 2 potions, ajoute automatiquement la potion dans l'inventaire avec l'id
        if (this.potionInventory.size() < 2) {
            if (this.potionInventory.containsKey(potion)) {
                this.potionInventory.put(potion, this.potionInventory.get(potion) + 1);
            } else {
                this.potionInventory.put(potion, 1);
            }
            // Si le personnage n'a pas de potion équipée, équipe automatiquement la nouvelle potion
            if (this.potionEquipped == null) {
                this.potionEquipped = potion;
            }
        } else {
            // Sinon, remplace la potion équipée par la nouvelle potion
            if (this.potionInventory.containsKey(potion)) {
                this.potionInventory.put(potion, this.potionInventory.get(potion) + 1);
                this.potionInventory.remove(this.potionEquipped);
                this.potionEquipped = potion;
            } else {
                // Si la potion n'est pas dans l'inventaire, remplace la potion actuellement équipée
                Potion potionToRemove = this.potionEquipped;
                this.potionInventory.remove(potionToRemove);
                this.potionInventory.put(potion, 1);
                this.potionEquipped = potion;
            }
        }
    }

    public void removeWeaponInventory(Weapon weapon) {
        if (this.weaponInventory.containsKey(weapon)) {
            if (this.weaponInventory.get(weapon) > 1) {
                this.weaponInventory.put(weapon, this.weaponInventory.get(weapon) - 1);
            } else {
                this.weaponInventory.remove(weapon);
            }
        }
    }

    public void removePotionInventory(Potion potion) {
        if (this.potionInventory.containsKey(potion)) {
            if (this.potionInventory.get(potion) > 1) {
                this.potionInventory.put(potion, this.potionInventory.get(potion) - 1);
            } else {
                this.potionInventory.remove(potion);
            }
        }
    }
}
