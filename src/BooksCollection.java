/**
 * This class is a container for books
 * (implements Iterator pattern)
 */
public class BooksCollection {
    private BooksCollectionNode first;
    private BooksCollectionNode last;
    private int size;

    /**
     * Constructor
      */ 
    public BooksCollection(){
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 
     * @param book class Book
     * Adding book to the collection                 
     */
    public void add(Book book) {
        if (size == 0) {
            first = new BooksCollectionNode(book, null);
            last = first;
        }
        else {
            BooksCollectionNode newBookNode = new BooksCollectionNode(book);
            last.setNextNode(newBookNode);
            last = newBookNode;
        }
        size ++;
    }

    /**
     * 
     * @param i int
     * @return book on i-th position
     */
    public Book get(int i){
        BooksCollectionNode tmp = first;
        if(size - 1 < i)
            return null;
        while(i != 0){
            tmp = tmp.getNextNode();
            i --;
        }
        return tmp.getValue();
    }

    /**
     * 
     * @return size of collection
     */
    public int size(){
        return size;
    }

    /**
     * 
     * @return BooksCollectionIterator class
     */
    public BooksCollectionIterator getIterator(){
        return new BooksCollectionIterator(this);
    }

}

/**
 * This class provides Node for BooksCollection
 * (needed for implementation)
 */
class BooksCollectionNode {
    private final Book value;
    private BooksCollectionNode next;

    /**
     *  Books collection node for if there is next element in collection
     * @param value
     * @param next
     */
    public BooksCollectionNode(Book value, BooksCollectionNode next){
        this.value = value;
        this.next = next;
    }

    /**
     * BooksCollectionNode if there is no next element in collections
     * @param value
     */
    public BooksCollectionNode(Book value){
        this.value = value;
        this.next = null;
    }

    /**
     * Get current node value
     * @return value
     */
    public Book getValue() {
        return value;
    }

    /**
     * Get next node
     * @return next node
     */
    public BooksCollectionNode getNextNode() {
        return next;
    }

    /**
     * Set next node
     * @param node 
     */
    public void setNextNode(BooksCollectionNode node){
        this.next = node;
    }

}
