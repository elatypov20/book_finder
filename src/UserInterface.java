import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInterface{

    private BookFilter filter;
    private final Scanner myObj;

    public UserInterface(BookFilter filter){
        this.filter = filter;
        myObj = new Scanner(System.in);
    }

    public void addKeywordFilter(String keyword){
        this.filter = new KeywordFilter(filter, keyword);
    }

    public void addDateFilter(int start, int end){
        this.filter = new DateFilter(filter, start, end);
    }

    public void addAuthorFilter(String name){ this.filter = new AuthorFilter(filter, name); }

    public void resetFilters(){
        this.filter = null;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /**
     * manages user to enter valid integer input
     * @return int
     */
    int getCommand(){

//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        int foo;

        while (true){
            try {
                System.out.print("Enter number >> ");

                String input = myObj.nextLine();  // Read user input
                foo = Integer.parseInt(input);
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter only digit!!!");
            }

        }
        return foo;
    }


    void readKeywords(){
        System.out.print("Enter keywords: ");
        String input = myObj.nextLine();

        for (String keyword: input.split(" "))
            addKeywordFilter(keyword);
    }

    void readDates(){
        int startDate = 0, endDate = 100000;

        System.out.print("Enter start year: ");
        String sdate = myObj.nextLine();

        if (isInteger(sdate)){
            startDate = Integer.parseInt(sdate);
        }


        System.out.print("Enter end year: ");
        String edate = myObj.nextLine();

        if (isInteger(edate)){
            endDate = Integer.parseInt(edate);
        }

        if (startDate > endDate){
            System.out.println("start date can't be greater than end");
            return;
        }

        addDateFilter(startDate, endDate);
    }


    void filterMain(LibraryDatabaseClient lb){
        System.out.println("\n-----------------------------------------");
        System.out.println("FILTERING");
        System.out.println("-----------------------------------------");

        System.out.println("REMINDER: If you don't want to filer by specific elements, just leave input empty!");

        readKeywords();
        readDates();

        try {
            showBooks(lb, filter.filter(LibraryDatabaseClient.getInstance().getBooks()));
        } catch (Exception ignored){
        }
        resetFilters();
    }


    /**
     * To print Book description and allows user to borrow book if available
     * @param lb instance of LibraryDatabase class
     * @param book book that's chosen by user
     */
    void bookDescription(LibraryDatabaseClient lb, Book book) throws FileNotFoundException{

        System.out.println("\n-----------------------------------------");
        System.out.println("BOOK DESCRIPTION");
        System.out.println("-----------------------------------------");

        System.out.println("ID: "+book.getId());
        System.out.println("Name: "+ book.getName());
        System.out.println("Author: "+book.getAuthor());
        System.out.println("Category: "+book.getCategory());
        System.out.println("Year: "+book.getYear());
        System.out.println("Pages: "+book.getPages());
        Location location = book.getLocation();
        System.out.println("Location -> "+"shelf: "+location.getShelf()+" column: "+location.getColumn()+" row: "+location.getRow());
        System.out.println("Status: "+ (book.getStatus() == Status.STATUS_AVAILABLE ? "Available" : "Not available"));

        if (book.getStatus() == Status.STATUS_AVAILABLE){
            System.out.println("------------------------------------");
            System.out.println("0 -> Borrow book");
            System.out.println("1 -> Go Back");
            int n = getCommand();

            while (true){
                if (n == 0) {
                    lb.getInstance().borrowBook(book.getId());
                    System.out.println("You have successfully borrowed book with id = " + book.getId());
                    break;
                }
                else if(n == 1){
                    resetFilters();
                    startUISession();
                }
                else {
                    System.out.println("Please enter only command numbers!");
                    n = getCommand();
                }

            }

        }
        else {
            System.out.println("Sorry you cannot borrow this book!");
        }
        startUISession();
    }

    void showBooks(LibraryDatabaseClient lb, List<Book> books) throws FileNotFoundException{
        resetFilters();
        if (books.size()>0){
            System.out.println("FOUND RESULTS: ");
            for (int i = 0; i < books.size(); i ++){
                System.out.println(i+" --> "+books.get(i).getName() +" BY: "+books.get(i).getAuthor());
            }

            System.out.println("Choose book from results");

            int n = getCommand();

            while (true){
                if (n<=books.size()){
                    bookDescription(lb, books.get(n));
                    break;

                }
                else{
                    System.out.println("Enter only number before results");
                    n = getCommand();

                }

            }

        }
        else {
            System.out.println("No results ");
        }
    }

    /**
     * Handles search typing
     * Prints suitable results
     *
     * @param lb library database
     * @param option "author" or "name" (mode)
     */
     void searchType(LibraryDatabaseClient lb, String option) throws FileNotFoundException{

        System.out.println("\n-----------------------------------------");
        if (option.equals("name")){
            System.out.println("SEARCHING BY NAME");
        } else {
            System.out.println("SEARCHING BY AUTHOR");
        }

        Scanner myObj = new Scanner(System.in);

        System.out.println("-----------------------------------------");

        System.out.println("0 -> Go Back");
        System.out.print("Please choose commands or Search: ");
        String input = myObj.nextLine();

        List<Book> books = new ArrayList<>();


        if (input.equals("0")) {
            doSearch();
        }
        else {
            if (option.equals("name")){
                addKeywordFilter(input);
            }
            else {
                addAuthorFilter(input);
            }

            if (filter != null)
                books = filter.filter(lb.getBooks());
        }

        System.out.println("--------------------------------");

        showBooks(lb, books);
        resetFilters();
    }



    /**
     * Handles searching action
     *
     * Can perform searching by book name or by author
     *
     */
    void doSearch() throws FileNotFoundException{
        System.out.println("\n-----------------------------------------");
        System.out.println("SEARCHING!");

        System.out.println("-----------------------------------------");

        System.out.println("Please choose following commands:");
        System.out.println("0 -> Search by Name");
        System.out.println("1 -> Search by Author");
        System.out.println("2 -> Go Back");

        int n = getCommand();

        while (true) {
            if (n == 0) {
                searchType(LibraryDatabaseClient.getInstance(), "name");
                resetFilters();
                break;
            } else if (n == 1) {
                searchType(LibraryDatabaseClient.getInstance(), "author");
                resetFilters();
                break;
            } else if (n == 2) {
                startUISession();
                break;
            } else {
                System.out.println("Please enter only command numbers!");
                n = getCommand();
            }
        }
    }


    /**
     * allows adding book  to library dynamically
     * (if for example student wants to make book donation for library)
     *
     * @param lb LibraryDatabase instance
     */
    void addBook(LibraryDatabaseClient lb) throws FileNotFoundException{
        System.out.println("\n-----------------------------------------");
        System.out.println("ADDING NEW BOOK");

        System.out.println("Please add information about new Book!");
//        Scanner myObj = new Scanner(System.in);

        System.out.println("-----------------------------------------");

        System.out.print("Book name: ");
        String name = myObj.nextLine();

        System.out.print("Book Author name: ");
        String author = myObj.nextLine();

        System.out.print("Book category: ");
        String category = myObj.nextLine();

        System.out.print("Realized year: ");
        int year = Integer.parseInt(myObj.nextLine());

        System.out.print("Number of Pages: ");
        int pages = Integer.parseInt(myObj.nextLine());

        System.out.print("Number of Shelf: ");
        int shelf = Integer.parseInt(myObj.nextLine());

        System.out.print("Number of Row: ");
        int row = Integer.parseInt(myObj.nextLine());

        System.out.print("Number of Column: ");
        int column = Integer.parseInt(myObj.nextLine());

        Book newBook = new Book(lb.getFreeId(), author, name, category, pages, Status.STATUS_AVAILABLE, new Location(shelf, row, column), year);
        lb.addBook(newBook);

        System.out.println("Book is successfully added!");

        startUISession();
    }


    /**
     * Handles returning book action by taking book id parameter
     * @param lb LibraryDatabase class instance
     */
    void returnBook(LibraryDatabaseClient lb) throws FileNotFoundException{
        System.out.println("\n-----------------------------------------");
        System.out.println("RETURNING BOOK");
        System.out.println("-----------------------------------------");
        int n = getCommand();


        if (lb.returnBook(n)){

            System.out.println("<Book has been successfully returned>");
        }
        else {
            System.out.println("<Book cannot be returned!!!>");
        }


        startUISession();
    }

    /**
     *  This function call first it's main page where user can do 4 function
     *      Searching book
     *      return book
     *      add new book
     *      exit from program
     */
    public void startUISession() throws FileNotFoundException{
        System.out.println("-----------------------------------------");
        System.out.println("Welcome to Book Finder");
        System.out.println("-----------------------------------------");
        System.out.println("Please choose following commands:");
        System.out.println("0 -> Search");
        System.out.println("1 -> Return book");
        System.out.println("2 -> Add new book");
        System.out.println("3 -> Filter books");
        System.out.println("4 -> Exit");

        int n = getCommand();

        while (true){

            if (n==0){

                doSearch();
                break;
            }
            else if (n==1){
                returnBook(LibraryDatabaseClient.getInstance());
                break;
            }
            else if (n==2){
                addBook(LibraryDatabaseClient.getInstance());
                break;
            }
            else if(n==3){
                filterMain(LibraryDatabaseClient.getInstance());
                break;
            }
            else if (n==4){
                System.out.println("Good bye!");
                break;
            }

            else {
                System.out.println("Please enter only command numbers!");
                n = getCommand();
            }
        }
    }
}