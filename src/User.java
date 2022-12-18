package IndividualProject.src;
public class User {
    private String username;
    private int sumOfPoints;
    private int countOfSessions;
    public User(String login, int points, int colSessions){
        username = login;
        sumOfPoints = points;
        countOfSessions = colSessions;
    }
    public double getAveragePoints(){
        return (double)sumOfPoints / countOfSessions;
    }

    public int getCountOfSessions() {
        return countOfSessions;
    }

    public String getUsername() {
        return username;
    }

    public int getSumOfPoints() {
        return sumOfPoints;
    }
    public void isConnected(){
        countOfSessions += 1;
    }
    public void addPints(int points){
        sumOfPoints += points;
    }
}
