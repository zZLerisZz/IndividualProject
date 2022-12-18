package IndividualProject.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private final List<IndividualProject.src.User> userList;
    private final Connection conn;
    public UserDataBase(String urlBase, String user, String pass, boolean fl) throws SQLException {
        conn = DriverManager.getConnection(urlBase, user, pass);
        userList = new ArrayList<>();
        if(!fl)
            createUserBase();
    }
    private void createUserBase() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("create table if not exists users(user_id int primary key auto_increment, " +
                "username varchar not null, points int not null, session_count int not null)");
    }
    public IndividualProject.src.User findUser(String username) throws SQLException {
        return IndividualProject.src.DataBaseDAO.UserDataBaseDAO.userFinder(username, conn);
    }
    public void updateUser(IndividualProject.src.User user) throws SQLException {
        IndividualProject.src.DataBaseDAO.UserDataBaseDAO.updateUser(conn, user);
    }
    public int getPlaceOfUser(IndividualProject.src.User user) throws SQLException {
        return IndividualProject.src.DataBaseDAO.UserDataBaseDAO.getPlaceOfUser(conn, user);
    }
}
