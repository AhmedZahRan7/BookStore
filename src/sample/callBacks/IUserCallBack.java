package sample.callBacks;



import sample.models.Book;
import sample.models.User;
import java.sql.SQLException;
import java.util.List;

public interface IUserCallBack {
    void onSuccess(User user) throws SQLException, ClassNotFoundException;
    void onSuccess(Book book);
    void onSuccess(List<Object> data);
    void onSuccess() throws SQLException, ClassNotFoundException;
    void onFailure();
}
