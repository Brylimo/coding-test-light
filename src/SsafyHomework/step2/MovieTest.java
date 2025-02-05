package SsafyHomework.step2;

public class MovieTest {
    public static void main(String[] args) {
        IMovieManager manager = MovieManagerImpl.getInstance();

        Movie m1 = new Movie(1, "타이타닉", "James Cameron", "Romance/Adventure", 194);
        Movie m2 = new Movie(2, "태극기 휘날리며", "강제규", "War/Action", 200);
        Movie m3 = new Movie(3, "쥬라기 공원", "스티븐 스필버그", "Adventure/Sci-fi", 140);
        Movie m4 = new Movie(4, "태극권", "Yuen Woo-ping", "Action/Comedy", 181);
        Movie m5 = new SeriesMovie(5, "스파이더맨", "Sam Raimi", "Action/Sci-fi", 181, 1, "에피소드 1");
        Movie m6 = new SeriesMovie(6, "스파이더맨2", "Sam Raimi", "Action/Sci-fi", 190, 2, "에피소드 2");

        manager.add(m1);
        manager.add(m2);
        manager.add(m3);
        manager.add(m4);
        manager.add(m5);
        manager.add(m6);

        manager.saveData();

        System.out.println("**********************영화 목록**********************");
        Movie[] movies = manager.getList();
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        System.out.println("**********************영화 조회:태극**********************");

        Movie[] foundMovies1 = null;
        try {
            foundMovies1 = manager.searchByTitle("태극");

            for (Movie foundMovie : foundMovies1) {
                System.out.println(foundMovie);
            }
        } catch (TitleNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("**********************영화 조회:쥬라기 공원**********************");
        Movie[] foundMovies2 = null;
        try {
            foundMovies2 = manager.searchByTitle("쥬라기 공원");

            for (Movie foundMovie : foundMovies2) {
                System.out.println(foundMovie);
            }
        } catch (TitleNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("**********************일반 영화 조회**********************");
        Movie[] normalMovies = manager.getMovies();
        for (Movie movie : normalMovies) {
            System.out.println(movie);
        }

        System.out.println("**********************시리즈 영화 조회**********************");
        SeriesMovie[] seriesMovies = manager.getSeriesMovies();
        for (SeriesMovie seriesMovie : seriesMovies) {
            System.out.println(seriesMovie);
        }

        System.out.println("**********************영화 평균 상영시간**********************");
        double average = manager.getRunningTimeAvg();
        System.out.println(average);
    }
}