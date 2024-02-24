import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    
    // Done
    public MovieCollection() {
        importMovies();
        sortListTitle();
        getMenu();
    }

    // Done
    public void importMovies() {
        try {
            Scanner fileScanner = new Scanner(new File("movies_data.csv"));
            while (fileScanner.hasNext()) {
                String[] movieData = fileScanner.nextLine().split(",");
                Movie movie = new Movie(movieData[0], movieData[1].split("|"), movieData[2], movieData[3], Integer.parseInt(movieData[4]), Double.parseDouble(movieData[5]));
                movies.add(movie);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Done
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

    public void searchTitles() {
        ArrayList<String> titles = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a title search term: ");
        String term = scan.nextLine();
        for (int i = 0; i < movies.size(); i++) {
            if ((movies.get(i).getTitle()).contains(term)) {
                titles.add((movies.get(i).getTitle()));
            }
        }
        if (titles.size() != 0) {
            int num = 1;
            for (String t : titles) {
                System.out.println(num + ". " + t);
                num++;
            }
            System.out.println("Which movie would you like to learn more about?");
            int choice = scan.nextInt();
            if (choice > -1 && choice < titles.size()) {
                for (int i = 0; i < movies.size(); i++) {
                    if ((movies.get(i).getTitle()).equals(titles.get(choice))) {
                        System.out.println("Title: " + titles.get(choice));
                        System.out.println("Runtime: " + movies.get(i).getRuntime());
                        System.out.println("Directed By: " + movies.get(i).getDirector());
                        System.out.println("Cast: " + movies.get(i).getCast());
                        System.out.println("Overview: " + movies.get(i).getOverview());
                        System.out.println("Rating: " + movies.get(i).getRating());
                    }
                }
            }
        } else {
            System.out.println("No movie titles match that search term!");
        }
        getMenu();
    }

    public void searchCast() {
        ArrayList<String[]> allNames = new ArrayList<>();
        for (int a = 0; a < movies.size(); a++) {
            allNames.add((movies.get(a).getCast()));
        }
        ArrayList<String> names = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a person to search for: ");
        String name = scan.nextLine();
        for (int i = 0; i < allNames.size(); i++) {
            if (allNames.get(i).);
        }
        if (names.size() != 0) {
            int num = 1;
            for (String n : names) {
                System.out.println(num + ". " + n);
                num++;
            }
            System.out.println("Which movie would you like to learn more about?");
            int choice = scan.nextInt();
            if (choice > -1 && choice < titles.size()) {
                for (int i = 0; i < movies.size(); i++) {
                    if ((movies.get(i).getTitle()).equals(movies.get(choice).getTitle())) {
                        System.out.println("Title: " + movies.get(i).getTitle());
                        System.out.println("Runtime: " + movies.get(i).getRuntime());
                        System.out.println("Directed By: " + movies.get(i).getDirector());
                        System.out.println("Cast: " + movies.get(i).getCast());
                        System.out.println("Overview: " + movies.get(i).getOverview());
                        System.out.println("Rating: " + movies.get(i).getRating());
                    }
                }
            }
        } else {
            System.out.println("No results match your search!");
        }
        getMenu();
    }
    
    // Done
    public void sortListTitle() {
        for (int i = 1; i < movies.size(); i++) {
            Movie swap = movies.get(i);
            for (int j = i - 1; j >= 0; j--) {
                Movie movie2 = movies.get(j);
                int moviesMin = Math.min(swap.getTitle().length(), movie2.getTitle().length());
                boolean less = false;
                for (int k = 0; k < moviesMin; k++) {
                    if (swap.getTitle().charAt(k) < movie2.getTitle().charAt(k)) {
                        less = true;
                        break;
                    } else if (swap.getTitle().charAt(k) > movie2.getTitle().charAt(k)) {
                        less = false;
                        break;
                    }
                    if (k == moviesMin - 1) {
                        if (swap.getTitle().length() < movie2.getTitle().length()) {
                            less = true;
                            break;
                        } else if (swap.getTitle().length() > movie2.getTitle().length()) {
                            less = false;
                            break;
                        }
                        break;
                    }
                }
                if (less) {
                    movies.set(j + 1, movie2);
                    movies.set(j, swap);
                } else {
                    break;
                }
            }
        }
    }
}
