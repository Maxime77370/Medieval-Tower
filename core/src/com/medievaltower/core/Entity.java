package com.medievaltower.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.animation.Animation;
import com.medievaltower.levels.Map;


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


    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected Rectangle boundingBox = new Rectangle();

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
        for (MapObject platform : Map.getInstance().getPlatforms()) {
            if (platform instanceof RectangleMapObject) {
                Rectangle platformRect = ((RectangleMapObject) platform).getRectangle();

                if (this.getBoundingBox().overlaps(platformRect)) {
                    // Calculate the overlap on both X and Y axes
                    float overlapX = Math.min(getBoundingBox().x + getBoundingBox().width, platformRect.x + platformRect.width) - Math.max(getBoundingBox().x, platformRect.x);
                    float overlapY = Math.min(getBoundingBox().y + getBoundingBox().height, platformRect.y + platformRect.height) - Math.max(getBoundingBox().y, platformRect.y);

                    boolean horizontalCollision = overlapX < overlapY;

                    // Horizontal collision (left or right)
                    if (horizontalCollision) {
                        // Adjust to the left of the platform
                        if (x < platformRect.x) {
                            x = (int) (platformRect.x - this.boundingBox.width);
                        } else {
                            // Adjust to the right of the platform
                            x = (int) -100;
                        }
                    } else {
                        // Vertical collision (top or bottom)
                        if (y < platformRect.y) {
                            // Adjust to the top of the platform
                            y = (int) (platformRect.y - this.boundingBox.height);
                        } else {
                            // Adjust to the bottom of the platform
                            y = (int) (platformRect.y + platformRect.height);
                            // Stay on the platform, don't fall, stop the jump and velocity
                            Personnage personnage = Personnage.getInstance();

                            personnage.setJumping(false);
                            personnage.setVelocityY(0);
                        }
                    }
                }

                // Update the bounding box with the new position
                setBoundingBox();
            }
        }
    }


    public void update() {
        // Check for collisions
        checkCollidePlatform();

        // Update the sprite or animation
        // updateTexture(animation);
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
}