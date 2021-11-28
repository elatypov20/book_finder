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

    public abstract boolean checker(Book book);

    /**
     *
     * @param books list of Book class
     * @return list of filtered Book class
     */
    public BooksCollection filter(BooksCollection books){
        BooksCollection filtered = new BooksCollection();

        BooksCollectionIterator bi = books.getIterator();
        while (bi.hasNext()) {
            Book book = bi.getNext();
            if (checker(book))
                filtered.add(book);
        }

        if (component == null)
            return filtered;


        // If there is no elements in collection that
        // satisfy condition of our filter, then there is no
        // need to check resulting values in other filters
        // because due to this filter we already know that
        // result will be an empty array
        // (Chain of Responsibility pattern)
        if (filtered.size() == 0)
            return filtered;

        return component.filter(filtered);
    }
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


    @Override
    public boolean checker(Book book) {
        return book.getName().toLowerCase().contains(this.keyword.toLowerCase());
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


    @Override
    public boolean checker(Book book) {
        return book.getYear() <= this.endYear && this.startYear <= book.getYear();
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


    @Override
    public boolean checker(Book book) {
        return book.getAuthor().toLowerCase().contains(authorName.toLowerCase());
    }
}