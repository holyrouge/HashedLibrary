/**
 * The <code>HashedLibrary</code> class contains
 * a hashed data structure that stores Book objects. It
 * implements the Serializable Java API to store the data
 * after the program has been terminated.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import big.data.*;

public class HashedLibrary extends Hashtable<String, Book> implements Serializable {
    /**
     * Returns an instance of HashedLibrary
     *
     * <dt><b>Postcondition:</b><db>
     *     This HashedLibrary has been initialized.
     */
    public HashedLibrary() {
        super(); // initialize HashedLibrary and Hashtable
    }

    /**
     * Records a Book into the hash.
     *
     * @param title
     *      The title of the book
     * @param author
     *      The author of the book
     * @param publisher
     *      The publisher of the book
     * @param isbn
     *      The ISBN of the book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>HashedLibrary</code> has been initialized.
     */
    public void addBook(String title, String author, String publisher, String isbn) {
        Book newBook = new Book(title, author, publisher, isbn); // crt Book
        put(newBook.getIsbn(), newBook); // add Book using put() method of Hashtable
        System.out.println(newBook.getIsbn() + ": " + newBook.getTitle() + " by " + newBook.getAuthor() + " recorded.");
    }

    /**
     * Parses the given TXT file and adds a Book record for each XML file in the TXT file.
     *
     * @param fileName
     *      The file name that includes the Book information
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>HashedLibrary</code> has been initialized. <code>fileName</code> exists in the working directory.
     *
     * @throws FileNotFoundException
     *      Indicates that the <code>fileName</code> does not exist in the working directory.
     */
    public void addAllBookInfo(String fileName) throws FileNotFoundException {
        File textFile = new File(fileName);
        Scanner in; // scanner to take data from file
        String line, title, author, publisher, isbn;
        if (textFile.exists()) { // check if TXT file exists
            in = new Scanner(textFile);
            while (in.hasNextLine()) { // while there are lines in TXT file
                line = in.nextLine();
                // get XML data using DataSource class of big.data
                try {
                    DataSource ds = DataSource.connect("http://www3.cs.stonybrook.edu/~cse214/hw/hw6/" + line +
                            ".xml").load();
                    title = ds.fetchString("title");
                    author = ds.fetchString("author");
                    publisher = ds.fetchString("publisher");
                    isbn = ds.fetchString("isbn");

                    if (!containsKey(isbn)) { // only add to hashtable if the book does not exist already
                        this.addBook(title, author, publisher, isbn);
                    }
                    else {
                        System.out.println(isbn + ": Book already recorded.");
                    }
                }
                catch (Exception ex) {
                    System.out.println("Error accessing data at " + line + ".xml. Data from " + line + ".xml will" +
                            " not be included in library.");
                }
            }
        }
        else {
            // throw exception if file does not exist
            throw new FileNotFoundException(fileName + " not found in the working directory.");
        }
    }

    /**
     * Returns the Book reference with the given ISBN if it exists
     *
     * @param isbn
     *      The ISBN of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>HashedLibrary</code> has been initialized.
     *
     * @return
     *      Returns the Book reference with the given ISBN if it exists, null otherwise.
     */
    public Book getBookByisbn(String isbn) {
        if (containsKey(isbn)) {
            return get(isbn);
        }
        else {
            return null;
        }
    }

    /**
     * Prints all the Books in the library.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>HashedLibrary</code> has been initialized.
     */
    public void printCatalog() {
        System.out.println("\nBook ISBN       Title                                                          " +
                "Author                   " +
                "                        Publisher");
        System.out.println("---------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------");
        // uses forEach method of hashtable to output each element of the hashtable
        forEach((k, v) -> System.out.println(v.toString()));
    }

    /**
     * Prints the Book with the given ISBN
     *
     * @param isbn
     *      The isbn of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>HashedLibrary</code> has been initialized. The <code>isbn</code> is a valid 13 digit ISBN.
     *
     * @throws IllegalArgumentException
     *      Indicates that given <code>isbn</code> is invalid.
     */
    public void printBookByIsbn(String isbn) throws IllegalArgumentException {
        if (isbn.length() == 13) { // check if valid ISBN
            if (this.containsKey(isbn)) { // if ISBN exists, output the Book
                System.out.println("\nBook ISBN       Title                                                          " +
                        "Author                   " +
                        "                        Publisher");
                System.out.println("---------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------------------");
                System.out.println(this.getBookByisbn(isbn).toString());
            }
            else { // if Book doesn't exist in Library
                System.out.println("\nThe Library does not contain the Book with the ISBN: " + isbn);
            }
        }
        else { // throw exception if not valid ISBN
            throw new IllegalArgumentException("Invalid ISBN. Need a 13 digit ISBN.");
        }
    }
}
