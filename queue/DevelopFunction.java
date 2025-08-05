package queue;

import java.util.ArrayList;

public class DevelopFunction {
    public static void main(String[] args) {
        new Solution().solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );
    }

    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int[] lastDay = new int[progresses.length];
            ArrayList<Integer> answer = new ArrayList<>();
            for(int i = 0; i < progresses.length; i++) {
                lastDay[i] = (int) Math.ceil( (double) (100 - progresses[i])/speeds[i]);
            }
            int max = lastDay[0];
            int count = 0;
            for (int j : lastDay) {
                if (max >= j) {
                    count++;
                } else {
                    max = j;
                    answer.add(count);
                    count = 1;
                }
            }
            answer.add(count);
            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
