package studio.abos.mc.strangeadventures;

import lombok.experimental.UtilityClass;
import org.jspecify.annotations.Nullable;

@UtilityClass
public class MathUtil {

    /**
     * Calculates n!, overflows are silently ignored, so use with care.
     */
    public int faculty(int n) {
        if (n <= 1) {
            return 1;
        }
        return switch (n) { // some default cases
            case 2 -> 2;
            case 3 -> 6;
            case 4 -> 24;
            default -> n * faculty(n-1);
        };
    }

    @Nullable
    public int[][] permutations(int n) {
        if (n < 0) {
            return null;
        }
        if (n == 0) {
            return new int[0][];
        }
        else if (n == 1) {
            return new int[][]{new int[]{0}};
        }
        else if (n == 2) {
            return new int[][]{new int[]{0,1}, new int[]{1,0}};
        }
        else if (n == 3) {
            return new int[][]{new int[]{0,1,2}, new int[]{0,2,1}, new int[]{1,0,2}, new int[]{1,2,0}, new int[]{2,0,1}, new int[]{2,1,0}};
        }
        int[][] result = new int[faculty(n)][n];
        permutationsRec(n, result, 0, new int[1]);
        return result;
    }

    // FIXME only works in theory, tests are needed
    private void permutationsRec(int n, int[][] permutations, int index, int[] current) {
        outer: for (int i = 0; i < n; i++) {
            for (int j = 0; j < index; j++) {
                if (permutations[current[0]][j] == i) {
                    continue outer;
                }
            }
            permutations[current[0]][index] = i;
            if (index + 1 == n) {
                if (current[0] + 1 < permutations.length) {
                    System.arraycopy(permutations[current[0]], 0, permutations[current[0]+1], 0, n);
                    current[0]++;
                }
            }
            else {
                permutationsRec(n, permutations, index+1, current);
            }
        }
    }

}
