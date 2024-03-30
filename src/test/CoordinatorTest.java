//package test;
//import org.junit.jupiter.api.Test;
//
//import controller.Coordinator;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.util.Scanner;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class CoordinatorTest {
//
//    @Test
//    public void testMainMenu_Choice1_SearchToys() {
//        // Prepare test data
//        String input = "1\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Call the method under test
//        Coordinator.mainMenu();
//
//        // Verify the output
//        String expectedOutput = "Enter your choice: ";
//        assertTrue(outputStream.toString().contains(expectedOutput));
//    }
//
//    @Test
//    public void testMainMenu_Choice2_AddToy() {
//        // Prepare test data
//        String input = "2\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Call the method under test
//        Coordinator.mainMenu();
//
//        // Verify the output
//        String expectedOutput = "Enter your choice: ";
//        assertTrue(outputStream.toString().contains(expectedOutput));
//    }
//
//    @Test
//    public void testSaveToysToFile() throws IOException {
//        // Prepare test data
//        String filePath = "res/test_toys.txt";
//        File file = new File(filePath);
//        file.createNewFile();
//        FileWriter writer = new FileWriter(file);
//        writer.write("1234567890;Test Toy;Test Brand;9.99;5;3;A\n");
//        writer.close();
//
//        // Call the method under test
//        Coordinator.saveToysToFile();
//
//        // Verify the file content
//        Scanner scanner = new Scanner(file);
//        String firstLine = scanner.nextLine();
//        scanner.close();
//        assertEquals("", firstLine);
//
//        file.delete();
//    }
//}