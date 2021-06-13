package sample.models;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Book, Integer> selectedBooks;
    private double totalPrice;
    private final Integer INITIAL_CAPACITY = 100;
    private static DecimalFormat df = new DecimalFormat("0.00");
    public Cart() {
        this.selectedBooks = new HashMap(this.INITIAL_CAPACITY);
        this.totalPrice = 0.0F;
    }

    public Cart(Book book) {
        this();
        this.addBook(book,  1);
    }

    public Cart(Book book, int noItem) {
        this();
        this.addBook(book, noItem);
    }

    public void addBook(Book book, int noItem) throws IndexOutOfBoundsException {
        Book selectedBook = this.getSelectedBook(book.getISBN());
        if(selectedBook == null)
            this.selectedBooks.put(book, noItem);
        else if(this.selectedBooks.get(book)+noItem > book.getNoCopies())
            throw (new IndexOutOfBoundsException("No remaining copies to add"));

        else
            this.selectedBooks.put(book, this.selectedBooks.get(book) + noItem);
        this.totalPrice += noItem * book.getPrice();
        System.out.println(book.getNoCopies()+"******************"+this.selectedBooks.get(book));
    }

    private Book getSelectedBook(String ISBN) {
        for(Book book : this.selectedBooks.keySet())
            if(book.getISBN().equals(ISBN))
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
        if(this.selectedBooks.get(book) == 1) {
            this.selectedBooks.remove(book);
            this.totalPrice -= book.getPrice();
            return;
        }
        this.totalPrice -= book.getPrice();
        this.selectedBooks.put(book, this.selectedBooks.get(book)-1);
    }

    public double getTotalPurchase(){
        this.totalPrice = Math.round(this.totalPrice * 100.0) / 100.0;
        return totalPrice;
    }
}