package IndividualProject.src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDAO {
    public static class TestDataBaseDAO{
        public static void inSertTest(Connection conn, IndividualProject.src.Test test) throws SQLException {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO tests (theme, task_count, task_cost) VALUES ('" +
                    test.getTheme() + "', " + test.getTaskCount() + ", " + test.getQuestionCost() + ")");
            ResultSet rs = statement.executeQuery("SELECT * FROM tests WHERE theme = '" + test.getTheme() + "'");
            int testid = 0;
            if(rs.next())
                testid = rs.getInt("test_id");
            for (var it : test.getTaskList())
                inSertTask(conn, it, testid);
        }
        private static void inSertTask(Connection conn, IndividualProject.src.Task task, int test_id) throws SQLException {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO tasks (test_id, question) VALUES (" +
                    test_id + ", '" + task.getWordToTranslate() + "')");
            ResultSet rs = statement.executeQuery("SELECT * FROM tasks WHERE test_id = '" + test_id +
                    "' AND question = '" + task.getWordToTranslate() + "'");
            int taskid = 0;
            if(rs.next())
                taskid = rs.getInt("task_id");
            for (var it : task.getOptions())
                inSertWord(conn, it, taskid, it.equals(task.getAnswer()));
        }
        private static void inSertWord(Connection conn, String word, int task_id, boolean isAnswer) throws SQLException {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO answers (task_id, word, is_correct) VALUES (" +
                    task_id + ", '" + word + "', " + isAnswer + ")");
        }
        public static IndividualProject.src.Test selectTest(Connection conn, int id) throws SQLException {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tests WHERE test_id = " +  id);
            if(!rs.next())
                return null;
            int test_id = rs.getInt("test_id");
            String theme = rs.getString("theme");
            int task_count = rs.getInt("task_count");
            int task_cost = rs.getInt("task_cost");
            return new IndividualProject.src.Test(theme, task_count, task_cost, selectTasks(conn, test_id));
        }
        private static List<IndividualProject.src.Task> selectTasks(Connection conn, int test_id) throws SQLException {
            Statement statement = conn.createStatement();
            List<IndividualProject.src.Task> temp = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM tasks WHERE test_id = " +  test_id);
            while(rs.next()) {
                int task_id = rs.getInt("task_id");
                String word = rs.getString("question");
                temp.add(new IndividualProject.src.Task(word, selectAnswer(conn, task_id), selectWords(conn, task_id)));
            }
            return temp;
        }
        private static List<String> selectWords(Connection conn, int task_id) throws SQLException {
            List<String> words = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM answers WHERE task_id = " +  task_id);
            while(rs.next()) {
                words.add(rs.getString("word"));
            }
            return words;
        }
        private static String selectAnswer(Connection conn, int task_id) throws SQLException {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM answers WHERE task_id = " +  task_id + " AND is_correct = TRUE");
            if(!rs.next())
                return null;
            return rs.getString("word");
        }
    }
    public static class UserDataBaseDAO{
        public static IndividualProject.src.User userFinder(String userLogin, Connection conn) throws SQLException {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + userLogin + "'");
            if(rs.next())
                return new IndividualProject.src.User(rs.getString("username"), rs.getInt("points"),
                        rs.getInt("session_count"));
            addUser(userLogin, conn);
            return new IndividualProject.src.User(userLogin, 0, 0);
        }
        private static void addUser(String username, Connection conn) throws SQLException {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO users (username, points, session_count) VALUES ('" + username
                                    + "', 0, 0)");
        }
        public static void updateUser(Connection con, IndividualProject.src.User user) throws SQLException {
            Statement statement = con.createStatement();
            statement.execute("update users set points = "+ user.getSumOfPoints()
                    + ", session_count = " + user.getCountOfSessions() +
                    " where username = '" + user.getUsername() + "'");
        }
        public static int getPlaceOfUser(Connection conn, IndividualProject.src.User user) throws SQLException {
            int top = 0;
            double userAvPoints = user.getAveragePoints();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next())
                if((double)rs.getInt("points") / rs.getInt("session_count") > userAvPoints)
                    top += 1;
            return top + 1;
        }
    }
}
