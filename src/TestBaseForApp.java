package IndividualProject.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestBaseForApp {
    private final Connection conn;
    private final List<IndividualProject.src.Test> testList;
    private int testCount = 3;
    public TestBaseForApp(String url, String user, String pass) throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        testList = new ArrayList<>();
    }
    private void fillTestList() throws SQLException {
        IndividualProject.src.Test tmp;
        for(int i = 1;;i++){
            tmp = IndividualProject.src.DataBaseDAO.TestDataBaseDAO.selectTest(conn, i);
            if(tmp == null)
                return;

        }
    }
}
