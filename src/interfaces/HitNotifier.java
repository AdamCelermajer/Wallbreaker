// 332638592 Adam Celermajer
package interfaces;

/**
 * The interfaces.HitNotifier interface represents an object that can notify listeners of hit events.
 */
public interface HitNotifier {
    /**
     * Add a interfaces.HitListener to the list of listeners for hit events.
     *
     * @param hl the interfaces.HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a interfaces.HitListener from the list of listeners for hit events.
     *
     * @param hl the interfaces.HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
