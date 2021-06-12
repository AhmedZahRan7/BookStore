package sample.Repository;
import sample.models.Book;
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

    protected PreparedStatement selectBookWithIsbn;
    public UserRepo() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://197.48.252.25:3306/mydb?rewriteBatchedStatements=true", "zezo", "zezo");
        selectUserStatement = con.prepareStatement("select * from user where user_name = ? and password = ? ");
        selectCartStatement = con.prepareStatement("select * from checkout natural join book where user_name = ?");
        writeCartStatement = con.prepareStatement("insert into checkout (isbn,nocopies,date,user_name) values (?,?,?,?)");
        deleteCartStatement = con.prepareStatement("delete from checkout where user_name = ?");
        addCreditCard = con.prepareStatement("insert into CREDIT_CARD (Card_id,Exp_date,User_Name) values (?,?,?)");
        removeCreditCard = con.prepareStatement("delete from Credit_CARD where Card_id = ?");
        selectBookWithIsbn = con.prepareStatement("select * from book where isbn = ?");
        selectUserStatement = con.prepareStatement("select * from user where user_name = ? and password = ? ");
        selectCartStatement = con.prepareStatement("select * from checkout natural join book where user_name = ?");
        writeCartStatement = con.prepareStatement("insert into checkout (isbn,nocopies,date,user_name) values (?,?,?,?)");
        deleteCartStatement = con.prepareStatement("delete from checkout where user_name = ?");
        getCatagoryStatement = con.prepareStatement("select from catagory where catagory_id = ?");
    }
    public void closeConnection() throws SQLException {
        con.close();
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
        return selectUserStatement.executeQuery();
    }
    public ResultSet getCatagory (int catagory_id ) throws SQLException {
        getCatagoryStatement.setInt(1,catagory_id);
        return  getCatagoryStatement.executeQuery();
    }
//    public void removeCart(String userName) throws SQLException {
//        deleteCartStatement.setString(1, userName);
//        deleteCartStatement.executeUpdate();
//    }
}