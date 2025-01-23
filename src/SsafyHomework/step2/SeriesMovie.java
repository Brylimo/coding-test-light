package SsafyHomework.step2;

public class SeriesMovie extends Movie {
    private int seriesNum;
    private String episode;

    public SeriesMovie() {
        System.out.println("Series Movie 기본 생성자 수행!!!");
    }

    public SeriesMovie(int id, String title, String director, String genre, int runningTime, int seriesNum, String episode) {
        super(id, title, director, genre, runningTime);
        this.seriesNum = seriesNum;
        this.episode = episode;
    }

    public int getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(int seriesNum) {
        this.seriesNum = seriesNum;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append(seriesNum);
        builder.append(", ");
        builder.append(episode);
        return builder.toString();
    }
}