package sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LargestNumber {
    public static void main(String[] args) {
        new Solution().solution(new int[]{6, 10, 2});
    }
    static class Solution {
        public String solution(int[] numbers) {
            String ans = Arrays.stream(numbers)
                    .boxed()
                    .sorted((a,b) -> {
                        String sa = String.valueOf(a);
                        String sb = String.valueOf(b);
                        int ab = Integer.parseInt(sa + sb);
                        int ba = Integer.parseInt(sb + sa);
                        return Integer.compare(ba, ab);
                    })
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            return ans.charAt(0) == '0' ? "0" : ans;
        }
    }
}
