package sample.models;


import java.util.ArrayList;
import java.util.List;

import sample.models.Book;

public class Cart {

    private class Item {
        public Book book;
        public int noItem;

        public Item(Book book) {
            this.book = book;
            this.noItem = 1;
        }

        public Item(Book book, int noItem) {
            this.book = book;
            this.noItem = noItem;
        }

    }

    private List<Item> selectedBooks;
    private float totalPrice;

    public Cart() {
        this.selectedBooks = new ArrayList<>(10);
        this.totalPrice = 0;
    }

    public Cart(Book bookName) {
        this();
        this.selectedBooks.add(new Item(bookName));

    }

    public Cart(Book bookName, int noItem) {
        this();
        this.selectedBooks.add(new Item(bookName, noItem));
    }

    public void addBook(Book book, int noItem) {
        this.selectedBooks.add(new Item(book, noItem));
        this.totalPrice += noItem * book.getPrice();
    }

    public void showCart() {
        for(Item item : this.selectedBooks)
            System.out.println(item.book.getTitle() + " " + item.noItem);
    }
}
