package sample.models;

public class BookSales {
    private Book book;
    private int amount;

    public BookSales(Book book, int amount) {
        this.book = book;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public int getAmount() {
        return amount;
    }
}
