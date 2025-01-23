package SsafyHomework.step3;

/**
 * 책 도메인 클래스
 * @author 임채진
 * @version ver1.0
 */
public class Book {
    /** 책 국제표준도서번호 */
    private String isbn;
    /** 책 제목 */
    private String title;
    /** 책 저자 */
    private String author;
    /** 책 발행자 */
    private String publisher;
    /** 책 가격 */
    private int price;
    /** 책 설명 */
    private String desc;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /** 기본생성자 */
    public Book() {
        System.out.println("book 기본생성자 수행!!!");
    }

    /**
     * 책 데이터 초기화 생성자
     * @param isbn
     * @param title
     * @param author
     * @param publisher
     * @param price
     * @param desc
     */
    public Book(String isbn, String title, String author, String publisher, int price, String desc) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.desc = desc;
    }

    /**
     * toString 메서드
     * @return toString 반환값 반환
     */
    @Override
    public String toString() {
        return String.format("%s  | %s    | %s | %s    | %d | %s    ", isbn, title, author, publisher, price, desc);
    }
}