package com.medievaltower.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Direction;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;

import java.util.WeakHashMap;

public class Personnage extends Entity implements MovableEntity, AttackableEntity {

    private static Personnage instance = null;

    private int health = 3;
    private final int speed = 1;
    private Weapon weaponEquipped = null;
    private Potion potionEquipped = null;
    private Cle cleEquipped = null;
    private final WeakHashMap<Weapon, Integer> weaponInventory = new WeakHashMap<>();
    private final WeakHashMap<Potion, Integer> potionInventory = new WeakHashMap<>();
    private Direction currentDirection = Direction.NONE;

    public Personnage(int x, int y) {
        super(x, y, 50, 50, new Sprite());

        // Créez une texture 1x1 de couleur verte
        Pixmap pixmap = new Pixmap(this.getWidth(), this.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        // Créez une texture à partir du pixmap
        Texture texture = new Texture(pixmap);

        // Définissez la texture du sprite
        getSprite().setRegion(new TextureRegion(texture));

        // N'oubliez pas de disposer du pixmap
        pixmap.dispose();
    }


    public static Personnage getInstance() {
        if (instance == null) {
            instance = new Personnage(0, 0);
        }
        return instance;
    }

    public void move() {
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
        // Depends on the weapon equipped by the character

    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
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

    public void update() {
        move();
        updatePosition();
    }

    public Cle getCleEquipped() {
        return this.cleEquipped;
    }

    public void setCleEquipped(Cle cle) {
        this.cleEquipped = cle;
    }
}
