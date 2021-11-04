import java.util.ArrayList;
import java.util.List;

/**
 * This class returning list of books by filtering name and date
 */
abstract class BookFilter{
    protected BookFilter component;

    /**
     *
     * @param comp parent filter
     */
    public BookFilter(BookFilter comp){
        this.component = comp;
    }


    /**
     *
     * @param books list of Book class
     * @return list of filtered Book class
     */
    public abstract List<Book> filter(List<Book> books);
}

/**
 *  This class extends BookFilter class
 *  This class return list of books filtered by keyword
 */
class KeywordFilter extends BookFilter{
    private final String keyword;

    /**
     *
     * @param comp parent filter
     * @param keyword filtering by this string
     */
    public KeywordFilter(BookFilter comp, String keyword){
        super(comp);
        this.keyword = keyword;
    }

    /**
     *
     * @param books list of Book class
     * @return list of filtered Book class
     */
    @Override
    public List<Book> filter(List<Book> books) {
        ArrayList<Book> filtered = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().contains(this.keyword)) {
                filtered.add(book);
            }
        }

        if (component == null)
            return filtered;
        return component.filter(filtered);
    }
}

/**
 * The class extends by BookFilter class
 * The class return list of filtered books by date
 */
class DateFilter extends BookFilter{
    private final int startYear, endYear;

    /**
     *
     * @param comp parent filter
     * @param startYear starting range
     * @param endYear ending range
     */
    public DateFilter(BookFilter comp, int startYear, int endYear){
        super(comp);
        this.startYear = startYear;
        this.endYear = endYear;
    }


    /**
     *
     * @param books list of Book class
     * @return list of filtered Book class
     */
    @Override
    public List<Book> filter(List<Book> books) {
        ArrayList<Book> filtered = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() <= this.endYear && this.startYear <= book.getYear()) {
                filtered.add(book);
            }
        }

        if (component == null)
            return filtered;
        return component.filter(filtered);
    }
}

/**
 * The AuthorFilter class extended BookFilter class
 * The class return list of
 */
class AuthorFilter extends BookFilter{

    private final String authorName;

    /**
     *
     * @param comp parent filter
     * @param authorName filtering by this author name
     */
    public AuthorFilter(BookFilter comp, String authorName){
        super(comp);
        this.authorName=authorName;
    }

    /**
     *
     * @param books list of Book class
     * @return list of filtered Book class
     */
    @Override
    public List<Book> filter(List<Book> books){
        ArrayList<Book> filtered=new ArrayList<>();

        for(Book book : books){
            if(book.getAuthor().contains(authorName)){
                filtered.add(book);
            }
        }
        if(component == null)
            return filtered;
        return component.filter(filtered);
    }
}