package SsafyHomework.step2;
import java.io.*;
import java.util.*;

public class MovieManagerImpl implements IMovieManager {
    private static IMovieManager instance = new MovieManagerImpl();
    private List<Movie> movieList = new ArrayList<>();
    private MovieManagerImpl() {
        loadData();
    }

    private void loadData() {
        File file = new File("movie.dat");

        if (file != null && file.exists()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                Object readed = ois.readObject();
                if(readed instanceof List<?>) {
                    for (Object element : (List<?>)readed) {
                        if (element instanceof Movie) {
                            movieList.add((Movie)element);
                        }
                    }
                }

            } catch(IOException e) {
                e.printStackTrace();
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }

    public void saveData() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("movie.dat"))){
            oos.writeObject(movieList);
            System.out.println("저장 완료!");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static IMovieManager getInstance() { return instance; }

    public void add(Movie movie) {
        movieList.add(movie);
    }

    public Movie[] getList() {
        return movieList.stream().toArray(Movie[]::new);
    }

    public Movie[] searchByTitle(String title) throws TitleNotFoundException {
        Movie[] movies =  movieList.stream().filter(movie -> movie.getTitle().contains(title)).toArray(Movie[]::new);

        if (movies.length > 0) return movies;

        throw new TitleNotFoundException("영화를 찾지 못했습니다!!!");
    }

    public Movie[] getMovies() {
        ArrayList<Movie> list = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if (!(movieList.get(i) instanceof SeriesMovie)) { // 일반 영화
                list.add(movieList.get(i));
            }
        }

        return list.toArray(new Movie[list.size()]);
    }

    public SeriesMovie[] getSeriesMovies() {
        ArrayList<SeriesMovie> list = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) instanceof SeriesMovie) {
                list.add((SeriesMovie) movieList.get(i));
            }
        }

        return list.toArray(new SeriesMovie[list.size()]);
    }

    public double getRunningTimeAvg() {
        int sum = 0;
        for (int i = 0; i < movieList.size(); i++) {
            sum += movieList.get(i).getRunningTime();
        }

        return (double)sum / movieList.size();
    }
}
