package hash;

import java.util.Arrays;
import java.util.HashMap;

public class CountClothes {

    public static void main(String[] args){
        new Solution().solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}});
        System.out.println((int) Math.ceil((double) 10/9));
    }
    static class Solution {
       public int solution(String[][] clothes){
            HashMap<String,Integer> map = new HashMap<>();
            Arrays.stream(clothes).forEach(it -> map.put(it[1], map.getOrDefault(it[1],0)+1));
            return map.values().stream().map(it-> it+1).reduce(1,Math::multiplyExact) -1 ;
        }
    }
}

