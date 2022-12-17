package IndividualProject.src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestBase {
    private final List<IndividualProject.src.Test> testList;
    private int testCount = 3;
    public TestBase() throws IOException {
        testList = new ArrayList<>();
        FileReader fr;
        Scanner sc;
        for(int i = 1; i <= testCount; i++){
            fr = new FileReader("TestData\\Test" + i + ".txt");
            sc = new Scanner(fr);
            String[] themeCount = sc.nextLine().split(" ");
            IndividualProject.src.Test test = new IndividualProject.src.Test(themeCount[0], Integer.parseInt(themeCount[1]));
            for (int j = 0; j < test.getTaskCount(); j++){
                String firstTaskSrgument = sc.nextLine();
                String secondTaskArgument = sc.nextLine();
                test.addTask(firstTaskSrgument, secondTaskArgument);
            }
            testList.add(test);
            sc.close();
            fr.close();
        }
    }
}
