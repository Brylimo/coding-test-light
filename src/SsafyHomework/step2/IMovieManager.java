package SsafyHomework.step2;

public interface IMovieManager {
    void add(Movie movie);
    Movie[] getList();
    Movie[] searchByTitle(String title) throws TitleNotFoundException;
    Movie[] getMovies();
    SeriesMovie[] getSeriesMovies();
    double getRunningTimeAvg();
    void saveData();
}
