import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * This class extends Database class
 * And has methods to action with database books
 */
public class LibraryDatabase extends Database{
    private final List<Book> books;

    /**
     * It reads database and parses its data to create
     * list of books, with which we will perform actions in future
     *
     * @throws FileNotFoundException if file not found
     */
    public LibraryDatabase() throws FileNotFoundException {
        super("database.csv");

        books = new ArrayList<>();

        for (List<String> row : rows) {
            String name = row.get(0);
            String author = row.get(1);
            String category = row.get(2);
            int year = Integer.parseInt(row.get(3));
            int page = Integer.parseInt(row.get(4));
            Status status = Status.STATUS_AVAILABLE;
            if (row.get(6).equals("FALSE"))
                status = Status.STATUS_UNAVAILABLE;
            int id = Integer.parseInt(row.get(7));
            int[] location = new int[3];
            int h = 1, j = 0;
            String s = row.get(5);
            for (int i = 1; i < s.length() - 1; i++) {
                if (s.charAt(i) == ',') {
                    i++;
                    h = 1;
                    j++;
                } else {
                    location[j] = location[j] * h + Character.getNumericValue(s.charAt(i));
                    h *= 10;
                }
            }
            Book newBook = new Book(id, author, name, category, page, status, new Location(location[0], location[1], location[2]), year);
            books.add(newBook);
        }
    }

    /**
     * @return All books from database
     */
    public List<Book> getBooks(){
        return books;
    }

    /**
     * Provides searching in LibraryDatabase by author name
     *
     * @param authorName
     * @return All books that have same author
     */
    public List<Book> searchByAuthor(String authorName){
        List<Book> founded = new ArrayList<Book>();

        for(Book i : books){
            if(i.getAuthor().toLowerCase().contains(authorName.toLowerCase())){
                founded.add (i);
            }
        }
        return founded;
    }

    /**
     * Provides searching book by name
     *
     * @param name book name
     * @return list of books which contains @param name
     */
    public List<Book> searchByName(String name){
        List<Book> founded = new ArrayList<Book>();

        for(Book i : books){
            if(i.getName().toLowerCase().contains(name.toLowerCase())){
                founded.add(i);
            }
        }
        return founded;
    }
    // TODO DELETE SEARCH


    /**
     * performs book borrowing and
     * changes its status to not available
     *
     * @param id book id
     * @return true if book borrowed successfully, otherwise false
     */
    public boolean borrowBook(int id) throws FileNotFoundException {
        int j = 0;
        for(Book i : books){
            if (i.getId() == id){
                if (i.getStatus().equals(Status.STATUS_UNAVAILABLE))
                    return false;
                i.setStatus(Status.STATUS_UNAVAILABLE);
                rows.get(j).set(6, "FALSE");
                flush();
                return true;
            }
            j ++;
        }
        flush();
        return true;
    }

    /**
     * Change status of the book from unavailable to available
     *
     * @param id book id
     * @return true if we can return book to library, otherwise false
     */
    public boolean returnBook(int id) throws FileNotFoundException {
        int j = 0;
        for(Book i : books){
            if(i.getId() == id){

                if (i.getStatus() == Status.STATUS_AVAILABLE)
                    return false;

                i.setStatus(Status.STATUS_AVAILABLE);
                rows.get(j).set(6, "TRUE");
                flush();
                return true;
            }
            j ++;
        }

        return false;
    }


    /**
     * @return free id, which can be used for new added book
     */
    public int getFreeId(){
        return rows.size();
    }

    /**
     * Add a new book into LibraryDatabase
     *
     * @param newBook book to add
     */
    public void addBook(Book newBook) throws FileNotFoundException {
        books.add (newBook);
        List<String> temp = new ArrayList<>();
        temp.add(newBook.getName());
        temp.add(newBook.getAuthor());
        temp.add(newBook.getCategory());
        temp.add(String.valueOf(newBook.getYear()));
        temp.add(String.valueOf(newBook.getPages()));
        String location = "[" + String.valueOf(newBook.getLocation().getShelf()) + ", " + String.valueOf(newBook.getLocation().getRow()) + ", " + String.valueOf(newBook.getLocation().getColumn()) + "]";
        temp.add(location);
        if (newBook.getStatus().equals(Status.STATUS_AVAILABLE))
            temp.add("TRUE");
        else
            temp.add("FALSE");
        temp.add(String.valueOf(newBook.getId()));
        rows.add(temp);
        flush();
    }
}

/**
 * This class extends LibraryDatabase
 */
class LibraryDatabaseClient extends LibraryDatabase{
    private static LibraryDatabaseClient instance;

    public LibraryDatabaseClient() throws FileNotFoundException {
        super();
    }

    /**
     * creates instance of LibraryDatabaseClient, but can
     * be called only inside LibraryDatabaseClient class.
     * This is done to implement Singleton pattern.
     *
     * Singleton implementation
     *
     * @return the only 1 existing instance in all program
     * @throws FileNotFoundException if LibraryDatabase can't be created
     */
    public static LibraryDatabaseClient getInstance() throws FileNotFoundException {
        if (instance == null)
            instance = new LibraryDatabaseClient();

        return instance;
    }
}


/**
 * This class provides basic methods
 * for working with database file
 */
class Database {
    protected List<List<String>> rows;
    protected final String databaseName;

    /**
     * creates database instance, reads database from file
     * and splits it into tokens for further computations
     *
     * @param fileName - name of database file
     * @throws FileNotFoundException - if file not found
     */
    protected Database(String fileName) throws FileNotFoundException {
        this.databaseName = fileName;
        Scanner scan = new Scanner(new FileReader(databaseName));
        rows = new ArrayList<List<String>>();

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            if (line.equals("")) continue;
            ArrayList<String> tokens = new ArrayList<>();

            int pos = 0;

            while (pos <= line.length() - 1) {
                StringBuilder currentToken = new StringBuilder();

                if (line.charAt(pos) == '"') {
                    pos++;
                    while (line.charAt(pos) != '"' || line.charAt(pos + 1) != ',') {
                        currentToken.append(line.charAt(pos));
                        pos++;
                    }
                    pos++;

                } else {
                    while (pos < line.length() && line.charAt(pos) != ',') {
                        currentToken.append(line.charAt(pos));
                        pos++;
                    }
                }
                pos++;
                tokens.add(currentToken.toString());
            }
            rows.add(tokens);
        }

        scan.close();
    }


    /**
     * Uploads changes in database to disk
     *
     * @throws FileNotFoundException if file can't be created
     */
    protected void flush() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(databaseName);

        for (List<String> row: rows){
            boolean first = true;
            for (String token: row){
                String tokenToWrite = token;
                if (token.contains(","))
                    tokenToWrite = '"' + token + '"';

                if (first)
                    first = false;
                else
                    tokenToWrite = "," + tokenToWrite;

                out.print(tokenToWrite);
            }
            out.println();
        }
        out.close();
    }
}