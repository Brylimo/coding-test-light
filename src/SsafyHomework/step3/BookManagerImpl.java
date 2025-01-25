package SsafyHomework.step3;

import java.util.ArrayList;

public class BookManagerImpl implements IBookManager {
    private static IBookManager instance = new BookManagerImpl();
    private static final int MAX_SIZE = 100;
    private Book[] books;
    private int size;

    /**
     * 기본생성자: MAX_SIZE로 배열객체 생성 초기화
     */
    private BookManagerImpl() {
        books = new Book[MAX_SIZE];
    }

    public static IBookManager getInstance() { return instance; }

    /**
     * 책 등록 메서드
     * @param book 등록 책 객체
     */
    public void add(Book book) {
        books[size++] = book;
    }

    /**
     * isbn에 해당하는 책 삭제
     * @param isbn
     */
    public void remove(String isbn) {
        for (int index = 0; index < size; index++) {
            if (books[index].getIsbn().equals(isbn)) {
                books[index] = books[--size];
                books[size] = null;
            }
        }
    }

    /**
     * 전체 책 정보 조회
     * @return 현재 등록한 책의 배열정보
     */
    public Book[] getList() {
        Book[] tempBooks = new Book[size];
        for (int index = 0; index < size; index++) {
            tempBooks[index] = books[index];
        }

        return tempBooks;
    }

    /**
     * isbn으로 책 조회
     * @param isbn
     * @return 존재하면 해당 책 객체, 미존재시 null 반환
     */
    public Book searchByIsbn(String isbn) {
        for (int index = 0; index < size; index++) {
            if (books[index].getIsbn().equals(isbn)) {
                return books[index];
            }
        }
        return null;
    }

    /**
     * title으로 책 조회
     * @param title
     * @return title로 찾은 boot 리스트
     */
    public Book[] searchByTitle(String title) {
        ArrayList<Book> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            Book target = books[index];

            if (target.getTitle().contains(title)) {
                list.add(target);
            }
        }

        return list.toArray(new Book[list.size()]);
    }

    /** 일반 도서 리스트 반환 */
    public Book[] getBooks() {
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!(books[i] instanceof Magazine)) {
                list.add(books[i]);
            }
        }

        return list.toArray(new Book[list.size()]);
    }

    /** 잡지 리스트 반환 */
    public Magazine[] getMagazines() {
        ArrayList<Magazine> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (books[i] instanceof Magazine) {
                list.add((Magazine) books[i]);
            }
        }

        return list.toArray(new Magazine[list.size()]);
    }

    /** 도서 리스트의 가격 총합 반환 */
    public int getTotalPrice() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += books[i].getPrice();
        }

        return sum;
    }

    /** 도서 가격의 평균 반환 */
    public double getPriceAvg() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += books[i].getPrice();
        }

        return (double)sum / size;
    }
}
