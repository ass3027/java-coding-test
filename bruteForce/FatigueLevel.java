package bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FatigueLevel {
    public static void main(String[] args) {
        new Solution().solution(0, new int[][]{});
    }
    static class Solution {
        int max = 0;
        int health = 0;
        int[][] dungeons;
        public int solution(int k, int[][] dungeons) {
            health = k;
            Stack<Integer> st = new Stack<>();
            this.dungeons = dungeons;
            bfs(st);
            return max;
        }

        void bfs(Stack<Integer> st){
            List<Integer> canGo = getCanGo(st);
            if(canGo.isEmpty()){
                max = Math.max(max, st.size());
                health += dungeons[st.pop()][1];
                return;
            }
            for(int i : canGo){
                st.push(i);
                // health -= dungeons[i][1];
                bfs(st);
            }
            health += dungeons[st.pop()][1];
        }

        List<Integer> getCanGo(Stack<Integer> st){
            List<Integer> result = new ArrayList<>();
            for(int i=0; i<dungeons.length; i++){
                if(!st.contains(i) && dungeons[i][0] <= health)
                    result.add(i);
            }
            return result;
        }
    }
}
