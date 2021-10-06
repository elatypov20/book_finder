import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.StringBuilder;

public class LibraryDatabase extends Database{
    private List<Book> books;

    private static LibraryDatabase instance;

    private LibraryDatabase(String databaseName) throws FileNotFoundException{
        super(databaseName);

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
    protected final String databaseName;

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

//        for (List<String> row : rows)
//            System.out.println(row);

        scan.close();
    }

    protected void flush(){

    }
}