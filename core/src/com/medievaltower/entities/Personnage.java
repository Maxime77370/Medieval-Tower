package com.medievaltower.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Direction;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;
import com.medievaltower.entities.animation.AnimationPersonnage;
import com.medievaltower.entities.monster.Arrow;
import com.medievaltower.entities.monster.Monstre;
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

    private static final float JUMP_FORCE = 600;
    private static Personnage instance;
    private final WeakHashMap<Weapon, Integer> weaponInventory = new WeakHashMap<>();
    private final WeakHashMap<Potion, Integer> potionInventory = new WeakHashMap<>();
    private final float invincibleDuration = 3;
    private final AnimationPersonnage animation = new AnimationPersonnage();
    private final float exp = 120f;
    private final int level = 1;
    private final Direction currentDirection = Direction.NONE;
    private final boolean isSlow = false;
    private final Map<String, Boolean> Actions = new HashMap<String, Boolean>() {{
        put("Left", false);
        put("Right", false);
        put("Up", false);
        put("Down", false);
        put("Space", false);
    }};
    private final Tileset runTile = new Tileset("2D_SL_Knight_v1.0/Run.png", 128, 64);
    private final Tileset AttackTile = new Tileset("2D_SL_Knight_v1.0/Attacks.png", 128, 64);
    private final Tileset JumpTile = new Tileset("2D_SL_Knight_v1.0/Jump.png", 128, 64);
    private final Tileset SlideTile = new Tileset("2D_SL_Knight_v1.0/Slide.png", 128, 64);
    private int speed = 256;
    private boolean isJumping = false;
    private int health = 3;
    private Weapon weaponEquipped = null;
    private Potion potionEquipped = null;
    private Cle cleEquipped = null;
    private boolean isSliding = false;
    private boolean isInvincible = false;
    private float invincibleTimer = 0;
    private boolean isDead = false;

    /**
     * Personnage constructor
     *
     * @param x : the x position of the personnage
     * @param y : the y position of the personnage
     */
    public Personnage(int x, int y) {
        super(x, y, 128, 64, new Texture("Texture/2D_SL_Knight_v1.0/Run.png"));
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

    public float getxVelocity() {
        return xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    /**
     * Method to move the personnage with the keyboard
     */
    public void move() {
        for (Map.Entry<String, Boolean> entry : Actions.entrySet()) {
            entry.setValue(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.Actions.put("Up", true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.Actions.put("Down", true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.Actions.put("Left", true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.Actions.put("Right", true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.Actions.put("Space", true);
        }


        this.xLast = this.x;
        this.yLast = this.y;

        // run left : left
        // slide left : left + down
        // run right : right
        // slide right : right + down
        // jump : up
        // jump attack : down + isJumping
        // attack : space

        this.animation.setStateLocal("Breath");

        if (Actions.get("Left")) {

            if (Actions.get("Down") && !isJumping) {
                if (!isSliding) {
                    this.xVelocity *= 2f;
                }
                this.isSliding = true;
                this.animation.setStateLocal("Slide", true);
            } else {
                this.animation.setStateLocal("Run", true);
                this.xVelocity = -speed;
            }
        } else if (Actions.get("Right")) {

            if (Actions.get("Down") && !isJumping) {
                if (!isSliding) {
                    this.xVelocity *= 2f;
                }
                this.isSliding = true;
                this.animation.setStateLocal("Slide", false);
            } else {
                this.animation.setStateLocal("Run", false);
                this.xVelocity = speed;
            }

        } else {
            this.xVelocity = 0;
        }

        if (!Actions.get("Down") && isSliding) {
            this.isSliding = false;
        }


        if (Actions.get("Up")) {
            if (!isJumping) {
                this.animation.setStateLocal("StartJump");
                this.jump();
            }
        }

        if( isJumping){
            this.animation.setStateLocal("InJump");
        }
        
        if (Actions.get("Down")) {
            if (isJumping) {
                this.yVelocity -= speed * Gdx.graphics.getDeltaTime() / 2;
                this.animation.setStateLocal("AttackFromAir");
            }
        }

        if (Actions.get("Space")) {
            this.attack();
        }

        if (isInvincible) {
            invincibleTimer -= Gdx.graphics.getDeltaTime();
            if (invincibleTimer <= 0) {
                isInvincible = false; // Fin de l'invincibilité
            }
        }

        if (this.y < 0) {
            this.y = 0;
            this.yVelocity = 0;
            this.isJumping = false;
        }

        //verify event
        setSliding();

        super.move();
    }

    /**
     * Method update the personnage, jump and gravity
     */

    @Override
    public void update() {
        //change Texture
        updateTexture(animation);
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
        if (this.isJumping) {
            this.animation.setStateLocal("AttackFromAir");
        } else {
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
                this.isDead = true;
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
     *
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
     *
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
     *
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
     *
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
     *
     * @return the Cle
     */
    public Cle getCleEquipped() {
        return this.cleEquipped;
    }

    /**
     * Set a Cle
     *
     * @param cle : the Cle
     */
    public void setCleEquipped(Cle cle) {
        this.cleEquipped = cle;
    }

    /**
     * Make the personnage character slide
     */
    public void setSliding() {
        if (this.isSliding) {
            // Upgrade the speed and make the personnage character slide
            this.xVelocity -= this.xVelocity * 1.5f * Gdx.graphics.getDeltaTime();
        }
    }


    /**
     * Make the personnage character slow
     */
    public void setSlow() {
        if (this.isSlow) {
            // Upgrade the speed and make the personnage character slide
            this.speed = 10;
        } else {
            this.speed = 20;
        }
    }

    @Override
    public void setBoundingBox() {
        // Set the bounding box of the personnage character
        boundingBox.setSize(width - 101, height - 17);
        boundingBox.setPosition(x + 50, y);
    }


    @Override
    public void collide_floor(){
        this.isJumping = false;
        super.collide_floor();
    }

    @Override
    public void collide_left(){
        super.collide_left();
    }

    @Override
    public void collide_right(){
        super.collide_right();
    }

    public void collide_wall(){
        this.xVelocity = 0;
    }

    public void setJumping(boolean b) {
        this.isJumping = b;
    }

    public void setVelocityY(int i) {
        this.yVelocity = i;
    }

    public void setVelocityX(int i) {
        this.xVelocity = i;
    }

    public boolean isMovingHorizontally() {
        return xVelocity != 0;
    }

    public boolean isMovingVertically() {
        return yVelocity != 0;
    }

    public void collide(Entity entity) {
        if (entity instanceof Monstre) {
            this.receiveDamage(1);
            System.out.println("Monster collision");
        }
        else if (entity instanceof Arrow){
            this.receiveDamage(1);
            System.out.println("Arrow collision");
        }
        else if (entity instanceof Potion){
            this.addPotionInventory((Potion) entity);
            System.out.println("Potion collision");
        }
        else if (entity instanceof Cle) {
            this.setCleEquipped((Cle) entity);
            System.out.println("Cle collision");
        }
    }
}