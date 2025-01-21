package SsafyHomework;
import java.util.*;

public class Main_2006_숫자출력_가위바위보게임 {
    public static void printDigitTest1() {
        StringBuilder sb = new StringBuilder();

        int cnt = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j < i) {
                    sb.append(" ");
                    // 띄어쓰기
                    if (cnt >= 10 && i - j == 1) {
                        sb.append("     ");
                    } else {
                        sb.append("      ");
                    }
                } else {
                    sb.append(cnt);
                    if (cnt < 10) {
                        sb.append("      ");
                    } else {
                        sb.append("     ");
                    }
                    cnt += 1;
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void printDigitTest2() {
        StringBuilder sb = new StringBuilder();

        int cnt = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < 3 && j < i) {
                    sb.append(" ");

                    // 띄어쓰기
                    if (cnt >= 10 && i - j == 1) {
                        sb.append("     ");
                    } else {
                        sb.append("      ");
                    }
                } else if (i >= 3 && j < 5 - i - 1) {
                    sb.append(" ");
                    sb.append("      ");
                } else if (i < 3 && j >= (5 - i)) {
                    sb.append(" ");

                    // 띄어쓰기
                    if (cnt >= 10 && i - j == 1) {
                        sb.append("     ");
                    } else {
                        sb.append("      ");
                    }
                } else if (i >= 3 && 5 - j < 5 - i) {
                    sb.append(" ");

                    // 띄어쓰기
                    if (cnt >= 10 && i - j == 1) {
                        sb.append("     ");
                    } else {
                        sb.append("      ");
                    }
                } else {
                    sb.append(cnt);

                    if (cnt < 10) {
                        sb.append("      ");
                    } else {
                        sb.append("     ");
                    }
                    cnt += 1;
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void gameTest() {
        System.out.println("가위바위보 게임을 시작합니다. 아래 보기 중 하나를 고르세요.");
        System.out.println();
        System.out.println("1. 5판 3승");
        System.out.println("2. 3판 2승");
        System.out.println("3. 1판 1승");

        Scanner sc = new Scanner(System.in);
        System.out.println("번호를 입력하세요. ");
        int num = sc.nextInt();

        int compScore = -1;
        int myScore = -1;
        int snum = 0;
        if (num == 1) {
            myScore = 3;
            compScore = 3;
            snum = 5;
        } else if (num == 2) {
            myScore = 2;
            compScore = 2;
            snum = 3;
        } else if (num == 3) {
            myScore = 1;
            compScore = 1;
            snum = 1;
        }

        while (snum-- > 0) {
            System.out.print("가위바위보 중 하나 입력: ");
            String input = sc.next();

            int compValue = (int) (Math.random() * 3) + 1;

            int res = 0; // 1 이김 2 짐 3 비김
            if (input.equals("가위")) {
                if (compValue == 2) {
                    res = 2;
                } else if (compValue == 3) {
                    res = 1;
                } else {
                    res = 3;
                }
            } else if (input.equals("바위")) {
                if (compValue == 3) {
                    res = 2;
                } else if (compValue == 1) {
                    res = 1;
                } else {
                    res = 3;
                }
            } else if (input.equals("보")) {
                if (compValue == 1) {
                    res = 2;
                } else if (compValue == 2) {
                    res = 1;
                } else {
                    res = 3;
                }
            }

            if (res == 1) {
                myScore -= 1;
                System.out.println("이겼습니다!!!");
            } else if (res == 2) {
                compScore -= 1;
                System.out.println("졌습니다!!!");
            } else {
                System.out.println("비겼습니다!!!");
            }

            if (myScore == 0) {
                System.out.println("###사용자 승!!!");
                break;
            } else if (compScore == 0) {
                System.out.println("###컴퓨터 승!!!");
                break;
            }
        }

        if (myScore > 0 && compScore > 0) {
            System.out.println("###승부가 결정나지 않고 게임이 종료되었습니다!!!");
        }
    }

    public static void main(String[] args) {
        //printDigitTest2();
        gameTest();
    }
}