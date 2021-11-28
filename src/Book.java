import java.io.FileNotFoundException;


/**
 * this class contain information about book
 */
public class Book{
    private final int id;
    private final String author;
    private final String name;
    private final String category;
    private final int pages;
    private Status status;
    private final Location location;
    private final int year;

    /**
     *
     * @param id book id
     * @param author book author
     * @param name book name
     * @param category book category
     * @param pages book pages
     * @param status book status
     * @param location book location
     * @param year book year
     */
    public Book(int id, String author, String name, String category,
                int pages, Status status, Location location, int year) {
        this.id=id;
        this.author=author;
        this.name=name;
        this.category=category;
        this.pages=pages;
        this.status=status;
        this.location=location;
        this.year=year;
    }

    /**
     *
     * @return book id
     */
    public int getId(){ return id;}

    /**
     *
     * @return book author
     */
    public String getAuthor(){ return author;}

    /**
     *
     * @return book name
     */
    public String getName(){ return name;}

    /**
     *
     * @return book category
     */
    public String getCategory(){ return category;}

    /**
     *
     * @return book pages
     */
    public int getPages(){ return pages;}

    /**
     *
     * @return book status
     */
    public Status getStatus(){ return status;}

    /**
     *
     * @return location of the book
     */
    public Location getLocation(){ return location;}

    /**
     *
     * @return book year
     */
    public int getYear() { return year; }

    /**
     *
     * @param status of the book
     */
    public void setStatus(Status status){ this.status=status;}

    /**
     *
     * @return number of available books
     * @throws FileNotFoundException
     */
    public int countAvailable() throws FileNotFoundException {
        int cnt = 0;
        BooksCollectionIterator bi = LibraryDatabaseClient.getInstance().getBooks().getIterator();
        while (bi.hasNext()){
            Book book = bi.getNext();
            if(book.name.equals(this.name) && book.author.equals(this.author) && book.getStatus() == Status.STATUS_AVAILABLE) cnt++;
        }
        return cnt;

    }
}

/**
 * tracking availability of the book
 */
enum Status {
    STATUS_AVAILABLE,
    STATUS_UNAVAILABLE
}


/**
 * This class represents book location in library
 */
class Location{
    private final int shelf, row, column;

    /**
     *
     * @param shelf location shelf
     * @param row location row
     * @param column location
     */
    public Location(int shelf, int row, int column){
        this.shelf=shelf;
        this.row=row;
        this.column=column;
    }

    /**
     *
     * @return shelf location
     */
    public int getShelf(){ return shelf;}

    /**
     *
     * @return row location
     */
    public int getRow(){ return row;}

    /**
     *
     * @return column location
     */
    public int getColumn(){ return column;}
}