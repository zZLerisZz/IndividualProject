package IndividualProject.src;

import java.io.IOException;

public class Application {
    private static final String urlTest = "jdbc:h2:/DataBase/TestDataBase/TestBase";
    private static final String urlUser = "jdbc:h2:/DataBase/UserDataBase/UserBase";
    public static void main(String[] args) throws Exception {
        if(!IndividualProject.src.Checkers.Existance.tableExists(urlTest)) {
            IndividualProject.src.TestBaseForDB testBase = new IndividualProject.src.TestBaseForDB();
            IndividualProject.src.TestDataBaseCreator testerDB = new IndividualProject.src.TestDataBaseCreator(testBase,
                    urlTest, "sa", "");
        }
        IndividualProject.src.UserDataBase userDataBase;
        if(!IndividualProject.src.Checkers.Existance.tableExists(urlUser)){
            userDataBase = new IndividualProject.src.UserDataBase(urlUser, "sa", "", false);
        }
        else{
            userDataBase = new IndividualProject.src.UserDataBase(urlUser, "sa", "", true);
        }
        IndividualProject.src.TestBaseForApp testBaseForApp = new IndividualProject.src.TestBaseForApp(urlTest, "sa", "");
        IndividualProject.src.TestApplication application = new IndividualProject.src.TestApplication(userDataBase, testBaseForApp);
        application.run();
    }
}