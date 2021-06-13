package sample.viewmodels;

import sample.Repository.ManagerRepo;
import sample.callBacks.IUserCallBack;
import sample.models.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
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
        if(model == null) {
            model =  new ManagerViewModel();
        }
        return model;
    }

    public synchronized  void promoteUser(String user_name, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
        System.out.println(user_name + "PROMOTION");
        repo.promoteUser(user_name);
        callBack.onSuccess();
    }

   public synchronized void getAllUsers(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSet set = repo.getAllUsers();
        List<Object> list = new ArrayList<>();
        while (set.next()){
            User user = resultSetParser.retrieveUser(set);
            list.add(user);
        }
        callBack.onSuccess(list);
   }

    public synchronized void getAllCheckout(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSet set = repo.getAllCheckout();
        List<Object> list = new ArrayList<>();
        while (set.next()){
            Checkout checkout = resultSetParser.retrieveCheckout(set);
            list.add(checkout);
        }
        callBack.onSuccess(list);
    }


    public synchronized void orderBook(String ISBN, int NOCopies, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
       repo.orderBook(ISBN,NOCopies);
       callBack.onSuccess();
    }

    public synchronized void confirmOrder(Integer order_id, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
       repo.confirmOrder(order_id);
       callBack.onSuccess();
    }

    public synchronized void addBook(Map<String,Object> map, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
        repo.addBook(map);
        callBack.onSuccess();
    }

    public synchronized void modifyBook(Map<String,Object> setAttribute , Map<String,Object> whereAttribute,IUserCallBack callBack) throws SQLException, ClassNotFoundException {
        repo.modifyBooks(setAttribute,whereAttribute);
        callBack.onSuccess();
    }

    public synchronized void removeBook(Map<String,Object> searchAttribute,IUserCallBack userCallBack) throws SQLException, ClassNotFoundException {
        repo.removeBooks(searchAttribute);
        userCallBack.onSuccess();
    }



    public synchronized void addPublisher(String address, String publisherName, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
        repo.addPublisher(address,publisherName);
        callBack.onSuccess();
    }

    /*TODO implement this*/
    public synchronized void addPublisher(String address, String publisherName,String phone ,IUserCallBack callBack) throws SQLException, ClassNotFoundException {
//        repo.addPublisher(address,publisherName);
//        callBack.onSuccess();
    }

    public synchronized void addCatagory(String catagoryName, IUserCallBack callBack) throws SQLException, ClassNotFoundException {
       repo.addCatagory(catagoryName);
       callBack.onSuccess();
    }

    public synchronized void removePublisher (String publisherName,IUserCallBack callBack) throws SQLException, ClassNotFoundException {
       repo.removePublisher(publisherName);
       callBack.onSuccess();
    }

    public synchronized void removeCatagory (String catagoryName,IUserCallBack callBack) throws SQLException, ClassNotFoundException {
       repo.removeCatagory(catagoryName);
       callBack.onSuccess();
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
    public synchronized void getOrders(IUserCallBack callBack) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ResultSet rs = repo.getOrders();
        List<Object> list = new ArrayList<>();
        while (rs.next()){
            Order order = resultSetParser.retrieveOrder(rs);
            list.add(order);
        }
        callBack.onSuccess(list);
    }


    public synchronized void getPublisher(Integer id,IUserCallBack callBack) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ResultSet set = repo.getPublisher(id);
        Publisher publisher = null ;
        if(set.next()) {
            publisher = resultSetParser.retrievePublisher(set);
        }
         callBack.onSuccess((List<Object>) publisher);
    }



    public synchronized void getAllCreditCards(IUserCallBack callBack) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSet rs = repo.getAllCreditCards();
        List<Object> list = new ArrayList<>();
        while (rs.next()){
            CreditCard card = resultSetParser.retireveCard(rs);
            list.add(card);
        }
        callBack.onSuccess(list);
    }

    public synchronized void getAllPublishers(IUserCallBack callBack) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ResultSet rs = repo.getAllPublishers();
        List<Object> list = new ArrayList<>();
        while (rs.next()){
            Publisher publisher = resultSetParser.retrievePublisher(rs);
            list.add(publisher);
        }
        callBack.onSuccess(list);
    }
}
