package IndividualProject.src;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestApplication {
    private final IndividualProject.src.UserDataBase userDataBase;
    private final IndividualProject.src.TestBaseForApp testBaseForApp;
    public TestApplication(IndividualProject.src.UserDataBase userBase, IndividualProject.src.TestBaseForApp testBase) {
        userDataBase = userBase;
        testBaseForApp = testBase;
    }
    public void run() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int menu;
        String str;
        IndividualProject.src.User curUser;
        System.out.print("Введите свой логин: ");
        do {
            str = sc.nextLine();
        }while (str == "");
        curUser = userConnect(str);
        do {
            do {
                System.out.println("1.Сменить пользователя");
                System.out.println("2.Узнать свое место в топе");
                System.out.println("3.Пройти тест");
                System.out.println("4.Выйти");
                menu = IndividualProject.src.Checkers.MeaningCheckers.valueChecker("Ваш выбор", sc, Scanner::nextInt, Scanner::hasNextInt);
            }while (menu < 1 || menu > 4);
            switch (menu){
                case 1:{
                    userDataBase.updateUser(curUser);
                    System.out.print("Введите свой логин: ");
                    do {
                        str = sc.nextLine();
                    }while (str == "");
                    curUser = userConnect(str);
                }break;
                case 2:{
                    userDataBase.updateUser(curUser);
                    System.out.println("У вас в среднем " + curUser.getAveragePoints() + " очков за сессию.");
                    System.out.println("Вы на " + userDataBase.getPlaceOfUser(curUser) + " месте");
                }break;
                case 3:{
                    curUser.addPints(runTest(sc));
                }break;
            }
        }while(menu != 4);
        userDataBase.updateUser(curUser);
    }
    private IndividualProject.src.User userConnect(String login) throws SQLException {
        IndividualProject.src.User neededUser = userDataBase.findUser(login);
        neededUser.isConnected();
        return neededUser;
    }
    private int runTest(Scanner scanner){
        List<IndividualProject.src.Test> tests = testBaseForApp.getTestList();
        int i = 1, id;
        System.out.println("Темы:");
        for(var it : tests)
            System.out.println(i++ + "." + it.getTheme());
        do{
            id = IndividualProject.src.Checkers.MeaningCheckers.valueChecker("Номер понравившейся вам темы", scanner,
                    Scanner::nextInt, Scanner::hasNextInt);
        }while (id < 1 || id >= i);
        IndividualProject.src.Test curTest = (IndividualProject.src.Test) tests.toArray()[id - 1];
        System.out.println("Для ответа на вопрос вписывайте ваш вариант ответа целиком.");
        int answerCorrectCount = runTasks(scanner, curTest.getTaskList());
        System.out.println("Вы верно ответили на " + answerCorrectCount + " вопр.\nЗа тест вы заработали " +
                answerCorrectCount * curTest.getQuestionCost() + " б.");
        return answerCorrectCount * curTest.getQuestionCost();
    }
    private int runTasks(Scanner scanner, List<IndividualProject.src.Task> tasks){
        int c = 0;
        String answer;
        scanner.nextLine();
        for(var it : tasks){
            System.out.println(it);
            do {
                System.out.print("Ваш вариант ответа: ");
                answer = scanner.nextLine();
            }while (!it.inOptions(answer));
            if(it.getAnswer().toLowerCase().equals(answer.toLowerCase())) {
                c += 1;
            }
        }
        return c;
    }
}
