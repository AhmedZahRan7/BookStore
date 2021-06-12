package sample.models;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Book, Integer> selectedBooks;
    private Float totalPrice;
    private final int INITIAL_CAPACITY = 100;

    public Cart() {
        this.selectedBooks = new HashMap(this.INITIAL_CAPACITY);
        this.totalPrice = 0.0F;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public Cart(Book book) {
        this();
        this.addBook(book,  1);
    }

    public Cart(Book book, int noItem) {
        this();
        this.addBook(book, noItem);
    }
    public void addBook(Book book, int noItem) {
        Book selectedBook = this.getSelectedBook(book.getISBN());
        if(noItem > book.getNoCopies())
            throw new IndexOutOfBoundsException();
        if(selectedBook == null)
            this.selectedBooks.put(book, noItem);
        else
            this.selectedBooks.put(book, this.selectedBooks.get(book) + noItem);
        this.totalPrice += noItem * book.getPrice();
    }
    private Book getSelectedBook(String ISBN) {
        for(Book book : this.selectedBooks.keySet())
            if(book.getISBN() ==  ISBN)
                return book;
        return null;
    }
    public Map<Book,Integer> getSelectedBooks() {
        return this.selectedBooks;
    }
    public int getTotalNumberOfBooksInCart() {
        int temp = 0;
        for(Integer i : this.selectedBooks.values())
            temp += i;
        return temp;
    }
    public int getNumberUniqueBooks() {
        return this.selectedBooks.size();
    }
    public int getBookNumberOnTheCart(String ISBN) {
        for(Book book : this.selectedBooks.keySet())
            if(book.getISBN() ==  ISBN)
                return this.selectedBooks.get(book);
        return 0;
    }
    public void showCart() {
        for(Book book : this.selectedBooks.keySet()) {
            System.out.println(book.getTitle() + " " + this.selectedBooks.get(book));
        }
    }
    public void removeBook(String ISBN) {
        Book book = this.getSelectedBook(ISBN);
        if(this.selectedBooks.get(book) == 1)
            this.selectedBooks.remove(book);
        this.selectedBooks.put(book, this.selectedBooks.get(book)-1);
    }
}