package greedy;

import java.util.Arrays;

public class RescueBoat {
    public static void main(String[] args) {
        new Solution().solution(new int[]{70},70);
    }
    static class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            int minIndex=0;
            int i=people.length - 1;
            for(; minIndex < i; i--){
                if(people[i] + people[minIndex] <= limit)
                    minIndex++;
            }
            return people.length - minIndex;
        }
    }
}
