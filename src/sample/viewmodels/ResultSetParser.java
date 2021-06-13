package sample.viewmodels;

import sample.models.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class ResultSetParser<T> {
    private List<Field> bookFields;
    private List<Field> publisherFields;
    private List<Field> catagoryFields;
    private List<Field> userFields;
    private List<Field> orderFields;
    private List<Field> checkoutFields;
    private List<Field> creditCardFields;
    public ResultSetParser(){
        bookFields = Arrays.asList(Book.class.getDeclaredFields());
        publisherFields = Arrays.asList(Publisher.class.getDeclaredFields());
        catagoryFields = Arrays.asList(Catagory.class.getDeclaredFields());
        userFields = Arrays.asList(User.class.getDeclaredFields());
        orderFields = Arrays.asList(Order.class.getDeclaredFields());
        checkoutFields = Arrays.asList(Checkout.class.getDeclaredFields());
        creditCardFields = Arrays.asList(CreditCard.class.getDeclaredFields());

        setAccessbility(creditCardFields);
        setAccessbility(orderFields);
        setAccessbility(checkoutFields);
        setAccessbility(bookFields);
        setAccessbility(publisherFields);
        setAccessbility(catagoryFields);
        setAccessbility(userFields);
    }

    protected void setAccessbility(List<Field> list){
        for(Field f : list)
            f.setAccessible(true);
    }
    protected User retrieveUser(ResultSet set) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        User user = new User();
        setAllFields(set, user, userFields);
        return user;
    }
    protected Checkout retrieveCheckout(ResultSet set) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Checkout checkout = new Checkout();
        setAllFields(set, checkout, checkoutFields);
        return checkout;
    }

    protected Order retrieveOrder(ResultSet set) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        Order order = new Order();
        setAllFields(set, order, orderFields);
        return order;
    }

    protected Book retrieveBook(ResultSet set) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Book book = new Book();
        Publisher publisher = new Publisher();
        Catagory catagory = new Catagory();

        setAllFields(set, book, bookFields);
        setAllFields(set, publisher, publisherFields);
        setAllFields(set, catagory, catagoryFields);
        book.setPublisherName(publisher);
        book.setCatagory(catagory);
        return book;
    }

    protected void setField(ResultSet set, Object user, String name, Field f)throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
      //  System.out.println(f.getName() + " " + f.getType());
        if(f.getType() == String.class){
            String value = null;
            try {
                value = set.getString(name);
                f.set(user, value);
                System.out.println(name + " " + value);
            } catch (SQLException e) {
            }
        }
        else if( f.getType() == Integer.class) {
            Integer value = null;
            try {
                value = set.getInt(name);
                f.set(user, value);
                System.out.println(name + " " + value);
            } catch (SQLException throwables) {
            }
        }else if(f.getType() == Date.class){
            Date value = null;
            try {
                value = set.getDate(name);
                f.set(user, value);
                System.out.println(name + " " + value);
            } catch (SQLException throwables) {
            }
        }else if(f.getType() == Float.class){
            Float value = null;
            try {
                value = set.getFloat(name);
                f.set(user, value);
                System.out.println(name + " " + value);
            } catch (SQLException throwables) {
            }
        }
    }

    protected void setAllFields(ResultSet set, Object user, List<Field> fields) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        for (Field f : fields)
            f.setAccessible(true);
        for (Field f : fields) {
            String name = f.getName();
            setField(set, user, name, f);
        }
    }

    public Publisher retrievePublisher(ResultSet set) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        Publisher publisher = new Publisher();
        setAllFields(set,publisher , publisherFields);
        return publisher;
    }

    public CreditCard retireveCard(ResultSet rs) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        CreditCard card = new CreditCard();
        setAllFields(rs,card ,creditCardFields);
        return card;
    }
}