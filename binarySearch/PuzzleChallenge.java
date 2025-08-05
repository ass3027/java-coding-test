package binarySearch;

public class PuzzleChallenge {

    public static void main(String[] args) {
        new Solution().solution(new int[]{1,99999,100000,99995}, new int[]{9999,9001,9999,9001},3456789012L);
    }

    static class Solution {
        int[] diffs,times;
        long limit;
        public int solution(int[] diffs, int[] times, long limit) {
            this.diffs = diffs;
            this.times = times;
            this.limit = limit;

            return (int) binarySearch();
        }

        boolean check(long level){
            int time_prev = 0;
            long total = 0;
            for(int i=0; i< diffs.length; i++){
                if(diffs[i] > level)
                    total += (times[i] + time_prev) * (diffs[i] - level);

                total += times[i];
                time_prev = times[i];
            }

            return total <= limit;
        }

        long binarySearch(){
            long high = (long) Math.pow(10,15);
            long low = 1L;
            long mid;

            while(low <= high){
                mid = (low + high) / 2;
                if(check(mid)){
                    high = mid - 1;
                } else{
                    low = mid + 1;
                }
            }

            return low;
        }
    }
}
