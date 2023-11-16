package com.medievaltower.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.medievaltower.entities.Cle;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.animation.Animation;
import com.medievaltower.entities.monster.Archer;
import com.medievaltower.entities.monster.Bat;
import com.medievaltower.entities.monster.Zombie;
import com.medievaltower.levels.Map;

import java.util.HashMap;
import java.util.List;


/**
 * Entity class
 * <p>
 * This class is the parent class of all entities in the game.
 * It contains the position, the size and the texture of the entity.
 * It also contains the update method.
 * It is abstract because it is not supposed to be instantiated.
 * It extends the Sprite class from libGDX.
 * </p>
 *
 * @see Sprite
 * @see Texture
 * @see EntityManager
 */
public abstract class Entity extends Sprite {


    protected float x;
    protected float y;
    protected float xLast;
    protected float yLast;
    protected int width;
    protected int height;
    protected Sprite sprite;
    protected static final float GRAVITY = 2000f;
    protected Rectangle boundingBox = new Rectangle();
    protected float yVelocity = 0;
    protected float xVelocity = 0;

    /**
     * Entity constructor
     * <p>
     * This constructor is used to create an entity.
     * It takes the position, the size and the texture of the entity as parameters.
     * It also adds the entity to the entity manager.
     * It is protected because it is not supposed to be used outside of the package.
     * It is used by the child classes.
     * It extends the Sprite class from libGDX.
     * </p>
     *
     * @param x       : the x position of the entity
     * @param y       : the y position of the entity
     * @param width   : the width of the entity
     * @param height  : the height of the entity
     * @param texture : the texture of the entity
     */
    protected Entity(int x, int y, int width, int height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = new Sprite(texture);

        setBoundingBox();

        EntityManager entityManager = EntityManager.getInstance();
    }

    /**
     * Get the x position of the entity
     *
     * @return the x position of the entity
     */
    public float getX() {
        return this.x;
    }

    /**
     * Get the y position of the entity
     *
     * @return the y position of the entity
     */
    public float getY() {
        return this.y;
    }

    /**
     * Get the width of the entity
     *
     * @return the width of the entity
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Get the height of the entity
     *
     * @return the height of the entity
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Get the sprite of the entity
     *
     * @return the sprite of the entity
     */
    public Sprite getSprite() {
        return this;
    }

    public void updateTexture(Animation animation) {
        this.sprite.setRegion(animation.update());
    }

    public void draw(Batch batch) {
        batch.draw(this.sprite, this.x, this.y, this.width, this.height);
    }

    public void checkCollidePlatform() {
        for (MapObject platformObject : Map.getInstance().getPlatforms()) {
            if (platformObject instanceof RectangleMapObject) {
                Rectangle platform = ((RectangleMapObject) platformObject).getRectangle();
                if (boundingBox.overlaps(platform)) {
                    // Déterminer le type de collision
                    if (isVerticalCollision(platform)) {
                        handleVerticalCollision(platform);
                    } else {
                        handleHorizontalCollision(platform);
                    }
                }
            }
        }
    }

    private boolean isVerticalCollision(Rectangle platform) {
        if (xLast + (getBoundingBox().x - x) + getBoundingBox().width < platform.x) {
            return false;
        } else return !(xLast + (getBoundingBox().x - x) > platform.x + platform.width);

    }

    private void handleVerticalCollision(Rectangle platform) {
        if (this.yVelocity > 0) { // Si l'entité se déplace vers le haut
            collide_ceiling();
            y = (int) (platform.y - this.boundingBox.height); // Positionnez l'entité juste en dessous de la plateforme
        } else { // Si l'entité se déplace vers le bas
            collide_floor();
            y = (int) (platform.y + platform.height); // Positionnez l'entité juste au-dessus de la plateforme
        }
        setBoundingBox(); // Mettez à jour la bounding box après avoir changé la position
    }

    private void handleHorizontalCollision(Rectangle platform) {
        if (Personnage.getInstance().getxVelocity() > 0) { // Si l'entité se déplace vers la droite
            collide_right();
            x = (int) (platform.x - this.boundingBox.width + x - boundingBox.x - 1); // Positionnez l'entité juste à gauche de la plateforme
        } else { // Si l'entité se déplace vers la gauche
            collide_left();
            x = (int) (platform.x + platform.width + x - boundingBox.x + 1); // Positionnez l'entité juste à droite de la plateforme
        }
        setBoundingBox(); // Mettez à jour la bounding box après avoir changé la position
    }


    public void update() {
        // Check for collisions
        checkCollidePlatform();

        // Update the sprite or animation
        // updateTexture(animation);
    }

    public void move(){
        this.yVelocity -= GRAVITY * Gdx.graphics.getDeltaTime();
        x += this.xVelocity * Gdx.graphics.getDeltaTime();
        y += this.yVelocity * Gdx.graphics.getDeltaTime();
        setBoundingBox();
    }

    /**
     * Update the entity
     * <p>
     * This method is used to update the entity.
     * It is abstract because it is not supposed to be used outside of the package.
     * It is used by the child classes.
     * </p>
     */

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox() {
        boundingBox.setSize(width, height); // Assurez-vous que width et height sont les dimensions de la boîte de collision
        boundingBox.setPosition(x, y); // Assurez-vous que x et y sont les coordonnées actuelles du joueur
    }

    public void drawDebug(Batch batch) {
        // Create a Pixmap
        Pixmap pixmap = new Pixmap((int) boundingBox.getWidth(), (int) boundingBox.getHeight(), Pixmap.Format.RGBA8888);
        // Fill it red
        pixmap.setColor(Color.RED);
        pixmap.fill();
        // Draw a box around the player
        batch.draw(new Texture(pixmap), boundingBox.x, boundingBox.y);
        // Dispose of the pixmap to avoid memory leaks
        pixmap.dispose();
    }

    public void collide_floor(){
        this.yVelocity = 0;
    }

    public void collide_ceiling(){
        this.yVelocity = 0;
    }

    public void collide_left(){
        this.xVelocity = 0;
        if (this.yVelocity < 0) {
            this.yVelocity *= 0.5f;
        }
    }

    public void collide_right(){
        this.xVelocity = 0;
        if (this.yVelocity < 0) {
            this.yVelocity *= 0.5f;
        }
    }

    public abstract void collide(Entity entity);

    public void dispose() {
        this.getSprite().getTexture().dispose();
    }
}