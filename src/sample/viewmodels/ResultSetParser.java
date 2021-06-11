package sample.viewmodels;

import sample.models.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResultSetParser {

    private List<Field> bookFields;
    private List<Field> publisherFields;
    private List<Field> catagoryFields;
    private List<Field> userFields;

    public ResultSetParser(){
        bookFields = Arrays.asList(Book.class.getDeclaredFields());
        publisherFields = Arrays.asList(Publisher.class.getDeclaredFields());
        catagoryFields = Arrays.asList(Catagory.class.getDeclaredFields());
        userFields = Arrays.asList(User.class.getDeclaredFields());
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
        if(f.getType() == String.class){
            String value = null;
            try {
                value = set.getString(name);
                f.set(user, f.getType().getConstructor(String.class).newInstance(value));
                System.out.println(name + " " + value);
            } catch (SQLException e) {
            }
        }
        else if(f.getType() == Integer.class){
            Integer value = null;
            try {
                value = set.getInt(name);
                f.set(user, new Integer(value));
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
}
