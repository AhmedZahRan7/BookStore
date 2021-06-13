package sample.Repository;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;

import java.sql.*;
import java.util.Map;

public class UserRepo {

    protected Connection con;
    protected PreparedStatement selectUserStatement;
    protected PreparedStatement selectBookStatement;
    protected PreparedStatement selectCartStatement;
    protected PreparedStatement writeCartStatement;
    protected PreparedStatement deleteCartStatement;
    protected PreparedStatement addCreditCard;
    protected PreparedStatement removeCreditCard;
    private PreparedStatement getCatagoryStatement;
    protected  PreparedStatement addUserStatement;

    protected PreparedStatement selectBookWithIsbn;
    public UserRepo() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://197.48.77.138:3306/mydb?rewriteBatchedStatements=true", "zezo", "zezo");
        selectUserStatement = con.prepareStatement("select * from user where user_name = ? and password = ? ");
        writeCartStatement = con.prepareStatement("insert into checkout (isbn,nocopies,date,user_name) values (?,?,?,?)");
        deleteCartStatement = con.prepareStatement("delete from checkout where user_name = ?");
        addCreditCard = con.prepareStatement("insert into CREDIT_CARD (Card_id,Exp_date,User_Name) values (?,?,?)");
        removeCreditCard = con.prepareStatement("delete from Credit_CARD where Card_id = ?");
        selectBookWithIsbn = con.prepareStatement("select * from book where isbn = ?");
        selectUserStatement = con.prepareStatement("select * from user where user_name = ? and password = ? ");
        selectCartStatement = con.prepareStatement("select * from checkout where user_name = ?");
        writeCartStatement = con.prepareStatement("insert into checkout (isbn,nocopies,date,user_name) values (?,?,?,?)");
        deleteCartStatement = con.prepareStatement("delete from checkout where user_name = ?");
        getCatagoryStatement = con.prepareStatement("select from catagory where catagory_id = ?");
        addUserStatement = con.prepareStatement("insert into user (user_name,first_name,last_name,email,password" +
                ",shipping_address,manager) values (?,?,?,?,?,?,0)");
    }
    public void closeConnection() throws SQLException {
        con.close();
    }


    public synchronized ResultSet getAllBooks() throws SQLException {
        String query = "select * from book";
        PreparedStatement st = con.prepareStatement(query);
        return st.executeQuery();
    }

    public void addUser(Map<String,Object> map) throws SQLException {
        addUserStatement.setString(1, (String) map.get(SearchContract.USER_NAME));
        addUserStatement.setString(2, (String) map.get(SearchContract.FIRST_NAME));
        addUserStatement.setString(3, (String) map.get(SearchContract.LAST_NAME));
        addUserStatement.setString(4, (String) map.get(SearchContract.EMAIL));
        addUserStatement.setString(5, (String) map.get(SearchContract.PASSWORD));
        addUserStatement.setString(6, (String) map.get(SearchContract.SHIPPING_ADDRESS));
        addUserStatement.executeUpdate();
    }

    public ResultSet getUser(String userName, String password) throws SQLException {
        selectUserStatement.setString(1, userName);
        selectUserStatement.setString(2,password);
        return selectUserStatement.executeQuery();
    }
    public ResultSet getBooks(Map<String,Object> searchAttribute ) throws SQLException {
        String query = " select * from book natural join publisher natural join catagory";

        if(searchAttribute == null){
            selectBookStatement = con.prepareStatement(query);
            return selectBookStatement.executeQuery();
        }

        query += " where ";
        int size = searchAttribute.size();
        int i = 0;
        for(String column : searchAttribute.keySet()){
            query += column + " like ? ";
            if(i != size-1) query += "OR ";
            i++;
        }
        selectBookStatement = con.prepareStatement(query);
        i = 1;
        for(Object value : searchAttribute.values()){
            selectBookStatement.setObject(i++,"%"+value+"%");
        }
        return selectBookStatement.executeQuery();
    }



    public void writeCart(Map<Book,Integer> books , String userName) throws SQLException {
        writeCartStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
        writeCartStatement.setString(4,userName);
        int i=0;
        for(Map.Entry<Book,Integer> entry : books.entrySet()){
            writeCartStatement.setString(1,entry.getKey().getISBN());
            writeCartStatement.setInt(2,entry.getValue());
            writeCartStatement.addBatch();
            i++;
            if (i % 20 == 0 || i == books.size()) {
                writeCartStatement.executeBatch(); // Execute every 20 items.
            }
        }
    }
    public void addCreditCard(String cardNo,String expireDate,String userName) throws  SQLException{
        addCreditCard.setString(1,cardNo);
        addCreditCard.setString(2,expireDate);
        addCreditCard.setString(3,userName);
        addCreditCard.executeUpdate();
    }
    public void removeCreditCard(String cardID) throws SQLException {
        removeCreditCard.setString(1,cardID);
        removeCreditCard.executeUpdate();
    }
    public ResultSet getBookWithIsbn(String isbn) throws SQLException {
        selectBookWithIsbn.setString(1,isbn);
        return selectBookWithIsbn.executeQuery();
    }
    public ResultSet getCheckouts(String userName) throws SQLException {
        selectCartStatement.setString(1, userName);
        return selectCartStatement.executeQuery();
    }
    public ResultSet getCatagory (int catagory_id ) throws SQLException {
        getCatagoryStatement.setInt(1,catagory_id);
        return  getCatagoryStatement.executeQuery();
    }

    public void updateUser(String userName, User newUser) throws SQLException {
        String query = "update user set ";
        query += SearchContract.EMAIL + "=?,";
        query += SearchContract.FIRST_NAME + "=?,";
        query += SearchContract.LAST_NAME + "=?,";
        query += SearchContract.PASSWORD + "=?,";
        query += SearchContract.SHIPPING_ADDRESS + "=?,";
        query += SearchContract.MANAGER + "=?";

        query += " where ";
        query += "user_name=?";
        PreparedStatement st = con.prepareStatement(query);


        st.setObject(1,newUser.getEmail());
        st.setObject(2,newUser.getFirst_name());
        st.setObject(3,newUser.getLast_name());
        st.setObject(4,newUser.getPassword());
        st.setObject(5,newUser.getShipping_address());
        st.setObject(6,newUser.isManager());
        st.setObject(7,userName);
        st.executeUpdate();
    }


    public void removeUser(String userName) throws SQLException {
        String query = "remove from user where user_name=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setObject(1,userName);
        ps.executeUpdate();
    }
}