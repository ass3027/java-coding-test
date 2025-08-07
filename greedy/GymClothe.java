package greedy;

public class GymClothe {
    static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            boolean[] lostArray = new boolean[n];
            for (int j : lost) lostArray[j-1] = true;

            boolean[] reserveArray = new boolean[n];
            for (int k : reserve) {
                if (lostArray[k - 1]) lostArray[k - 1] = false;
                else reserveArray[k - 1] = true;
            }

            for(int i=0; i<lostArray.length; i++){
                if(!lostArray[i]) continue;
                if(i - 1 >= 0 && reserveArray[i - 1]){
                    lostArray[i] = false;
                    reserveArray[i - 1] = false;
                }
                else if(i + 1 < n && reserveArray[i + 1]){
                    lostArray[i] = false;
                    reserveArray[i + 1] = false;
                }
            }
            int answer = 0;
            for(boolean isLost : lostArray)
                if(!isLost) answer++;

            return answer;
        }
    }
}
