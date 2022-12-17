package IndividualProject.src;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws Exception {
        IndividualProject.src.TestBase testBase = new IndividualProject.src.TestBase();
        IndividualProject.src.TestDataBaseWorker tester = new IndividualProject.src.TestDataBaseWorker(testBase,
                "jdbc:h2:/DataBase/TestDataBase/TestsBase", "sa", "");
    }
}