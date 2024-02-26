public class Movie {
    private String title;
    private String[] cast;
    private String director;
    private String overview;
    private int runtime;
    private double rating;

    public Movie(String title, String[] cast, String director, String overview, int runtime, double rating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }
    public String[] getCast() {
        return cast;
    }
    public String getDirector() {
        return director;
    }
    public String getOverview() {
        return overview;
    }
    public int getRuntime() {
        return runtime;
    }
    public double getRating() {
        return rating;
    }
    public String toString() {
        return title;
    }
}
