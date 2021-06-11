package sample.viewmodels;

import sample.Repository.ManagerRepo;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManagerViewModel extends UserViewModel{

    private ManagerRepo repo;
    private static ManagerViewModel model;
    private ManagerViewModel() throws SQLException, ClassNotFoundException {
        this.repo = new ManagerRepo();
    }

    public static ManagerViewModel get_instance() throws SQLException, ClassNotFoundException {
        if(model == null)
            return new ManagerViewModel();
        else return model;
    }

    public synchronized  void promoteUser(String user_name, IUserCallBack callBack) throws SQLException {
        repo.promoteUser(user_name);
        callBack.onSuccess();
    }

    public synchronized void orderBook(String ISBN, int NOCopies, IUserCallBack callBack) throws SQLException {
       repo.orderBook(ISBN,NOCopies);
       callBack.onSuccess();
    }

    public synchronized void confirmOrder(String ISBN, IUserCallBack callBack) throws SQLException {
       repo.confirmOrder(ISBN);
       callBack.onSuccess();
    }

    public synchronized void addBooks(List<Book> books, IUserCallBack callBack) throws SQLException {
       repo.addBooks(books);
       callBack.onSuccess();
    }

    public synchronized void addPublisher(String address, String publisherName, IUserCallBack callBack) throws SQLException {
        repo.addPublisher(address,publisherName);
        callBack.onSuccess();
    }

    public synchronized void addCatagory(String catagoryName, IUserCallBack callBack) throws SQLException {
       repo.addCatagory(catagoryName);
       callBack.onSuccess();
    }

    public synchronized void removePublisher (String publisherName,IUserCallBack callBack) throws SQLException {
       repo.removePublisher(publisherName);
       callBack.onSuccess();
    }

    public synchronized void removeCatagory (String catagoryName,IUserCallBack callBack) throws SQLException {
       repo.removeCatagory(catagoryName);
       callBack.onSuccess();
    }

    public synchronized void modifyBooks (Map<String,Object> setAttribute , Map<String,Object> whereAttribute,IUserCallBack callBack) throws SQLException {
        repo.modifyBooks(setAttribute,whereAttribute);
        callBack.onSuccess();
    }



    public synchronized void removeBooks (Map<String,Object> searchAttribute,IUserCallBack userCallBack) throws SQLException {
       repo.removeBooks(searchAttribute);
       userCallBack.onSuccess();
    }

    public synchronized void getTopTenBooks(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

      ResultSet rs = repo.getTopTenBooks();
      List<Object> list = new ArrayList<>();
      while (rs.next()){
          Book book = resultSetParser.retrieveBook(rs);
          book.setNoCopies(rs.findColumn("Total_Saled_Copies"));
          list.add(book);
      }
        callBack.onSuccess(list);
    }

    public synchronized void getSalesLastMonth(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ResultSet rs = repo.getSalesLastMonth();
        List<Object> list = new ArrayList<>();
       while (rs.next()){
           Book book = resultSetParser.retrieveBook(rs);
           book.setNoCopies(rs.findColumn("Total_Sales"));
           list.add(book);
       }
       callBack.onSuccess(list);
    }

    public synchronized void getTop5CustomersLastThreeMonths(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSet rs = repo.getTop5CustomersLastThreeMonths();
        List<Object> list = new ArrayList<>();
        while (rs.next()){
            User user = resultSetParser.retrieveUser(rs);
            list.add(user);
        }
        callBack.onSuccess(list);
    }
}
