package model;

import java.text.DecimalFormat;

/**
 * The abstract class representing a toy.
 */
public abstract class Toy {

    private String serialNumber;
    private String name;
    private String brand;
    private float price;
    private int availableCount;
    private int ageAppropriate;
    
    private String type;

    /**
     * Constructs a Toy object with the specified attributes.
     *
     * @param serialNumber    the serial number of the toy																																																																																																																																																																					
     * @param name            the name of the toy
     * @param brand           the brand of the toy
     * @param price           the price of the toy
     * @param availableCount  the available count of the toy
     * @param ageAppropriate  the age appropriateness of the toy
     */
    public Toy(String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.availableCount = availableCount;
        this.ageAppropriate = ageAppropriate;
    }

    /**
     * Returns the serial number of the toy.
     *
     * @return the serial number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serial number of the toy.
     *
     * @param serialNumber the serial number to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Returns the name of the toy.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the toy.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the brand of the toy.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the toy.
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the price of the toy.
     *
     * @return the price
     */
    public String getPrice() {
    	DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    /**
     * Sets the price of the toy.
     *
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns the available count of the toy.
     *
     * @return the available count
     */
    public int getAvailableCount() {
        return availableCount;
    }

    /**
     * Sets the available count of the toy.
     *
     * @param availableCount the available count to set
     */
    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    /**
     * Returns the age appropriateness of the toy.
     *
     * @return the age appropriateness
     */
    public int getAgeAppropriate() {
        return ageAppropriate;
    }

    /**
     * Sets the age appropriateness of the toy.
     *
     * @param ageAppropriate the age appropriateness to set
     */
    public void setAgeAppropriate(int ageAppropriate) {
        this.ageAppropriate = ageAppropriate;
    }
    
    /**
     * Returns the type of the toy.
     *
     * @return the type
     */
    public String getType() {
    	return type;
    }
    
    /**
     * Sets the type of the toy.
     *
     * @param type the type to set
     */
    public void setType(String type) {
    	this.type = type;
    }

    /**
     * Converts the toy object to a database representation.
     *
     * @return the database representation of the toy
     */
	public abstract String toDatabase();
	
	@Override
	public String toString() {
	    return "Category: " + getType() +
	            ", Serial Number: " + getSerialNumber() +
	            ", Name: " + getName() +
	            ", Brand: " + getBrand() +
	            ", Price: $" + getPrice() +
	            ", Available Units: " + getAvailableCount() +
	            ", Minimum Age: " + getAgeAppropriate();
	}
    


}
