package Programmers;

class P72410_신규_아이디_추천 {
    public String solution(String new_id) {
        String answer = "";

        // 1단계 소문자 -> toLowerCase
        answer = new_id.toLowerCase();

        // 2단계
        answer = answer.replaceAll("[^a-z0-9\\-_\\.]", "");

        // 3단계
        answer = answer.replaceAll("\\.+", ".");

        // 4단계
        answer = answer.replaceAll("^\\.", "");
        answer = answer.replaceAll("\\.$", "");

        // 5단계
        if (answer.isEmpty()) {
            answer += "a";
        }

        // 6단계
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);

            answer = answer.replaceAll("\\.$", "");
        }

        // 7단계
        while (answer.length() < 3) {
            answer += String.valueOf(answer.charAt(answer.length() - 1));
        }

        return answer;
    }
}
