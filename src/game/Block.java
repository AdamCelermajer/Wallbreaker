// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;


/**
 * A class representing a block, which implements the interfaces.Collidable and interfaces.Sprite interfaces.
 * A block has a rectangle shape and can collide with other objects. It can be drawn on a given DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.00001;
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private String whoAmI;

    /**
     * Constructs a game.Block object with a given rectangle shape.
     *
     * @param rectangle the rectangle shape of the block.
     * @param whoAmI    string representation of whom the object is
     */
    public Block(Rectangle rectangle, String whoAmI) {
        this.rectangle = rectangle;
        if (whoAmI == null) {

            this.whoAmI = "block";

        } else {
            this.whoAmI = whoAmI;
        }
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public void whoRU() {
        System.out.println(this.whoAmI);
    }

    /**
     * Gets the rectangle shape of the block, which represents its collision boundary.
     *
     * @return the rectangle shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Sets the rectangle shape of the block to a given rectangle.
     *
     * @param rectangle the rectangle shape to set for the block.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the block.
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adds the block as a sprite and a collidable to the given game.
     *
     * @param g the game to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

        g.addCollidable(this);
    }


    /**
     * remove the block as a sprite and a collidable from the given game.
     *
     * @param game the game to add the block to.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();


        //check if collide range in x
        if (collisionPoint.getX() < this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
            return currentVelocity;
        }
        // check if collide range in y

        if (collisionPoint.getY() < this.rectangle.getUpperLeft().getY()
                || collisionPoint.getY() > this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
            return currentVelocity;
        }



        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < EPSILON
                ||
                Math.abs(collisionPoint.getY() - (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()))
                        <
                        EPSILON) {
            dy = -dy;

        }
        if (Math.abs(collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) < EPSILON
                ||
                Math.abs(collisionPoint.getX() - (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()))
                        <
                        EPSILON) {
            dx = -dx;
        }

        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Notifies all registered HitListeners about a hit event with the specified geometry.Ball.
     *
     * @param hitter the geometry.Ball object that initiated the hit event
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {

        this.hitListeners.remove(hl);
    }


}
