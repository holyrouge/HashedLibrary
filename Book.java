/**
 * The <code>Book</code> class represents a book
 * with its title, author, publisher, and ISBN.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.io.Serializable;

public class Book implements Serializable {
    private String title; // book title
    private String author; // book author
    private String publisher; // book publisher
    private String isbn; // book's isbn number

    /**
     * Returns an instance of Book.
     *
     * <dt><b>Postcondition:</b><db>
     *     This Book has been initialized with template values.
     */
    public Book() {
        this.title = "";
        this.author = "";
        this.publisher = "";
        this.isbn = "";
    }

    /**
     * Returns an instance of Book.
     *
     * @param title
     *      The book's title
     * @param author
     *      The book's author
     * @param publisher
     *      The book's publisher
     * @param isbn
     *      The book's ISBN number
     *
     * <dt><b>Postcondition:</b><db>
     *     This Book has been initialized with given values. The <code>isbn</code> is a valid 13 digit ISBN.
     *
     * @throws IllegalArgumentException
     *      Indicates that given <code>isbn</code> is invalid. The <code>isbn</code> is a valid 13 digit ISBN.
     */
    public Book(String title, String author, String publisher, String isbn) throws IllegalArgumentException {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        if (isbn.length() != 13) // throw new exception if ISBN is not valid
            throw new IllegalArgumentException("Invalid ISBN.");
        this.isbn = isbn;
    }

    /**
     * Returns the title of the Book.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @return
     *      Returns the String value of the title of the Book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Book.
     *
     * @param title
     *      The title of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the author of the Book.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @return
     *      Returns the String value of the author of the Book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the Book.
     *
     * @param author
     *      The author of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the publisher of the Book.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @return
     *      Returns the String value of the publisher of the Book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the Book.
     *
     * @param publisher
     *      The publisher of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Returns the ISBN of the Book.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @return
     *      Returns the String value of the ISBN of the Book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the Book.
     *
     * @param isbn
     *      The ISBN of the Book
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @throws IllegalArgumentException
     *      Indicates that given <code>isbn</code> is invalid. The <code>isbn</code> is a valid 13 digit ISBN.
     */
    public void setIsbn(String isbn) throws IllegalArgumentException {
        if (isbn.length() != 13) // throw exception if isbn is not valid
            throw new IllegalArgumentException("Invalid ISBN. Need a 13 digit ISBN.");
        this.isbn = isbn;
    }

    /**
     * Returns the information in Book.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Book</code> has been initialized.
     *
     * @return
     *      Returns the information in Book as a formatted String.
     */
    public String toString() {
        // format output string
        return String.format("%-16s%-63s%-49s%-24s", this.isbn, this.title, this.author, this.publisher);
    }
}
