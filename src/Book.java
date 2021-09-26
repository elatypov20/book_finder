import java.util.ArrayList;

public class Book{
    private int id;
    private String author;
    private String name;
    private ArrayList<String> category;
    private int pages;
    private Status status;
    private Location location;

    public Book(int id, String author, String name, ArrayList<String> category,
                int pages, Status status, Location location) {/*TODO*/}

    public int getId(){/*TODO*/ return 0;}

    public String getAuthor(){/*TODO*/ return "";}

    public String getName(){/*TODO*/ return "";}

    public ArrayList<String> getCategory(){/*TODO*/ return null;}

    public int getPages(){/*TODO*/ return 0;}

    public Status getStatus(){/*TODO*/ return null;}

    public Location getLocation(){/*TODO*/ return null;}

    public int countAvailable(){
        /*
        * TODO
        *
        * implement this method to demonstrate explicit usage
        *  of Singleton pattern in out program from separate places.
        *
        * Just get instance of database (Singleton) and via loop
        * count how many instances of this book are available at the moment
        * */
        return 0;
    }
}

enum Status {
    STATUS_AVAILABLE,
    STATUS_UNAVAILABLE
}

class Location{
    private int shelf, row, column;

    public Location(int shelf, int row, int column){/*TODO*/}

    public int getShelf(){/*TODO*/ return 0;}

    public int getRow(){/*TODO*/ return 0;}

    public int getColumn(){/*TODO*/ return 0;}
}