package model;

/**
 * The Animals class represents a type of toy that is an animal.
 * It extends the Toy class and adds additional properties such as material and size.
 */
public class Animals extends Toy {
    private String material;
    private String size;

    /**
     * Constructs a new Animals object with the specified properties.
     *
     * @param serialNumber     the serial number of the toy
     * @param name             the name of the toy
     * @param brand            the brand of the toy
     * @param price            the price of the toy
     * @param availableCount   the available count of the toy
     * @param ageAppropriate   the age appropriateness of the toy
     * @param material         the material of the toy
     * @param size             the size of the toy
     */
    public Animals(String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate, String material, String size) {
        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
        this.material = material;
        this.size = size;
        this.setType("Animal");
        this.setAgeAppropriate(ageAppropriate);
    }

    /**
     * Gets the material of the toy.
     *
     * @return the material of the toy
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material of the toy.
     *
     * @param material the material of the toy
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Gets the size of the toy.
     *
     * @return the size of the toy
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the toy.
     * Throws an IllegalArgumentException if the size is not "small", "medium", or "large".
     *
     * @param size the size of the toy
     */
    public void setSize(String size) {
        try {
            switch (size.toLowerCase()) {
                case "small":
                case "medium":
                case "large":
                    this.size = size;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid size. Size must be small, medium, or large.");
            }
        } catch (IllegalArgumentException e) {
            // Handle the exception here
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the given serial number is valid.
     * A valid serial number starts with either "2" or "3".
     *
     * @param serialNumber the serial number to check
     * @return true if the serial number is valid, false otherwise
     */
    public boolean isSerialNumberValid(String serialNumber) {
        return serialNumber.startsWith("2") || serialNumber.startsWith("3");
    }

    /**
     * Returns a string representation of the Animals object.
     *
     * @return a string representation of the Animals object
     */
    @Override
    public String toString() {
        return "Category: " + getType() +
                ", Serial Number: " + getSerialNumber() +
                ", Name: " + getName() +
                ", Brand: " + getBrand() +
                ", Price: " + getPrice() +
                ", Available Units: " + getAvailableCount() + 
                ", Minimum Age: " + getAgeAppropriate() +
            ", Material: " + material +
            ", Size: " + size;
    }   
        public String toDatabase() {
       	 return getSerialNumber() + ";" +
       	            getName() + ";" +
       	            getBrand() + ";" +
       	            getPrice() + ";" +
       	            getAvailableCount() + ";" +
       	            getAgeAppropriate() + ";" +
       	            getMaterial() + ";" +
       	            getSize();
    }
}
