package com.medievaltower.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Direction;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;
import com.medievaltower.entities.animation.AnimationPersonnage;
import com.medievaltower.entities.potion.Potion;
import com.medievaltower.entities.weapon.Weapon;
import com.medievaltower.game.Tileset;

import java.util.WeakHashMap;

/**
 * Personnage class
 * <p>
 * This class represents the personnage character in the game.
 * It contains the speed, the health, the weapon and the potion of the personnage.
 * It also contains the update method.
 * It extends the Entity class.
 * It implements the MovableEntity and the AttackableEntity interfaces.
 * It is a singleton class.
 * It contains the instance of the personnage.
 * It contains the weapon inventory and the potion inventory of the personnage.
 * It contains the methods to add and remove weapons and potions from the inventories.
 * It contains the methods to equip weapons and potions.
 * </p>
 *
 * @see Entity
 * @see MovableEntity
 * @see AttackableEntity
 * @see Weapon
 * @see Potion
 * @see Direction
 * @see WeakHashMap
 * @see Cle
 * @see Texture
 * @see Gdx
 * @see Input
 * @see System
 */
public class Personnage extends Entity implements MovableEntity, AttackableEntity {

    private static final float JUMP_FORCE = 18;
    private static final float GRAVITY = 0.9f;
    private static Personnage instance;

    private AnimationPersonnage animation = new AnimationPersonnage();
    private final WeakHashMap<Weapon, Integer> weaponInventory = new WeakHashMap<>();
    private final WeakHashMap<Potion, Integer> potionInventory = new WeakHashMap<>();
    private int speed = 12;
    private boolean isJumping = false;
    private float yVelocity = 0;
    private int health = 3;
    private Weapon weaponEquipped = null;
    private Potion potionEquipped = null;
    private Cle cleEquipped = null;
    private Direction currentDirection = Direction.NONE;
    private boolean isSliding = false;
    private boolean isSlow = false;
    private boolean isInvincible = false;
    private float invincibleTimer = 0;
    private final float invincibleDuration = 3;

    private Tileset runTile = new Tileset("2D_SL_Knight_v1.0/Run.png", 128, 64);
    private Tileset AttackTile = new Tileset("2D_SL_Knight_v1.0/Attacks.png", 128, 64);
    private Tileset JumpTile = new Tileset("2D_SL_Knight_v1.0/Jump.png", 128, 64);
    private Tileset SlideTile = new Tileset("2D_SL_Knight_v1.0/Slide.png", 128, 64);

    /**
     * Personnage constructor
     *
     * @param x : the x position of the personnage
     * @param y : the y position of the personnage
     */
    public Personnage(int x, int y) {
        super(x, y, 128*2, 64*2, new Texture("Texture/2D_SL_Knight_v1.0/Run.png"));
        // Définissez l'instance du personnage
        instance = this;

    }

    /**
     * Get the instance of the personnage
     *
     * @return the instance of the personnage
     */
    public static Personnage getInstance() {
        if (instance == null) {
            System.out.println("Personnage instance created");
            instance = new Personnage(0, 0);
        }
        return instance;
    }

    /**
     * Method to move the personnage with the keyboard
     */
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

    /**
     * Method update the personnage, jump and gravity
     */
    public void update() {
        // Move the personnage character based on the player's input and gravity
        this.animation.setStateLocal("Breath");
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.x -= this.speed;
            this.animation.setStateLocal("Run", true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.x += this.speed;
            this.animation.setStateLocal("Run", false);
        }

        if (isJumping) {
            // Si le personnage est en train de sauter, mettez à jour la position verticale
            this.y += yVelocity;
            yVelocity -= GRAVITY; // Applique la gravité pour modifier la vitesse de descente du saut
            this.animation.setStateLocal("InJump");
        }

        // Make th personnage down if the player is pressing the down key
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.y -= this.speed;
            if (this.isJumping){
                this.animation.setStateLocal("AttackFromAir");
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.attack();
        }

        // Check if the personnage character is below the ground
        if (this.y <= 0) { // Changement ici pour inclure le contact avec le sol
            if (this.isJumping){
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    this.animation.setStateLocal("EndAttackFromAir");
                    this.isJumping = false;
                }
                else {
                    this.animation.setStateLocal("EndJump");
                    this.isJumping = false;
                }
            }
            this.y = 0;
            yVelocity = 0; // Réinitialise la vitesse de saut lorsque le personnage touche le sol

        }

        // Check for jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !isJumping) {
            this.jump();
            this.animation.setStateLocal("StartJump");
        }

        if (isInvincible) {
            invincibleTimer -= Gdx.graphics.getDeltaTime();
            if (invincibleTimer <= 0) {
                isInvincible = false; // Fin de l'invincibilité
            }
        }

        //change Texture
        updateTexture(animation);
    }

    /**
     * Method to jump
     */
    private void jump() {
        // Simulate the jump by adjusting the y coordinate
        this.isJumping = true;
        yVelocity = JUMP_FORCE; // Définit la vitesse initiale du saut
        this.y += yVelocity; // Applique le mouvement initial du saut
    }

    /**
     * Method to attack
     */
    public void attack() {
        // Depends on the weapon equipped by the character
        this.attackAnimation();

    }

    private void attackAnimation() {
        if (this.isJumping){
            this.animation.setStateLocal("AttackFromAir");
        }
        else {
            this.animation.setStateLocal("Attack");
        }
    }

    /**
     * Method to receive damage
     *
     * @param damage : the damage received
     */
    public void receiveDamage(int damage) {
        if (!this.isInvincible) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
            // Active l'invincibilité après avoir subi des dégâts
            activateInvincibility();
        }
    }

    /**
     * Method to activate invincibility
     */
    private void activateInvincibility() {
        this.isInvincible = true;
        this.invincibleTimer = invincibleDuration; // Réinitialise le timer pour la durée spécifiée
    }

    /**
     * Add a weapon to the personnage
     */
    public void addWeapon(Weapon weapon) {
        this.weaponEquipped = weapon;
    }

    /**
     * Add a potion to the personnage
     */
    public void addPotion(Potion potion) {
        this.potionEquipped = potion;
    }

    /**
     * Add a weapon to the personnage inventory, maximum 2 weapons
     * @param weapon
     */
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

    /**
     * Add a potion to the personnage inventory, maximum 2 potions
     * @param potion
     */
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

    /**
     * Remove a weapon from the personnage inventory
     * @param weapon
     */
    public void removeWeaponInventory(Weapon weapon) {
        if (this.weaponInventory.containsKey(weapon)) {
            if (this.weaponInventory.get(weapon) > 1) {
                this.weaponInventory.put(weapon, this.weaponInventory.get(weapon) - 1);
            } else {
                this.weaponInventory.remove(weapon);
            }
        }
    }

    /**
     * Remove a potion from the personnage inventory
     * @param potion
     */
    public void removePotionInventory(Potion potion) {
        if (this.potionInventory.containsKey(potion)) {
            if (this.potionInventory.get(potion) > 1) {
                this.potionInventory.put(potion, this.potionInventory.get(potion) - 1);
            } else {
                this.potionInventory.remove(potion);
            }
        }
    }

    /**
     * Get a Cle
     * @return the Cle
     */
    public Cle getCleEquipped() {
        return this.cleEquipped;
    }

    /**
     * Set a Cle
     * @param cle : the Cle
     */
    public void setCleEquipped(Cle cle) {
        this.cleEquipped = cle;
    }

    /**
     * Make the personnage character slide
     * @param b
     */
    public void setSliding(boolean b) {
        this.isSliding = b;
        if (b) {
            // Upgrade the speed and make the personnage character slide
            this.speed = 40;
        } else {
            this.speed = 20;
        }
    }

    /**
     * Make the personnage character slow
     * @param b
     */
    public void setSlow(boolean b) {
        this.isSlow = b;
        if (b) {
            // Upgrade the speed and make the personnage character slide
            this.speed = 10;
        } else {
            this.speed = 20;
        }
    }

}