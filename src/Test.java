package IndividualProject.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    private final String theme;
    private final int taskCount;
    private final List<IndividualProject.src.Task> taskList;
    public Test(String thm, int tCount){
        theme = thm;
        taskCount = tCount;
        taskList = new ArrayList<>();
    }
    public int getTaskCount(){
        return taskCount;
    }
    public void addTask(String firstLine, String secondLine){
        String[] wordAnswer = firstLine.split("-");
        List<String> variants = new ArrayList<>(Arrays.asList(secondLine.split(" ")));
        taskList.add(new IndividualProject.src.Task(wordAnswer[0], wordAnswer[1], variants));
    }
}
