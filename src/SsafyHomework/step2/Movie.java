package SsafyHomework.step2;

import java.io.Serializable;

public class Movie implements Serializable {
    /**  영화 아이디 */
    private int id;
    /**  영화 제목 */
    private String title;
    /**  영화 감독 */
    private String director;
    /**  영화 장르 */
    private String genre;
    /**  영화 상영 시간 */
    private int runningTime;

    public Movie() {
        System.out.println("Movie 기본 생성자 수행!!!");
    }

    public Movie(int id, String title, String director, String genre, int runningTime) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.runningTime = runningTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append(", ");
        builder.append(title);
        builder.append(", ");
        builder.append(director);
        builder.append(", ");
        builder.append(genre);
        builder.append(", ");
        builder.append(runningTime);
        return builder.toString();
    }
}