package model;

/**
 * The Puzzles class represents a type of toy called Puzzles.
 * It extends the Toy class and adds additional functionality specific to puzzles.
 */
public class Puzzles extends Toy {
    private String puzzleType;

    /**
     * Constructs a Puzzles object with the specified attributes.
     *
     * @param serialNumber     the serial number of the puzzle
     * @param name             the name of the puzzle
     * @param brand            the brand of the puzzle
     * @param price            the price of the puzzle
     * @param availableCount   the number of available units of the puzzle
     * @param ageAppropriate   the minimum age appropriate for the puzzle
     * @param puzzleType       the type of the puzzle (M - Mechanical, C - Cryptic, L - Logic, T - Trivia, R - Riddle)
     */
    public Puzzles(String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate, String puzzleType) {
        super(serialNumber, name, brand, price, availableCount, availableCount);
        setPuzzleType(puzzleType);
        this.setType("Puzzle");
        this.setAgeAppropriate(ageAppropriate);
    }

    /**
     * Returns the type of the puzzle.
     *
     * @return the puzzle type
     */
    public String getPuzzleType() {
        return puzzleType;
    }

    /**
     * Sets the type of the puzzle.
     *
     * @param puzzleType the puzzle type to set (M - Mechanical, C - Cryptic, L - Logic, T - Trivia, R - Riddle)
     */
    public void setPuzzleType(String puzzleType) {
        switch (puzzleType) {
            case "M":
                this.puzzleType = "Mechanical";
                break;
            case "C":
                this.puzzleType = "Cryptic";
                break;
            case "L":
                this.puzzleType = "Logic";
                break;
            case "T":
                this.puzzleType = "Trivia";
                break;
            case "R":
                this.puzzleType = "Riddle";
                break;
            default:
                System.out.println("Error in Puzzle");
                this.puzzleType = null;
                break;
        }
    }

    /**
     * Returns a string representation of the puzzle.
     *
     * @return a string representation of the puzzle
     */
    @Override
    public String toString() {
        return "Category: " + getType() +
            ", Serial Number: " + getSerialNumber() +
            ", Name: " + getName() +
            ", Brand: " + getBrand() +
            ", Price: " + getPrice() +
            ", Available Units: " + getAvailableCount() + 
            ", Minimum Age: " + getAgeAppropriate();
    }
    
    /**
     * Returns a string representation of the puzzle for database storage.
     *
     * @return a string representation of the puzzle for database storage
     */
    public String toDatabase() {
        return getSerialNumber() + ";" +
            getName() + ";" +
            getBrand() + ";" +
            getPrice() + ";" +
            getAvailableCount() + ";" +
            getAgeAppropriate() + ";" +
            getPuzzleType().charAt(0);
    }

    /**
     * Checks if the given serial number is valid for the puzzle.
     *
     * @param serialNumber the serial number to check
     * @return true if the serial number is valid, false otherwise
     */
    public boolean isSerialNumberValid(String serialNumber) {
        return serialNumber.startsWith("4") || serialNumber.startsWith("5") || serialNumber.startsWith("6");
    }
}
