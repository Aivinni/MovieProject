import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> movies = 
        try {
            Scanner fileScanner = new Scanner(new File("movies_data.csv"));
            while (fileScanner.hasNext()) {
                String movieData = fileScanner.next();
                wordList.add(movieData);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}