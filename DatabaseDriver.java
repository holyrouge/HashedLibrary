/**
 * The <code>DatabaseDriver</code> class contains
 * a main method to help the user interface with the
 * HashedLibrary.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;
import big.data.*;

public class DatabaseDriver {
    final static String LIBRARY_FILE = "library.obj";
    /**
     * Menu-driven program that allows the user to interface with the HashedLibrary. The program stores information
     * within a .obj file (default is library.obj) to allow for data continuation after the program has been
     * terminated.
     */
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        char userInput; // for user input

        System.out.println("Welcome to the DatabaseDriver!");

        HashedLibrary myLibrary;
        try { // get data from .obj database
            FileInputStream file = new FileInputStream(LIBRARY_FILE);
            ObjectInputStream fin = new ObjectInputStream(file);
            // readObject() returns Object, so must typecast to HashedLibrary
            myLibrary = (HashedLibrary) fin.readObject();
            fin.close();
        }
        catch (IOException ex) { // make new HashedLibrary if error
            System.out.println("\nError with getting input from library.obj. Using a new HashedLibrary.");
            myLibrary = new HashedLibrary();
        }
        catch (ClassNotFoundException ex) { // make new HashedLibrary if not found
            System.out.println("library.obj is not found. Using a new HashedLibrary.");
            myLibrary = new HashedLibrary();
        }

        while (true) { // while loop for menu to continue running unless quit
            System.out.println("\n\nMain Menu: \n" +
                    "(D) Display Books \n" +
                    "(G) Get Book \n" +
                    "(L) Load File \n" +
                    "(R) Record Book \n" +
                    "(Q) Quit \n");

            System.out.print("\nEnter a selection: ");
            userInput = sc.next().toUpperCase().charAt(0); // get the user input and set to uppercase

            switch(userInput) {
                case 'D': // Display all books in a nice tabular format
                    myLibrary.printCatalog();
                    break;
                case 'G': // get book by ISBN
                    try {
                        String isbn;
                        System.out.print("Enter Book ISBN:");
                        isbn = sc.next();
                        myLibrary.printBookByIsbn(isbn); // print Book
                    }
                    catch (IllegalArgumentException ex) {
                        System.out.println("\nInvalid input ISBN. Please try again!");
                    }
                    break;
                case 'L': // accepts a file name and records all books specified in the file
                    try {
                        System.out.print("Enter the file to load: ");
                        String loadFile = sc.next();
                        myLibrary.addAllBookInfo(loadFile); // load all Books in TXT file
                    }
                    catch (IOException ex) {
                        System.out.println("\nThe given file was not found in the working directory. " +
                                "Please try again!");
                    }
                    break;
                case 'R': // prompts the user for book information, and record this book.
                    String title, author, publisher, isbn;
                    while (true) {
                        System.out.print("Enter book title: ");
                        sc.nextLine();
                        title = sc.nextLine();
                        System.out.print("Enter book author: ");
                        author = sc.nextLine();
                        System.out.print("Enter book publisher: ");
                        publisher = sc.nextLine();
                        System.out.print("Enter book ISBN: ");
                        isbn = sc.next();

                        if (isbn.length() != 13) { // check for valid ISBN
                            System.out.println("\nInvalid ISBN. Need a 13 digit ISBN. Please try again!");
                            continue;
                        }
                        else {
                            try {
                                myLibrary.addBook(title, author, publisher, isbn);
                            }
                            catch (Exception ex) {
                                System.out.println("Error in input for Book. Please try again.");
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                case 'Q': // Terminates the program and save the data to library.obj
                    // end the program if userInput is 'Q'
                    boolean quitSafely = true;
                    try { // store HashedLibrary object in .obj
                        FileOutputStream file = new FileOutputStream(LIBRARY_FILE);
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(myLibrary); // Writes myLibrary to LIBRARY_FILE
                        fout.close();
                    }
                    catch (IOException ex){
                        System.out.println("\nUnable to save library to " + LIBRARY_FILE);
                        quitSafely = false;
                    }

                    if (!quitSafely) { // if unable to save, ask to continue with program or quit without saving
                        System.out.print("\nThere was an error in saving your current library. Continue quitting? " +
                                "(Y/N): ");
                        userInput = sc.next().toUpperCase().charAt(0); // get the user input and set to uppercase
                        if (userInput == 'Y') {
                            System.out.println("\nProgram terminating without saving...");
                            System.exit(0);
                        }
                        else {
                            System.out.println("\nContinuing with the program...");
                        }
                    }
                    else {
                        System.out.println("\nCurrent data saved in " + LIBRARY_FILE + ".");
                        System.out.println("Program terminating normally...");
                        System.exit(0);
                    }
                    break;
                default: // if userInput is none of the above
                    System.out.println("\nInvalid Selected Operation. Please try again!\n");
                    break;
            } // end switch
        } // end while
    } // end main method
} // end class
