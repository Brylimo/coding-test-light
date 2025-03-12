package SWEA;
import java.io.IOException;
import java.util.*;

public class Solved_성적조회 {
    static class Student {
        int id, grade, score;
        String gender;

        Student(int id, int grade, String gender, int score) {
            this.id = id;
            this.grade = grade;
            this.gender = gender;
            this.score = score;
        }
    }

    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<String, TreeMap<Integer, TreeSet<Integer>>> gradeGenderMap = new HashMap<>();

    public static void init() {
        students.clear();
        gradeGenderMap.clear();
    }

    public static int add(int mId, int mGrade, String mGender, int mScore) {
        Student student = new Student(mId, mGrade, mGender, mScore);
        students.put(mId, student);

        String key = mGrade + "-" + mGender;
        gradeGenderMap.putIfAbsent(key, new TreeMap<>());
        gradeGenderMap.get(key).putIfAbsent(mScore, new TreeSet<>());
        gradeGenderMap.get(key).get(mScore).add(mId);

        return getHighestId(mGrade, mGender);
    }

    public static int remove(int mId) {
        if (!students.containsKey(mId)) return 0;

        Student student = students.remove(mId);
        String key = student.grade + "-" + student.gender;
        TreeMap<Integer, TreeSet<Integer>> scoreMap = gradeGenderMap.get(key);

        scoreMap.get(student.score).remove(mId);
        if (scoreMap.get(student.score).isEmpty()) {
            scoreMap.remove(student.score);
        }
        if (scoreMap.isEmpty()) {
            gradeGenderMap.remove(key);
            return 0;
        }

        return getLowestId(student.grade, student.gender);
    }

    public static int query(int mGradeCnt, int[] mGrade, int mGenderCnt, String[] mGender, int mScore) {
        int minId = Integer.MAX_VALUE;
        int minScore = Integer.MAX_VALUE;

        for (int grade : mGrade) {
            for (String gender : mGender) {
                String key = grade + "-" + gender;
                if (!gradeGenderMap.containsKey(key)) continue;

                TreeMap<Integer, TreeSet<Integer>> scoreMap = gradeGenderMap.get(key);
                NavigableMap<Integer, TreeSet<Integer>> tailMap = scoreMap.tailMap(mScore, true);

                for (TreeSet<Integer> ids : tailMap.values()) {
                    if (!ids.isEmpty()) {
                        Iterator<Integer> it = ids.iterator();

                        while (it.hasNext()) {
                            int ee = it.next();
                            if (students.get(ee).score <= minScore) {

                                if (students.get(ee).score == minScore && students.get(ee).id > minId) continue;

                                minScore = students.get(ee).score;
                                minId = students.get(ee).id;
                            }
                        }
                    }
                }
            }
        }

        return minId == Integer.MAX_VALUE ? 0 : minId;
    }

    private static int getHighestId(int grade, String gender) {
        String key = grade + "-" + gender;
        if (!gradeGenderMap.containsKey(key)) return 0;

        TreeMap<Integer, TreeSet<Integer>> scoreMap = gradeGenderMap.get(key);
        int maxScore = scoreMap.lastKey();
        return scoreMap.get(maxScore).last();
    }

    private static int getLowestId(int grade, String gender) {
        String key = grade + "-" + gender;
        if (!gradeGenderMap.containsKey(key)) return 0;

        TreeMap<Integer, TreeSet<Integer>> scoreMap = gradeGenderMap.get(key);
        int minScore = scoreMap.firstKey();
        return scoreMap.get(minScore).first();
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(add(500, 1, "male", 1250));
        System.out.println(add(400, 3, "female", 2900));
        System.out.println(add(900, 2, "female", 2500));
        System.out.println(add(700, 2, "male", 2500));
        System.out.println(add(600, 1, "female", 1750));
        System.out.println(add(800, 3, "male", 1000));
        System.out.println(add(300, 2, "female", 2000));

        System.out.println(query(2, new int[] {2, 3}, 2, new String[] {"male", "female"}, 2500));
        System.out.println(query(3, new int[] {1, 2, 3}, 1, new String[] {"male"}, 1100));
        System.out.println(add(100, 2, "female", 2500));
        System.out.println(query(1, new int[] {2}, 2, new String[] {"male", "female"}, 2200));

        System.out.println(remove(300));
        System.out.println(remove(200));

        System.out.println(add(300, 3, "female", 3000));
        System.out.println(query(2, new int[] {1, 3}, 1, new String[] {"female"}, 1800));

        System.out.println(remove(800));
        System.out.println(query(3, new int[] {1, 2, 3}, 2, new String[] {"male", "female"}, 1000));
    }
}