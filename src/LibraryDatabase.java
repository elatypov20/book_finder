import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;


public class LibraryDatabase extends Database{
    private List<Book> books;

    private static LibraryDatabase instance;

    private LibraryDatabase(FileReader file){
        super(file);

        // TODO
        // implement parsing rows into Books
    }

    public LibraryDatabase getInstance(){
        // TODO
        // implement singleton pattern

        return null;
    }

    public List<Book> getBooks(){
        // TODO
        // return all books
        return null;
    }

    public List<Book> searchByAuthor(String authorName){
        // TODO
        return null;
    }

    public List<Book> searchByName(String name){
        // TODO
        return null;
    }

    public boolean borrowBook(){
        // TODO
        return false;
    }

    public void returnBook(int id){
        // TODO
    }

    public void addBook(){
        // TODO
        // if someone wants to add book to the library
        // (needed only for demonstrating OOP)
    }
}

class Database {
    protected List<List<String>> rows;
    protected FileReader file;

    protected Database(FileReader file){
        file = file;
        // TODO
        // implement reading rows from file
    }

    protected void updateRows(List<String> rows){
        // TODO
        // when some rows are changed
        // imlement uploading changes to the disk
        
    }
}