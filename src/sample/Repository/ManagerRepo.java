package sample.Repository;

import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;

import java.sql.Date;
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
    private PreparedStatement getPublisherStatement;
    private PreparedStatement addUserStatement;
    public ManagerRepo() throws SQLException, ClassNotFoundException {
        super();
        getPublisherStatement = con.prepareStatement("select from publisher where publisher_id = ?");
        promoteUserStatement = con.prepareStatement("update user set manager=1 where user_name = ?");
        orderBookStatement = con.prepareStatement("insert into orders (isbn,nocopies) values (?,?)");
        confirmOrderStatement = con.prepareStatement("delete from orders where orders_id = ?");
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




    public synchronized ResultSet getAllUsers() throws SQLException {
        String query = "select * from user";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
    }



    public synchronized ResultSet getAllPublishers() throws SQLException {
        String query = "select * from publisher";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
    }

    public synchronized ResultSet getAllCheckout() throws SQLException {
        String query = "select * from checkout";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
    }

    public ResultSet getPublisher (Integer publisher_id ) throws SQLException {
        getPublisherStatement.setInt(1,publisher_id);
        return  getPublisherStatement.executeQuery();
    }


    public void orderBook(String ISBN, int NOCopies) throws SQLException {
     orderBookStatement.setString(1,ISBN);
     orderBookStatement.setInt(2,NOCopies);
     orderBookStatement.executeUpdate();
    }

    public void confirmOrder(Integer orders_id) throws SQLException {
     confirmOrderStatement.setInt(1,orders_id);
     confirmOrderStatement.executeUpdate();
    }

    public void addBook(Map<String,Object> map) throws SQLException {
        addBookStatement.setString(1, (String) map.get(SearchContract.ISBN));
        addBookStatement.setString(2,(String) map.get(SearchContract.TITLE));
        addBookStatement.setFloat(3,(Float) map.get(SearchContract.PRICE));
        addBookStatement.setString(4,(String) map.get(SearchContract.PUBLICATION_YEAR));
        addBookStatement.setInt(5,(Integer) map.get(SearchContract.NOCOPIES));
        addBookStatement.setInt(6,(Integer) map.get(SearchContract.THRESHOLD));
        addBookStatement.setInt(7,(Integer) map.get(SearchContract.PUBLISHER_ID));
        addBookStatement.setInt(8,(Integer) map.get(SearchContract.CATEGORY_ID));
        addBookStatement.executeUpdate();
    }

    public ResultSet getOrders() throws SQLException {
        String query = "select * from orders";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
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
    String query = "update book set ";
    int sizeSet = setAttribute.size();
    int i = 0;
    for(String column : setAttribute.keySet()){
        query += (column + " =? ");
        if(i != sizeSet-1) query += ", ";
        i++;
    }
    if(whereAttribute == null){
        modifyBookStatement = con.prepareStatement(query) ;
        modifyBookStatement.executeUpdate();
        return;
    }
    int sizeWhere = whereAttribute.size();
    query += "where " ;
    i = 0;
    for(String column : whereAttribute.keySet()){
        query += column + " =? ";
        if(i != sizeWhere-1) query += "and ";
        i++;
    }
    System.out.println(query);
    i = 1;
    modifyBookStatement = con.prepareStatement(query) ;
    for(Map.Entry<String,Object> entry : setAttribute.entrySet()){
        if(entry.getKey().equals(SearchContract.CATEGORY_ID)){
            Object id = getCatagroy((String) entry.getValue());
            modifyBookStatement.setObject(i++,id);
        }else if(entry.getKey().equals(SearchContract.PUBLISHER_ID)){
            Object id = getPublisherWithName((String) entry.getValue());
            modifyBookStatement.setObject(i++,id);
        }else{
            modifyBookStatement.setObject(i++,entry.getValue());
        }
    }
    for(Object value : whereAttribute.values()){
        System.out.println(value);
        modifyBookStatement.setObject(i++,value);
    }
    modifyBookStatement.executeUpdate();
}
private Object getPublisherWithName(String value) throws SQLException {
    String query = "select publisher_id from publisher where publisher_name =?";
    PreparedStatement st = con.prepareStatement(query);
    st.setString(1,value);
    ResultSet set = st.executeQuery();
    if(set.next())
        return set.getObject(1);
    return null;
}
private Object getCatagroy(String value) throws SQLException {
    String query = "select catagory_id from catagory where catagory_name =?";
    PreparedStatement st = con.prepareStatement(query);
    st.setString(1,value);
    ResultSet set = st.executeQuery();
    if(set.next())
      return set.getObject(1);
    return null;
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


    public ResultSet getAllCreditCards() throws SQLException {
        String query = "select * from credit_card";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
    }
}
