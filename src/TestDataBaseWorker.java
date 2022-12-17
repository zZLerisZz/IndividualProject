package IndividualProject.src;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class TestDataBaseWorker {
    private final IndividualProject.src.TestBase testBase;
    private final Connection conn;
    public TestDataBaseWorker(IndividualProject.src.TestBase testBaseToWork, String filepath, String username, String password) throws Exception {
        boolean flDir = false;
        boolean flTab = false;
        testBase = testBaseToWork;
        Path pathDir = Paths.get(filepath.substring(filepath.indexOf("/"), filepath.lastIndexOf("/")));
        Path pathTab = Paths.get(filepath.substring(filepath.indexOf("/"), filepath.length()) + ".mv.db");
        if(!Files.exists(pathDir)){
            new File(filepath.substring(filepath.indexOf("/"), filepath.lastIndexOf("/"))).mkdirs();
            flDir = true;
            flTab = true;
        }
        if(!Files.exists(pathTab))
            flTab = true;
        conn = DriverManager.getConnection(filepath, username, password);
        //fillTestDataBase();
        if(flTab || flDir){
            createTablesIfNotExist();
            fillTestDataBase();
        }
    }
    private void createTablesIfNotExist() throws SQLException {
        System.out.println("Сработало создание");
        Statement statement = conn.createStatement();
        statement.execute("create table if not exists tests(test_id int primary key auto_increment, " +
                "theme varchar not null, task_count int not null, task_cost int not null)");
        statement.execute("create table if not exists tasks(task_id int primary key auto_increment, " +
                "test_id int not null, question varchar not null)");
        statement.execute("create table if not exists answers(task_id int not null, " +
                "word varchar not null, is_correct bool)");
    }
    private void fillTestDataBase() throws SQLException {
        System.out.println("Сработало заполнение");
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
