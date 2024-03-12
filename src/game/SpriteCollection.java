// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 A collection of Sprites that can be drawn on a DrawSurface and updated over time.
 */
public class SpriteCollection {
    private List<Sprite> mySprite;

    /**
     * Constructs a new, empty game.SpriteCollection.
     */
    public SpriteCollection() {
        this.mySprite = new ArrayList<Sprite>();
    }

    /**
     * Returns a list of sprites associated with the object.
     *
     * @return a list of sprites
     */
    public List<Sprite> getMySprite() {
        return this.mySprite;
    }


    /**
     * Adds a interfaces.Sprite to the game.SpriteCollection.
     *
     * @param s the interfaces.Sprite to add
     */
    public void addSprite(Sprite s) {

        mySprite.add(s);
    }

    /**
     * Calls the timePassed() method on all Sprites in the collection.
     * This method is used to update the state of all Sprites in the collection over time.
     */
    public void notifyAllTimePassed() {

        List<Sprite>  sprit = new ArrayList<Sprite>(this.mySprite);

        for (Sprite a : sprit) {

            a.timePassed();
        }

    }


    /**
     * Calls the drawOn(d) method on all Sprites in the collection, drawing each on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the Sprites
     */
    public void drawAllOn(DrawSurface d) {

        for (Sprite a : mySprite) {

            a.drawOn(d);
        }
    }
}