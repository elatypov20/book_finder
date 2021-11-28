/**
 * The BooksCollection concrete iterator class
 * 
 */
public class BooksCollectionIterator {
    BooksCollection collection;
    int current;

    public BooksCollectionIterator(BooksCollection collection){
        this.collection = collection;
        this.current = 0;
    }

    // gets next element from collection
    Book getNext(){
        return collection.get(current++);
    }

    // checks if there is next element
    boolean hasNext(){
        return this.current < collection.size();
    }
}