package IndividualProject.src;

import java.sql.*;

public class DataBaseDAO {
    public static class TestDataBaseDAO{
        public static void inSertTest(Connection conn, IndividualProject.src.Test test) throws SQLException {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO tests (theme, task_count, task_cost) VALUES ('" +
                    test.getTheme() + "', " + test.getTaskCount() + ", " + test.getTaskCount() + ")");
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
            ResultSet rs = statement.executeQuery("SELECT * FROM tasks WHERE question = '" + task.getWordToTranslate() + "'");
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
    }
}
