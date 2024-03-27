package exceptions;

/**
 * The NegativePrice class represents an exception that is thrown when a negative price is entered.
 * It is a subclass of the Exception class.
 */
public class NegativePrice extends Exception {
    
    /**
     * Constructs a new NegativePrice object with a default error message.
     * The error message is printed to the console when the exception is thrown.
     */
    public NegativePrice() {
    	  super("Whoops! A negative price was entered. Please try again.");
    }
}
