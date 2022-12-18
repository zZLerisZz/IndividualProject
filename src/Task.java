package IndividualProject.src;

import java.util.List;

public class Task {
    private final String wordToTranslate;
    private final String answer;
    private final List<String> options;
    public Task(String task, String ans, List<String> vars){
        wordToTranslate = task;
        answer = ans;
        options = vars;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    public String getAnswer() {
        return answer;
    }
    @Override
    public String toString(){
        String str = "Как переводится \"" + wordToTranslate + "\"?\n";
        for (var it : options)
            str += " " + it;
        return str;
    }
    public boolean inOptions(String word){
        return options.contains(word.toLowerCase());
    }
}
