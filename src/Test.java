package IndividualProject.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    private final String theme;
    private final int taskCount;
    private final List<IndividualProject.src.Task> taskList;
    private final int questionCost;
    public Test(String thm, int tCount, int cost){
        theme = thm;
        taskCount = tCount;
        taskList = new ArrayList<>();
        questionCost = cost;
    }
    public Test(String thm, int tCount, int cost, List<IndividualProject.src.Task> tasks){
        theme = thm;
        taskCount = tCount;
        taskList = tasks;
        questionCost = cost;
    }
    public int getTaskCount(){
        return taskCount;
    }
    public void addTask(String firstLine, String secondLine){
        String[] wordAnswer = firstLine.split("-");
        List<String> variants = new ArrayList<>(Arrays.asList(secondLine.split(" ")));
        taskList.add(new IndividualProject.src.Task(wordAnswer[0], wordAnswer[1], variants));
    }

    public int getQuestionCost() {
        return questionCost;
    }

    public List<IndividualProject.src.Task> getTaskList() {
        return taskList;
    }

    public String getTheme() {
        return theme;
    }
    @Override
    public String toString(){
        return theme + " " + taskCount + " " + questionCost + " " + taskList;
    }
}
