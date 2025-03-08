import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class UserSolution {
    private int N, L;
    private List<TreeSet<Integer>> leagues = new ArrayList<>();
    private int[] abilities;

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        this.abilities = Arrays.copyOf(mAbility, N);
        this.leagues.clear();

        Comparator<Integer> comparator = (a, b) -> {
            if (abilities[a] != abilities[b]) {
                return abilities[b] - abilities[a]; // 능력 값이 높은 순
            }
            return a - b; // ID가 낮은 순
        };

        for (int i = 0; i < L; i++) {
            leagues.add(new TreeSet<>(comparator));
        }

        for (int i = 0; i < N; i++) {
            leagues.get(i / (N/L)).add(i);
        }
    }

    int move() {
        int moveSum = 0;
        List<int[]> movingPlayers = new ArrayList<>();

        for (int i = 1; i < L; i++) {
            int best = leagues.get(i).first();
            int worst = leagues.get(i - 1).last();

            leagues.get(i).remove(best);
            leagues.get(i - 1).remove(worst);

            movingPlayers.add(new int[] { i, best, i - 1, worst });
            moveSum += best + worst;
        }

        for (int[] move : movingPlayers) {
            leagues.get(move[0]).add(move[3]);
            leagues.get(move[2]).add(move[1]);
        }

        return moveSum;
    }

    int trade() {
        int tradeSum = 0;
        List<int[]> tradingPlayers = new ArrayList<>();

        for (int i = 1; i < L; i++) {
            int best = leagues.get(i).first();
            int midIdx = (leagues.get(i - 1).size() + 1) / 2 - 1;

            Iterator<Integer> it = leagues.get(i - 1).iterator();
            int midPlayer = 0;
            for (int j = 0; j <= midIdx; j++) {
                midPlayer = it.next();
            }

            leagues.get(i).remove(best);
            leagues.get(i - 1).remove(midPlayer);

            tradingPlayers.add(new int[] { i, best, i - 1, midPlayer });
            tradeSum += best + midPlayer;
        }

        for (int[] trade : tradingPlayers) {
            leagues.get(trade[0]).add(trade[3]);
            leagues.get(trade[2]).add(trade[1]);
        }

        return tradeSum;
    }
}