package sample.viewmodels;

import sample.Repository.UserRepo;
import sample.callBacks.IUserCallBack;
import sample.models.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserViewModel {

    private UserRepo repo;
    private Cart userCartBooks;
    private Map<String, Book> searchResult;
    protected ResultSetParser resultSetParser;
    private static UserViewModel model;
    protected UserViewModel() throws SQLException, ClassNotFoundException {
        searchResult = new HashMap<>();
        userCartBooks = new Cart();
        resultSetParser = new ResultSetParser();
        repo = new UserRepo();
    }

    public static UserViewModel get_instance() throws SQLException, ClassNotFoundException {
        if(model == null) {
            model = new UserViewModel();
        }
        return model;
    }

    public synchronized void getUser(String userName, String password, IUserCallBack callBack) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ResultSet set = repo.getUser(userName,password);
        User user = null;
        if(set.next()) {
             user = resultSetParser.retrieveUser(set);
        }
        callBack.onSuccess(user);
    }

    public synchronized void getBook(String ISBN ,IUserCallBack callBack) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ResultSet rs = repo.getBookWithIsbn(ISBN);
        Book book = null;
        if(rs.next()) {
            book = resultSetParser.retrieveBook(rs);
        }
        callBack.onSuccess(book);
    }

    public synchronized void getBooks(Map<String,Object> searchAttribute ,IUserCallBack callBack) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ResultSet set = repo.getBooks(searchAttribute);
        Map<String,Book> bookMap = new HashMap<>();
        while (set.next()) {
            Book book = resultSetParser.retrieveBook(set);
            bookMap.put(book.getISBN(), book);
        }
        searchResult = bookMap;
        callBack.onSuccess(new ArrayList<>(bookMap.values()));
    }


    public  Cart addToCart(String isbn,Integer NOCopies) throws IndexOutOfBoundsException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(searchResult.containsKey(isbn)) {
            if(searchResult.get(isbn).getNoCopies() < NOCopies)
                throw new IndexOutOfBoundsException();
            else
                userCartBooks.addBook(searchResult.get(isbn),NOCopies);
        }
        else{
            ResultSet rs = repo.getBookWithIsbn(isbn);
            Book book = null;
            if(rs.next()) {
                book = resultSetParser.retrieveBook(rs);
                if (book.getNoCopies() < NOCopies)
                    throw new IndexOutOfBoundsException();
                else{
                    userCartBooks.addBook(book, NOCopies);
                }
            }else throw new SQLException();
        }
        return userCartBooks;
    }

    public Cart getCart() {
        return userCartBooks;
    }

    public void removeCart(){
        userCartBooks = new Cart();
    }
    public void removeFromCart(String isbn) {
        for(Map.Entry<Book,Integer> book : userCartBooks.getSelectedBooks().entrySet()){
            if(book.getKey().getISBN().equals(isbn)){
                userCartBooks.getSelectedBooks().remove(book);
                break;
            }
        }
    }

    public synchronized void addCreditCard(String cardNo,String expireDate,String userName,IUserCallBack userCallBack) throws  SQLException{
        repo.addCreditCard(cardNo,expireDate,userName);
        userCallBack.onSuccess();
    }
    public synchronized void removeCreditCard(String cardID,IUserCallBack userCallBack) throws SQLException {
      repo.removeCreditCard(cardID);
      userCallBack.onSuccess();
    }

    public synchronized void writeCart(Cart cart, String userName, IUserCallBack callBack) throws SQLException {
      repo.writeCart(cart.getSelectedBooks(),userName);
      callBack.onSuccess();
    }

    public synchronized void updateUser(String userName, User newUser, IUserCallBack callBack) throws SQLException {
//        callBack.onSuccess();
    }

}
