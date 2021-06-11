package sample.Repository;

import sample.models.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ManagerRepo extends UserRepo{

    private PreparedStatement promoteUserStatement;
    private PreparedStatement orderBookStatement;
    private PreparedStatement confirmOrderStatement;
    private PreparedStatement addBookStatement;
    private PreparedStatement addPublisherStatement;
    private PreparedStatement addCatagoryStatement;
    private PreparedStatement deleteCatagoryStatement;
    private PreparedStatement removePublisherStatement ;
    private PreparedStatement removeBookStatement ;
    private PreparedStatement modifyBookStatement ;
    private PreparedStatement getBookSales;
    private PreparedStatement getTopCustomers;
    private PreparedStatement getTotalLastMonthsSales;

    public ManagerRepo() throws SQLException, ClassNotFoundException {
        super();
        promoteUserStatement = con.prepareStatement("update user set manager=1 where user_name = ?");
        orderBookStatement = con.prepareStatement("insert into orders (isbn,nocopies) values (?,?)");
        confirmOrderStatement = con.prepareStatement("delete from orders where isbn = ?");
        addBookStatement = con.prepareStatement("insert into book " +
                " (isbn,title,price,publication_year,nocopies,threshold,publisher_id,catagory_id) values (?,?,?,?,?,?,?,?)");
        addPublisherStatement = con.prepareStatement("insert into publisher (address,publisher_name) values (?,?) ");
        addCatagoryStatement = con.prepareStatement("insert into catagory (catagory_name) values (?)");
        deleteCatagoryStatement = con.prepareStatement("delete from catagory where catagory_name = ? ");
        removePublisherStatement = con.prepareStatement("delete from publisher where publisher_name = ? ");
        getBookSales = con.prepareStatement("Select b.ISBN , b.Title , sum(c.NOCOpies) as Total_Saled_Copies\n" +
                "From Book AS b , Checkout as c \n" +
                "where b.ISBN = c.ISBN \n" +
                "AND c.Date >= DATE_ADD(NOW(),INTERVAL -90 DAY) \n" +
                "Group by b.ISBN \n" +
                "Order by Total_Saled_Copies desc\n" +
                "Limit 10;");
        getTopCustomers = con.prepareStatement("select u.User_Name,u.First_name , last_name , sum(b.price * c.NOCOpies) as Total_purchases \n" +
                "from user as u , checkout as c , book as b\n" +
                "where U.User_Name = c.User_name \n" +
                "and c.ISBN = b.ISBN\n" +
                "AND c.Date >= DATE_ADD(NOW(),INTERVAL -90 DAY)\n" +
                "group by user_name\n" +
                "order by Total_purchases desc");
        getTotalLastMonthsSales = con.prepareStatement("Select b.ISBN , b.Title ,b.price, sum(c.NOCopies) as Total_Sales\n" +
                "from book as b , checkout as c\n" +
                "where b.ISBN = c.ISBN \n" +
                "AND c.Date >= DATE_ADD(NOW(),INTERVAL -30 DAY)\n" +
                "group by b.ISBN \n" +
                "order by Total_Sales desc");
    }


    public void promoteUser(String user_name) throws SQLException {
        promoteUserStatement.setString(1,user_name);
        promoteUserStatement.executeUpdate();
    }

    public void orderBook(String ISBN, int NOCopies) throws SQLException {
     orderBookStatement.setString(1,ISBN);
     orderBookStatement.setInt(2,NOCopies);
     orderBookStatement.executeUpdate();
    }

    public void confirmOrder(String ISBN) throws SQLException {
     confirmOrderStatement.setString(1,ISBN);
     confirmOrderStatement.executeUpdate();
    }

    public void addBooks(List<Book> books) throws SQLException {
        int i=0;
      for(Book book : books){
          addBookStatement.setString(1,book.getISBN());
          addBookStatement.setString(2,book.getTitle());
          addBookStatement.setFloat(3,book.getPrice());
          addBookStatement.setDate(4,book.getPublication_year());
          addBookStatement.setInt(5,book.getNoCopies());
          addBookStatement.setInt(6,book.getThreshold());
          addBookStatement.setInt(7,book.getPublisher().getPublisher_id());
         // addBookStatement.setInt(8,book.getCategory());

          addBookStatement.addBatch();
          i++;
          if (i % 20 == 0 || i == books.size()) {
              addBookStatement.executeBatch(); // Execute every 20 items.
          }
      }
    }

    public void addPublisher(String address, String publisherName) throws SQLException {
        addPublisherStatement.setString(1,address);
        addPublisherStatement.setString(2,publisherName);
        addPublisherStatement.executeUpdate() ;
    }

    public void addCatagory(String catagoryName) throws SQLException {
        addCatagoryStatement.setString(1,catagoryName);
        addCatagoryStatement.executeUpdate() ;
    }

    public void removePublisher (String publisherName) throws SQLException {
        removePublisherStatement.setString(1,publisherName);
        removePublisherStatement.executeUpdate();
    }

    public void removeCatagory (String catagoryName) throws SQLException {
        deleteCatagoryStatement.setString(1,catagoryName);
        deleteCatagoryStatement.executeUpdate();
    }

    public void modifyBooks (Map<String,Object> setAttribute , Map<String,Object> whereAttribute) throws SQLException {

        String query = " update into book set " ;
        int sizeSet = setAttribute.size();
        int i = 0;
        for(String column : setAttribute.keySet()){
            query += column + "= ? ";
            if(i != sizeSet-1) query += ", ";
            i++;
        }

        if(whereAttribute == null){
            modifyBookStatement = con.prepareStatement(query) ;
            modifyBookStatement.executeUpdate(query);
            return;
        }

        int sizeWhere = whereAttribute.size();
        query += "where " ;
        i = 0;
        for(String column : whereAttribute.keySet()){
            query += column + "= ? ";
            if(i != sizeWhere-1) query += "and ";
            i++;
        }
        i = 1;
        modifyBookStatement = con.prepareStatement(query) ;
        for(Object value : setAttribute.values()){
            modifyBookStatement.setObject(i++,value);
        }
        for(Object value : whereAttribute.values()){
            modifyBookStatement.setObject(i++,value);
        }

        modifyBookStatement.executeUpdate(query);
    }

    public void removeBooks (Map<String,Object> searchAttribute) throws SQLException {
        String query = " delete from book ";

        if(searchAttribute == null){
            removeBookStatement = con.prepareStatement(query);
            removeBookStatement.executeUpdate();
            return;
        }

        int size = searchAttribute.size();
        int i = 0;
        query += " where " ;
        for(String column : searchAttribute.keySet()){
            query += column + "= ? ";
            if(i != size-1) query += "and ";
            i++;
        }
        removeBookStatement = con.prepareStatement(query);
        i = 1;
        for(Object value : searchAttribute.values()){
            removeBookStatement.setObject(i++,value);
        }
        removeBookStatement.executeUpdate();
    }

    public ResultSet getTopTenBooks() throws SQLException {
        return getBookSales.executeQuery();
    }
    public ResultSet getSalesLastMonth() throws SQLException{
        return getTotalLastMonthsSales.executeQuery();
    }
    public ResultSet getTop5CustomersLastThreeMonths() throws SQLException{
        return getTopCustomers.executeQuery();
    }
}
