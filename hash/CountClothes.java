package hash;

import java.util.Arrays;
import static java.util.stream.Collectors.*;

public class CountClothes {

    public static void main(String[] args){
        new Solution().solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}});
        System.out.println((int) Math.ceil((double) 10/9));
    }

    static class Solution {
        public int solution(String[][] clothes){
            int NOTHING_SELECT = 1;
            return Arrays.stream(clothes)
                    .collect(groupingBy(cloth -> cloth[1], counting()))
                    .values().stream()
                    .mapToInt(it -> (int) (it + 1))
                    .reduce(1, Math::multiplyExact)
                    - NOTHING_SELECT;
        }
    }
}

