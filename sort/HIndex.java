package sort;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        new Solution().solution(new int[]{3, 0, 6 ,1, 5});
    }
    static class Solution {
        public int solution(int[] citations) {
            Arrays.sort(citations);

            int max = 0;
            int min =0;
            for(int i = citations.length-1; i > -1; i--){
                min = (int)Math.min(citations[i], citations.length - i);
                if(max < min) max = min;
            }

            return max;
//         Arrays.sort(citations);
//         for(int i = citations.length-1; i > -1; i--){
//             if(citations[i] <= i+1)
//                 return citations[i];
//         }

//         return 0;
            // for(int i=Arrays.stream(citations).max().getAsInt() ;i>=0;i--){
            //     final int d = i;
            //     if(Arrays.stream(citations).filter(t->t>=d).count() >= i)
            //         return i;
            // }
            // return 0;
        }
    }
}
