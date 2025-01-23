package SsafyHomework.step3;

public class BookTest {
    public static void main(String[] args) {
        // 도서 관리를 위한 객체 생성
        BookManager manager = BookManager.getInstance();

        // 테스트를 위한 Book 객체 3개 생성
        Book b1 = new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법");
        Book b2 = new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용");
        Book b3 = new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링");
        Book b4 = new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 2021, 1);

        manager.add(b1);
        manager.add(b2);
        manager.add(b3);
        manager.add(b4);

        manager.printTitle("도서 전체 목록");
        Book[] books = manager.getList();
        for (Book book : books) {
            System.out.println(book);
        }

        manager.printTitle("일반 도서 목록");
        Book[] normalBooks = manager.getBooks();
        for (Book book : normalBooks) {
            System.out.println(book);
        }

        manager.printTitle("잡지 목록");
        Magazine[] magazines = manager.getMagazines();
        for (Magazine magazine : magazines) {
            System.out.println(magazine);
        }

        manager.printTitle("도서 제목 포함검색:Java");
        Book[] foundBooks = manager.searchByTitle("Java");
        for (Book foundBook : foundBooks) {
            System.out.println(foundBook);
        }

        System.out.printf("도서 가격 총합 : %d\n", manager.getTotalPrice());
        System.out.printf("도서 가격 평균 : %.1f", manager.getPriceAvg());
    }
}
