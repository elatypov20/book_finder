import java.util.ArrayList;

public class Book{
    private final int id;
    private final String author;
    private final String name;
    private final ArrayList<String> category;
    private final int pages;
    private final Status status;
    private final Location location;

    public Book(int id, String author, String name, ArrayList<String> category,
                int pages, Status status, Location location) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.category = category;
        this.pages = pages;
        this.status = status;
        this.location = location;
    }

    public int getId(){ return this.id;}

    public String getAuthor(){ return this.author;}

    public String getName(){ return this.name;}

    public ArrayList<String> getCategory(){ return this.category;}

    public int getPages(){ return this.pages;}

    public Status getStatus(){ return this.status;}

    public Location getLocation(){ return this.location;}

    public int countAvailable(){
        /*
        * 
        *
        * implement this method to demonstrate explicit usage
        *  of Singleton pattern in out program from separate places.
        *
        * Just get instance of database (Singleton) and via loop
        * count how many instances of this book are available at the moment
        * */
        int count = 0;
        for (Book book: LibraryDatabase.getInstance().getBooks()) {
            if (book.getAuthor().equals(this.author) && book.getName().equals(this.name)) count++;
        }
        return count;
    }
}

enum Status {
    STATUS_AVAILABLE,
    STATUS_UNAVAILABLE
}

class Location{
    private final int shelf, row, column;

    public Location(int shelf, int row, int column){
        this.shelf = shelf;
        this.row = row;
        this.column = column;
    }

    public int getShelf(){ return this.shelf;}

    public int getRow(){ return this.row;}

    public int getColumn(){ return this.column;}
}