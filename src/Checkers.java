package IndividualProject.src;

import com.sun.tools.javac.Main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Checkers {
    public static class Existance{
        public static boolean tableExists(String urlForCheck){
            String filepath = urlForCheck;
            Path pathTab = Paths.get(filepath.substring(filepath.indexOf("/"), filepath.length()) + ".mv.db");
            return Files.exists(pathTab);
        }
    }
}