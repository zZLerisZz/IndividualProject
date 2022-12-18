package IndividualProject.src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestBaseForApp {
    private final Connection conn;
    private final List<IndividualProject.src.Test> testList;
    private int testCount = 3;
    public TestBaseForApp(String url, String user, String pass) throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        testList = new ArrayList<>();
        fillTestList();
    }
    private void fillTestList() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM tests");
        while (rs.next())
            testList.add(IndividualProject.src.DataBaseDAO.TestDataBaseDAO.selectTest(conn, rs.getInt("test_id")));
    }
    public void printTestBase(){
        for(var it:testList)
            System.out.println(it);
    }

    public List<IndividualProject.src.Test> getTestList() {
        return testList;
    }
}
