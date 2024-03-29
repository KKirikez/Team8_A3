package view;

import java.util.List;

import model.Toy;

/**
 * The ToyStoreMenu class represents the menu options for a toy store company.
 * It provides methods to draw the main menu, search menu, and print a list of acceptable toys.
 */
public class ToyStoreMenu {
    
    /**
     * Draws the main menu of the toy store company.
     * The main menu displays various options for the user to choose from.
     */
    public static void drawMainMenu() {
        System.out.println("************************************");
        System.out.println("*    Welcome to Toy Store Company  *");
        System.out.println("************************************");
        System.out.println("How may we help you?");
        System.out.println("1. Search Inventory and Purchase Toy");
        System.out.println("2. Add a New Toy");
        System.out.println("3. Remove a Toy");
        System.out.println("4. Make a Recommendation");
        System.out.println("5. Save and Exit");
    }
    
    /**
     * Draws the search menu of the toy store company.
     * The search menu allows the user to search for toys based on different criteria.
     */
    public static void drawSearchMenu() {
        System.out.println("Find toys with: \n");
        System.out.println("(1) Serial Number");
        System.out.println("(2) Toy Name");
        System.out.println("(3) Type");
        System.out.println("(4) Back to Main Menu");
    }

    /**
     * Prints a list of acceptable toys.
     * 
     * @param acceptableToys The list of toys to be printed.
     */
    public static void drawPrintList(List<Toy> acceptableToys) {
        int count = 1;
        for (Toy toy : acceptableToys) {
            System.out.println(count + ") " + toy.toString());
            count++;
        }
    }
}
