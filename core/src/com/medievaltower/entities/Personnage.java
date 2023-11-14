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

import java.util.HashMap;
import java.util.Map;
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
    private static final float GRAVITY = 20f;
    private static Personnage instance;

    private AnimationPersonnage animation = new AnimationPersonnage();
    private final WeakHashMap<Weapon, Integer> weaponInventory = new WeakHashMap<>();
    private final WeakHashMap<Potion, Integer> potionInventory = new WeakHashMap<>();
    private int speed = 12;
    private boolean isJumping = false;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private int health = 3;
    private float exp = 120f;
    private int level = 1;
    private Weapon weaponEquipped = null;

    public WeakHashMap<Weapon, Integer> getWeaponInventory() {
        return weaponInventory;
    }

    public WeakHashMap<Potion, Integer> getPotionInventory() {
        return potionInventory;
    }

    public int getHealth() {
        return health;
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public Weapon getWeaponEquipped() {
        return weaponEquipped;
    }

    public Potion getPotionEquipped() {
        return potionEquipped;
    }

    private Potion potionEquipped = null;
    private Cle cleEquipped = null;
    private Direction currentDirection = Direction.NONE;
    private boolean isSliding = false;
    private boolean isSlow = false;
    private boolean isInvincible = false;
    private float invincibleTimer = 0;
    private final float invincibleDuration = 3;

    private Map<String, Boolean> Actions = new HashMap<String, Boolean>() {{
        put("Left", false);
        put("Right", false);
        put("Up", false);
        put("Down", false);
        put("Space", false);
    }};

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
        for (Map.Entry<String, Boolean> entry : Actions.entrySet()) {
            entry.setValue(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.currentDirection = Direction.UP;
            this.Actions.put("Up", true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.currentDirection = Direction.DOWN;
            this.Actions.put("Down", true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.currentDirection = Direction.LEFT;
            this.Actions.put("Left", true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.currentDirection = Direction.RIGHT;
            this.Actions.put("Right", true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.Actions.put("Space", true);
        } else {
            this.currentDirection = Direction.NONE;
        }
    }

    /**
     * Method update the personnage, jump and gravity
     */

    @Override
    public void update() {

        move();

        this.animation.setStateLocal("Breath");

        if (Actions.get("Left")){
            this.xVelocity = -speed;
            this.animation.setStateLocal("Run", true);
        }

        else if (Actions.get("Right")){
            this.xVelocity = speed;
            this.animation.setStateLocal("Run", false);
        }

        else {
            this.xVelocity = 0;
        }

        if (Actions.get("Up")){
            if (!isJumping){
                this.animation.setStateLocal("StartJump");
                this.jump();
            }
        }

        if (Actions.get("Down")){
            if (isJumping) {
                this.yVelocity = speed * Gdx.graphics.getDeltaTime();
            }
        }

        if (Actions.get("Space")){
            this.attack();
        }

        if (isJumping) {
            this.yVelocity -= GRAVITY * Gdx.graphics.getDeltaTime();
        }

        if (isInvincible) {
            invincibleTimer -= Gdx.graphics.getDeltaTime();
            if (invincibleTimer <= 0) {
                isInvincible = false; // Fin de l'invincibilité
            }
        }

        if (this.y < 0){
            this.y = 0;
            this.yVelocity = 0;
            this.isJumping = false;
        }

        //change position
        this.x += xVelocity;
        this.y += yVelocity;

        //change Texture
        updateTexture(animation);
        setBoundingBox();
        super.update();
    }

    /**
     * Method to jump
     */
    private void jump() {
        // Simulate the jump by adjusting the y coordinate
        this.isJumping = true;
        yVelocity = JUMP_FORCE; // Définit la vitesse initiale du saut
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

    @Override
    public void setBoundingBox() {
        boundingBox.setSize(width - 96, height); // Assurez-vous que width et height sont les dimensions de la boîte de collision
        boundingBox.setPosition(x + 48, y); // Assurez-vous que x et y sont les coordonnées actuelles du joueur
    }

}