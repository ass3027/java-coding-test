package hash;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public class Phoneketmon {
    public static void main(String[] args) {
        new Solution().solution(new int[]{1,2,3});
    }
    static class Solution {
        public int solution(int[] nums) {
            int answer = 0;
            Map<Integer,Integer> map = new HashMap<>();
            // Map<Integer,Integer> map
            // = Arrays.stream(nums).boxed().collect(Collectors.toMap(it-> it,it->it));
            // = Arrays.stream(nums).collect(Collectors.groupingBy(it->it+1,Collectors.counting()));
            for(int n : nums)
                map.put(n,1);

            return Math.min(nums.length/2, map.size());
        }
    }
}
