package SsafyHomework.step2;
import java.util.*;

public class MovieManager {
    private static MovieManager instance = new MovieManager();
    private static final int MAX_SIZE = 100;
    private Movie[] movieList;
    private int size;
    private MovieManager() {
        movieList = new Movie[MAX_SIZE];
    }

    public static MovieManager getInstance() { return instance; }

    public void add(Movie movie) {
        movieList[size++] = movie;
    }

    public Movie[] getList() {
        Movie[] tempMovies = new Movie[size];
        for (int index = 0; index < size; index++) {
            tempMovies[index] = movieList[index];
        }

        return tempMovies;
    }

    public Movie[] searchByTitle(String title) {
        ArrayList<Movie> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            Movie target = movieList[index];

            // 인자로 주어진 title이 포함되어 있으면
            if (target.getTitle().contains(title)) {
                list.add(target);
            }
        }

        return list.toArray(new Movie[list.size()]);
    }

    public Movie[] getMovies() {
        ArrayList<Movie> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!(movieList[i] instanceof SeriesMovie)) { // 일반 영화
                list.add(movieList[i]);
            }
        }

        return list.toArray(new Movie[list.size()]);
    }

    public SeriesMovie[] getSeriesMovies() {
        ArrayList<SeriesMovie> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (movieList[i] instanceof SeriesMovie) {
                list.add((SeriesMovie) movieList[i]);
            }
        }

        return list.toArray(new SeriesMovie[list.size()]);
    }

    public double getRunningTimeAvg() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += movieList[i].getRunningTime();
        }

        return (double)sum / size;
    }

    public void printTitle(String title) {
        System.out.println("**********************" + title + "**********************");
    }
}
