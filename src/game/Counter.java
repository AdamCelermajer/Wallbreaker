// 332638592 Adam Celermajer
package game;

/**
 * The game.Counter class represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int count;

    /**
     * Constructs a new game.Counter object with the specified initial count.
     *
     * @param count the initial count for the counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Constructs a new game.Counter object with an initial count of zero.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the count by the specified number.
     *
     * @param number the number to be added to the current count
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the count by the specified number.
     *
     * @param number the number to be subtracted from the current count
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }

    /**
     * Returns the string representation of the current count.
     *
     * @return the string representation of the current count
     */
    @Override
    public String toString() {
        return Integer.toString(count);
    }
}
