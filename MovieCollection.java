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
                Movie movie = new Movie(movieData[0], movieData[1].split("\\|"), movieData[2], movieData[3], Integer.parseInt(movieData[4]), Double.parseDouble(movieData[5]));
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

    // Done
    public void searchTitles() {
        ArrayList<Movie> moviesContainingWord = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a title search term: ");
        String term = scan.nextLine().toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(term)) {
                moviesContainingWord.add(movie);
            }
        }

        // Done
        if (!moviesContainingWord.isEmpty()) {
            for (int i = 0; i < moviesContainingWord.size(); i++) {
                System.out.println(i + ": " + moviesContainingWord.get(i));
            }
            System.out.println("Which movie would you like to learn more about? (-1 to quit)");
            int choice = scan.nextInt();
            scan.nextLine();

            while (choice > -1) {
                if (choice < moviesContainingWord.size()) {
                    Movie movie = moviesContainingWord.get(choice);
                    System.out.println("Title: " + movie.getTitle());
                    System.out.println("Runtime: " + movie.getRuntime());
                    System.out.println("Directed By: " + movie.getDirector());
                    System.out.print("Cast: ");
                    for (String actor : movie.getCast()) {
                        System.out.print(actor + ", ");
                    }
                    System.out.println();
                    System.out.println("Overview: " + movie.getOverview());
                    System.out.println("Rating: " + movie.getRating());
                    System.out.println();
                    for (int i = 0; i < moviesContainingWord.size(); i++) {
                        System.out.println(i + ": " + moviesContainingWord.get(i));
                    }
                    System.out.println("Would you like to learn more about another movie? (-1 to quit)");
                    choice = scan.nextInt();
                    scan.nextLine();
                } else {
                    System.out.println("That choice does not exist!");
                    System.out.println("Select a movie: ");
                    for (int i = 0; i < moviesContainingWord.size(); i++) {
                        System.out.println(i + ": " + moviesContainingWord.get(i));
                    }
                    choice = scan.nextInt();
                    scan.nextLine();
                }
            }
        } else {
            System.out.println("No movie titles match that search term!");
        }
        getMenu();
    }

    // Done
    public void searchCast() {
        ArrayList<Movie> moviesWithCast = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a person to search for: (first or last name)");
        String search = scan.nextLine().toLowerCase();
        ArrayList<String> namesOptions = new ArrayList<>();
        for (Movie movie : movies) {
            for (String actorInMovie : movie.getCast()) {
                if (actorInMovie.toLowerCase().contains(search)) {
                    boolean duplicate = false;
                    for (String actorSearchDuplicate : namesOptions) {
                        if (actorSearchDuplicate.equals(actorInMovie)) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (!duplicate) {
                        namesOptions.add(actorInMovie);
                    }
                    break;
                }
            }
        }

        // Sort nameOptions
        for (int i = 1; i < namesOptions.size(); i++) {
            String swap = namesOptions.get(i);
            for (int j = i - 1; j >= 0; j--) {
                String name2 = namesOptions.get(j);
                int namesMin = Math.min(swap.length(), name2.length());
                boolean less = false;
                for (int k = 0; k < namesMin; k++) {
                    if (swap.charAt(k) < name2.charAt(k)) {
                        less = true;
                        break;
                    } else if (swap.charAt(k) > name2.charAt(k)) {
                        break;
                    }
                    if (k == namesMin - 1) {
                        if (swap.length() < name2.length()) {
                            less = true;
                            break;
                        } else if (swap.length() > name2.length()) {
                            break;
                        }
                        break;
                    }
                }
                if (less) {
                    namesOptions.set(j + 1, name2);
                    namesOptions.set(j, swap);
                } else {
                    break;
                }
            }
        }

        if (!namesOptions.isEmpty()) {
            for (int i = 0; i < namesOptions.size(); i++) {
                System.out.println(i + ": " + namesOptions.get(i));
            }
            System.out.println("Select an Actor");
            String name = namesOptions.get(scan.nextInt());
            System.out.println(name);
            scan.nextLine();

            for (Movie movie : movies) {
                for (String actor : movie.getCast()) {
                    if (actor.equalsIgnoreCase(name)) {
                        moviesWithCast.add(movie);
                    }
                }
            }

            for (int i = 0; i < moviesWithCast.size(); i++) {
                System.out.println(i + ": " + moviesWithCast.get(i));
            }
            System.out.println("Which movie would you like to learn more about? (-1 to quit)");
            int choice = scan.nextInt();
            scan.nextLine();

            while (choice > -1) {
                if (choice < moviesWithCast.size()) {
                    Movie movie = moviesWithCast.get(choice);
                    System.out.println("Title: " + movie.getTitle());
                    System.out.println("Runtime: " + movie.getRuntime());
                    System.out.println("Directed By: " + movie.getDirector());
                    System.out.print("Cast: ");
                    for (String actor : movie.getCast()) {
                        System.out.print(actor + ", ");
                    }
                    System.out.println();
                    System.out.println("Overview: " + movie.getOverview());
                    System.out.println("Rating: " + movie.getRating());
                    System.out.println();
                    for (int i = 0; i < moviesWithCast.size(); i++) {
                        System.out.println(i + ": " + moviesWithCast.get(i));
                    }
                    System.out.println("Would you like to learn more about another movie? (-1 to quit)");
                    choice = scan.nextInt();
                    scan.nextLine();
                } else {
                    System.out.println("That choice does not exist!");
                    System.out.println("Select a movie: ");
                    for (int i = 0; i < moviesWithCast.size(); i++) {
                        System.out.println(i + ": " + moviesWithCast.get(i));
                    }
                    choice = scan.nextInt();
                    scan.nextLine();
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
                        break;
                    }
                    if (k == moviesMin - 1) {
                        if (swap.getTitle().length() < movie2.getTitle().length()) {
                            less = true;
                            break;
                        } else if (swap.getTitle().length() > movie2.getTitle().length()) {
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
