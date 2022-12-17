package IndividualProject.src;

import java.io.IOException;

public class Application {
    private static final String url = "jdbc:h2:/DataBase/TestDataBase/TestBase";
    public static void main(String[] args) throws Exception {
        if(!IndividualProject.src.Checkers.Existance.tableExists(url)) {
            IndividualProject.src.TestBaseForDB testBase = new IndividualProject.src.TestBaseForDB();
            IndividualProject.src.TestDataBaseCreator testerDB = new IndividualProject.src.TestDataBaseCreator(testBase,
                    url, "sa", "");
        }
        IndividualProject.src.TestBaseForApp testBaseForApp = new IndividualProject.src.TestBaseForApp(url, "sa", "");
        testBaseForApp.printTestBase();
    }
}