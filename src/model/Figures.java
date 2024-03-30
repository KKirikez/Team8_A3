package model;

/**
 * The Figures class represents a type of toy called Figures.
 * It extends the Toy class and adds additional properties and methods specific to Figures.
 */
public class Figures extends Toy {
    private String classification;

    /**
     * Constructs a Figures object with the specified parameters.
     *
     * @param serialNumber     the serial number of the Figures
     * @param name             the name of the Figures
     * @param brand            the brand of the Figures
     * @param price            the price of the Figures
     * @param availableCount   the available count of the Figures
     * @param ageAppropriate   the age appropriateness of the Figures
     * @param classification   the classification of the Figures
     */
    public Figures(String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate, String classification) {
        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
        this.classification = classification;
        this.setType("Figure");
        this.setAgeAppropriate(ageAppropriate);
    }

    /**
     * Sets the classification of the Figures.
     *
     * @param classification   the classification to be set
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * Returns the classification of the Figures.
     *
     * @return the classification of the Figures
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Checks if the given serial number is valid.
     * A valid serial number starts with either "0" or "1".
     *
     * @param serialNumber   the serial number to be checked
     * @return true if the serial number is valid, false otherwise
     */
    public boolean isSerialNumberValid(String serialNumber) {
        return serialNumber.startsWith("0") || serialNumber.startsWith("1");
    }

    /**
     * Returns a string representation of the Figures object.
     *
     * @return a string representation of the Figures object
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
     * Returns a string representation of the Figures object for database storage.
     *
     * @return a string representation of the Figures object for database storage
     */
    public String toDatabase() {
        return getSerialNumber() + ";" +
                getName() + ";" +
                getBrand() + ";" +
                getPrice() + ";" +
                getAvailableCount() + ";" +
                getAgeAppropriate() + ";" +
                getClassification();
    }
}
