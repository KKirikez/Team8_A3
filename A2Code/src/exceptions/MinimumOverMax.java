package exceptions;

/**
 * The `MinimumOverMax` class represents an exception that is thrown when the minimum number of players is greater than the maximum number of players.
 */
public class MinimumOverMax extends Exception {
   
    /**
     * Constructs a new `MinimumOverMax` exception with a default error message.
     */
    public MinimumOverMax() {
       super("The minimum number of players is greater than the maximum");
    }
}