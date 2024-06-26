package controller;

import java.util.Scanner;

import exceptions.MinimumOverMax;
import exceptions.NegativePrice;
import view.ToyStoreMenu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;
/**
 * The `Coordinator` class is responsible for coordinating the different functionalities of the toy store application.
 * It handles the main menu, loading toys from a file, saving toys to a file, searching for toys, adding new toys to the inventory,
 * and purchasing toys.
 */
public class Coordinator {
    private static final String FILE_PATH = "res/toys.txt";
    private static List<Toy> toys = new ArrayList<>();
/**
     * Displays the main menu and handles user input to navigate different functionalities of the toy store application.
     */
    public static void mainMenu() {
        loadToysFromFile();
        ToyStoreMenu.drawMainMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
               searchToys();
                break;
            case 2:
                addToy();
                break;
            case 3:
                removeToy();
                break;
            case 4:
            	giftSuggestion();
                break;
            case 5:
                System.out.println("Thank you for using our app!"); //or something else just as corny
                saveToysToFile();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.\n");
                mainMenu();
        }
        scanner.close();
    }
/**
     * Loads toys from a file into the application's memory.
     */
    public static void loadToysFromFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String toyString = scanner.nextLine();
                String type = getType(toyString);
                String[] toyArray = toyString.split(";");
                Toy toy = null;
                switch (type) {
                    case "Figure":
                        //0113513686;Toy soldier;Gamen;14.06;2;5;A
                        //String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate, String classification
                        toy = new Figures(toyArray[0], toyArray[1], toyArray[2], Float.parseFloat(toyArray[3]), Integer.parseInt(toyArray[4]),
                                Integer.parseInt(toyArray[5]), toyArray[6]);
                        break;

                    case "Animal":
                        //3015547049;Dove;Game Zombie;24.55;2;9;Wooden;S
                        //String serialNumber, String name, String brand, float price, int availableCount, int ageAppropriate, String material, String size
                        toy = new Animals(toyArray[0], toyArray[1], toyArray[2], Float.parseFloat(toyArray[3]), Integer.parseInt(toyArray[4]),
                                Integer.parseInt(toyArray[5]), toyArray[6], toyArray[7]);
                        break;

                    case "Puzzle":
                        //5239019250;Nob Yoshigahara Puzzle Design Competition;Game Zombie;67.20;5;3;M
                        //String serialNumber, String name, String brand, float price, int availableCount, String puzzleType
                        toy = new Puzzles(toyArray[0], toyArray[1], toyArray[2], Float.parseFloat(toyArray[3]), Integer.parseInt(toyArray[4]), Integer.parseInt(toyArray[5]),
                                toyArray[6]);
                        break;

                    case "Board Game":
                        //7734720369 ; Doom: The Boardgame ; Gamefluent ; 174.24 ; 10 ; 7 ; 2-5 ; Miller Knights
                        //String serialNumber, String name, String brand, float price, int availableCount, int minPlayers, int maxPlayers, String designers
                        toy = new Boardgames(toyArray[0], toyArray[1], toyArray[2], Float.parseFloat(toyArray[3]), Integer.parseInt(toyArray[4]), Integer.parseInt(toyArray[5]),
                                Integer.parseInt(toyArray[6].substring(0, 1)), Integer.parseInt(toyArray[6].substring(2, 3)), toyArray[7]);
                        break;
                }

                toys.add(toy);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Failed to load toys from file: " + e.getMessage());
        }
    }
     /**
     * Saves the current list of toys to a file.
     */
    public static void saveToysToFile() {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Toy toy : toys) {
                writer.write(toy.toDatabase() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save toys to file: " + e.getMessage());
        }
    }
    /**
     * Allows the user to search for toys based on various criteria.
     */
    public static void searchToys() {
        ToyStoreMenu.drawSearchMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        
        String target = null;
        List<Toy> matchingToys = new ArrayList<>();
        switch (choice) {
            case 1:
                System.out.println("Enter the serial number: ");
                target = scanner.nextLine();
                matchingToys = compareToys(target, "Serial");
                break;
            case 2:
                System.out.println("Enter the name of the toy: ");
                target = scanner.nextLine().toLowerCase();
                matchingToys = compareToys(target, "Name");
                break;
            case 3:
                System.out.println("Enter the type of toy: ");
                target = scanner.nextLine();
                matchingToys = compareToys(target, "Type");
                break;
            case 4:
                mainMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.\n");
                    searchToys();
                    scanner.close();
                    return; 
        }
    
        if (!matchingToys.isEmpty()) {
            System.out.println("Matching toys:");
            for (int i = 0; i < matchingToys.size(); i++) {
                System.out.println((i + 1) + ". " + matchingToys.get(i));
            }

            System.out.println((matchingToys.size() + 1) + ". Return to search menu");
            
            System.out.println("Select the number of the toy to purchase, 0 to cancel, or " + (matchingToys.size() + 1) + " to return to search menu:");
            int toyChoice = scanner.nextInt();
            scanner.nextLine(); 
    
            if (toyChoice > 0 && toyChoice <= matchingToys.size()) {
                purchaseToy(matchingToys.get(toyChoice - 1), scanner);
            } else if (toyChoice == matchingToys.size() + 1) {
                searchToys();
            } else {
                System.out.println("Purchase cancelled.");
            }
        } else {
            System.out.println("No matching toys found.");
            searchToys();
        }
    }
     /**
     * allows the purchase of a toy.
     * @param toyToPurchase The toy to be purchased.
     * @param scanner       The scanner object for user input.
     */
    private static void purchaseToy(Toy toyToPurchase, Scanner scanner) {
        // Toy object is directly passed to this method, so no need to search it again
        if (toyToPurchase != null) {
            System.out.println("You have selected: " + toyToPurchase.toString());
            System.out.println("Do you want to purchase this toy? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
    
            if ("yes".equals(confirmation)) {
                System.out.println("The Transaction Successfully Terminated!");
                toyToPurchase.setAvailableCount(toyToPurchase.getAvailableCount() - 1);
                // Remove the toy from the list if it's no longer available
                if (toyToPurchase.getAvailableCount() <= 0) {
                    toys.remove(toyToPurchase);
                }
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); 
                searchToys();
            }
        } else {
            System.out.println("Error: Toy not found.");
        }
    }
    
 /**
     * Allows the user to add a new toy to the inventory.
     */
private static void addToy() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Adding a new Toy!");

    System.out.print("Enter Serial Number: ");
    String serialNumber = scanner.nextLine();


    if (serialNumber.length() != 10) {
        System.out.println("Invalid serial number. It must be 10 digits long.");
        scanner.close();
        return;
    }
    if (!isAllDigits(serialNumber)) {
        System.out.println("Invalid serial number. It must contain only numbers.");
        scanner.close();
        return;
    }   
    for (Toy toy : toys) {
        if (toy.getSerialNumber().equals(serialNumber)) {
            System.out.println("Serial number already exists. Please enter a unique serial number.");
            return;
        }
    }

    
    String type = getType(serialNumber);
    if ("Error".equals(type)) {
        System.out.println("Invalid serial number. First digit must be between 0 and 9.");
        addToy();
        return;
    }

    System.out.print("Enter Toy Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Toy Brand: ");
    String brand = scanner.nextLine();

    System.out.print("Enter Toy Price: ");
    float price = Float.parseFloat(scanner.nextLine());
    try {
    	if (price < 0) {
        	throw new NegativePrice();
    	}
    } catch (NegativePrice e) {
    	e.printStackTrace();
    }

    System.out.print("Enter Available Count: ");
    int availableCount = scanner.nextInt();

    System.out.print("Enter Appropriate Age: ");
    int ageAppropriate = scanner.nextInt();
    scanner.nextLine();  

    Toy toy = null;
    switch (type) {
        case "Figure":
            System.out.print("Enter Classification (Action, Doll, Historic): ");
            String classification = scanner.nextLine();
            toy  = new Figures(serialNumber, name, brand, price, availableCount, ageAppropriate, classification);
            break;
        case "Animal":
            System.out.print("Enter Material: ");
            String material = scanner.nextLine();
            System.out.print("Enter Size (Small, Medium, Large): ");
            String size = scanner.nextLine();
            toy = new Animals(serialNumber, name, brand, price, availableCount, ageAppropriate, material, size);
            break;
        case "Puzzle":
            System.out.print("Enter Puzzle Type (Mechanical, Cryptic, Logic, Trivia, Riddle): ");
            String puzzleType = scanner.nextLine();
            toy = new Puzzles(serialNumber, name, brand, price, availableCount, ageAppropriate, puzzleType);
            break;
        case "Board Game":
           System.out.print("Enter Minimum Number of Players: ");
            int minPlayers = scanner.nextInt();
            System.out.print("Enter Maximum Number of Players: ");
            int maxPlayers = scanner.nextInt();
            try {
            	if (minPlayers > maxPlayers) {
                	throw new MinimumOverMax();
            	}
            } catch (MinimumOverMax e) {
                e.printStackTrace();
            }
            scanner.nextLine(); 
            System.out.print("Enter Designer(s): ");
            String designers = scanner.nextLine();
            toy = new Boardgames(serialNumber, name, brand, price, availableCount, ageAppropriate, minPlayers, maxPlayers, designers);
            break;
        default:
            System.out.println("Invalid toy type.");
            return;
    }

    toys.add(toy);
    System.out.println("New toy added successfully!");

	System.out.println("Press Enter to continue...");
    scanner.nextLine(); 

	mainMenu();
}

/**
     * Checks if a string contains only digits.
     * @param str The string to be checked.
     * @return true if the string contains only digits, false otherwise.
     */
private static boolean isAllDigits(String str) {
    for (char c : str.toCharArray()) {
        if (!Character.isDigit(c)) {
            return false;
        }
    }
    return true;
}

	/**
     * Allows the user to remove a toy from the inventory.
     */
private static void removeToy() {
	Scanner scanner = new Scanner(System.in);

    System.out.println("Removing a Toy!");

    System.out.print("Enter Serial Number: ");
    String serialNumber = scanner.nextLine();

    
    Toy toyToRemove = null;
    for (Toy toy : toys) {
        if (toy.getSerialNumber().equalsIgnoreCase(serialNumber)) {
            toyToRemove = toy;
            break;
        }
    }

    if (toyToRemove != null) {
        System.out.println("This Item Found:");
        System.out.println(toyToRemove);
        System.out.print("Do you want to remove it (Y/N)? ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            toys.remove(toyToRemove);
            System.out.println("Item Removed!"); 
    } else {
        System.out.println("Toy with serial number " + serialNumber + " not found.");
    }

    System.out.println("Press Enter to Continue...");
    scanner.nextLine();
    mainMenu();
    }
}


private static void purchaseToy(String target, String parameterType, Scanner scanner) {
    Toy toyToPurchase = null;
    for (Toy toy : toys) {
        if (compare(toy, target, parameterType)) {
            toyToPurchase = toy;
            break;
        }
    }

    if (toyToPurchase != null) {
        System.out.println("Toy found: " + toyToPurchase.toString());
        System.out.println("Do you want to purchase this toy? (yes/no): ");

        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            System.out.println("The Transaction Successfully Terminated!");
            toys.remove(toyToPurchase);
            toyToPurchase.setAvailableCount(toyToPurchase.getAvailableCount() - 1);
        } else {
            System.out.println("Transaction cancelled. You can continue browsing our toys.");
        }
    } else {
        System.out.println("Toy not found. Please enter a valid input.");
    }

    System.out.println("Press Enter to continue...");
    scanner.nextLine();
}

    private static boolean compare(Toy toy, String target, String parameterType) {
        switch (parameterType) {
            case "Serial":
                return toy.getSerialNumber().equals(target);
            case "Name":
                return toy.getName().equalsIgnoreCase(target);
            case "Type":
                return toy.getType().equalsIgnoreCase(target);
            default:
                return false;
        }
    }
    /**
     * Suggests toys as a gift based on user input criteria.
     */
    private static void giftSuggestion() {
    Scanner scanner = new Scanner(System.in);
    int age = -1;
    String type = "";
    float maxPrice = 0;
    String reply;
    System.out.println("Our advanced systems will now determine what type of toy you should buy!");
    System.out.println("If you are unsure of any answer, leave the answer blank.");

    	try {
    		System.out.println("What is the gift recipients age: ");
    		reply = scanner.nextLine();
    		if(!(reply == "")) {
    			age = Integer.parseInt(reply);
    		}
    	} catch (Exception e) {
    		System.out.println("Error, invalid age. Try again!");
    		giftSuggestion();
    	}
    	try {
    		System.out.println("What is the gift recipients favourite toy type: ");
    		reply = scanner.nextLine();
    		if(!(reply == "")) {
    			type = reply;
    		}
    	} catch (Exception e) {
    		System.out.println("Error, invalid toy Type. Try again!");
    		giftSuggestion();
    	}
    	try {
    		System.out.println("What is Your maximum price: ");
    		reply = scanner.nextLine();
    		if(!(reply == "")) {
    			maxPrice = Float.parseFloat(reply);
    		}
    	} catch (Exception e) {
    		System.out.println("Error, invalid price. Try again!");
    		giftSuggestion();
    	}
    	 List<Toy> acceptableToys = new ArrayList<>();
    	 for (Toy toy : toys) {
    		 if(!(age == -1)) {
    			if(age < toy.getAgeAppropriate()) {
    				continue;
    			}
    		}
    		if(!(type.equals(""))) {
    			if(!(toy.getType().equals(type))) {
    				continue;
    			}
    		}
    		if(!(maxPrice == 0)) {
    			if(Float.parseFloat(toy.getPrice()) > maxPrice) {
    				continue;
    			}
    		}
    		acceptableToys.add(toy);
    	}
    	System.out.println("Here is a list of the acceptable toys we have found:");
    	view.ToyStoreMenu.drawPrintList(acceptableToys);
    	System.out.println("\nFeel free to go to our Inventory and Purchase section to make a purchase!");
    	mainMenu();
    }
     /**
     * Compares toys based on a given parameter.
     * @param target        The target value to compare against.
     * @param parameterType The type of parameter to compare (e.g., "Serial", "Name", "Type").
     * @return A list of matching toys.
     */
    public static List<Toy> compareToys(String target, String parameterType) {
        List<Toy> matchingToys = new ArrayList<>();
        String targetString = target.toLowerCase();
        
        for (Toy toy : toys) { 
            switch (parameterType) {
                case "Serial":
                    if (toy.getSerialNumber().equalsIgnoreCase(target)) {
                        matchingToys.add(toy);
                    }
                    break;
                case "Name":
                    if (toy.getName().toLowerCase().contains(targetString)) {
                        matchingToys.add(toy);
                    }
                    break;
                case "Type":
                    if (toy.getType().equalsIgnoreCase(target)) {
                        matchingToys.add(toy);
                    }
                    break;
            }
        }
        return matchingToys;
    }

    
    public static List<Toy> searchToysByName(String name) {
        System.out.println("Searching for name: " + name);
        List<Toy> results = compareToys(name, "Name");
        System.out.println("Found " + results.size() + " results");
        return results;
    }
    
    public static List<Toy> getToys() {
        return toys;
    }


    /**
     * Determines the type of toy based on the first digit of its serial number.
     * 
     * @param input The serial number of the toy.
     * @return The type of toy (e.g., "Figure", "Animal", "Puzzle", "Board Game").
     */
    
    private static String getType(String input) {
    	char firstChar = input.charAt(0);

    	switch (firstChar) {
    	    case '0':
    	    case '1':
    	        return "Figure";
    	    case '2':
    	    case '3':
    	        return "Animal";
    	    case '4':
    	    case '5':
    	    case '6':
    	        return "Puzzle";
    	    case '7':
    	    case '8':
    	    case '9':
    	        return "Board Game";
    	    default:
    	        return "Error";
    	}
    }
  
   
}
