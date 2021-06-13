package sample.models;



public class Checkout {

    private Integer Checkout_id;
    private String ISBN;
    private String user_name;
    private String date;
    private Integer nocopies;

    public Checkout(int checkout_id, String ISBN, String user_name, String date, int noCopies) {
        this.Checkout_id = checkout_id;
        this.ISBN = ISBN;
        this.user_name = user_name;
        this.date = date;
        this.nocopies = noCopies;
    }

    public Checkout() {

    }

    public int getCheckout_id() {
        return Checkout_id;
    }

    public void setCheckout_id(int checkout_id) {
        this.Checkout_id = checkout_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNocopies() {
        return nocopies;
    }

    public void setNocopies(int nocopies) {
        this.nocopies = nocopies;
    }
}
