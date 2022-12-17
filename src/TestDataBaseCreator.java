package IndividualProject.src;

import java.sql.*;
import java.util.List;

public class TestDataBaseCreator {
    private final IndividualProject.src.TestBaseForDB testBase;
    private final Connection conn;
    public TestDataBaseCreator(IndividualProject.src.TestBaseForDB testBaseToWork, String filepath, String username, String password) throws Exception {
        testBase = testBaseToWork;
        conn = DriverManager.getConnection(filepath, username, password);
        createTablesIfNotExist();
        fillTestDataBase();
    }
    private void createTablesIfNotExist() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("create table if not exists tests(test_id int primary key auto_increment, " +
                "theme varchar not null, task_count int not null, task_cost int not null)");
        statement.execute("create table if not exists tasks(task_id int primary key auto_increment, " +
                "test_id int not null, question varchar not null)");
        statement.execute("create table if not exists answers(task_id int not null, " +
                "word varchar not null, is_correct bool)");
    }
    private void fillTestDataBase() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM tests");
        statement.execute("DELETE FROM answers");
        statement.execute("DELETE FROM tasks");
        List<IndividualProject.src.Test> testToWrite = testBase.getTestList();
        for(var it : testToWrite){
            IndividualProject.src.DataBaseDAO.TestDataBaseDAO.inSertTest(conn, it);
        }
    }
}
