import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();

    public MovieCollection() {
        getMenu();
    }
    public void getMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    public void importMovies() {
        try {
            Scanner fileScanner = new Scanner(new File("movies_data.csv"));
            while (fileScanner.hasNext()) {
                String[] movieData = fileScanner.next().split(",");
                Movie movie = new Movie(movieData[0], movieData[1], movieData[2], movieData[3], Integer.parseInt(movieData[4]), Integer.parseInt(movieData[5]));
                movies.add(movie);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
