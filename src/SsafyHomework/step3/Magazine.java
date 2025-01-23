package SsafyHomework.step3;

public class Magazine extends Book {
    private int year;
    private int month;

    public Magazine() {
        System.out.println("Magazine 기본 생성자 수행!!!");
    }

    public Magazine(String isbn, String title, String author, String publisher, int price, String desc, int year, int month) {
        super(isbn, title, author, publisher, price, desc);
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(String.format("| %d    | %d ", year, month));
        return builder.toString();
    }
}
