package IndividualProject.src;

import java.sql.SQLException;

public class TestApplication {
    private final IndividualProject.src.UserDataBase userDataBase;
    private final IndividualProject.src.TestBaseForApp testBaseForApp;
    public TestApplication(IndividualProject.src.UserDataBase userBase, IndividualProject.src.TestBaseForApp testBase) {
        userDataBase = userBase;
        testBaseForApp = testBase;
    }
    public void run() throws SQLException {
        userDataBase.findUser("Алладин");
    }
}
