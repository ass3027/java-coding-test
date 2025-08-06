package bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FatigueLevel {
    public static void main(String[] args) {
        new Solution().solution(0, new int[][]{});
    }

    static class Solution {
        int[][] dungeons;
        int IS_VISITED = -1;
        public int solution(int k, int[][] dungeons) {
            this.dungeons = dungeons;
            return dfs(k, 0);
        }

        int dfs(int health, int current) {
            int max = current;
            for (int i = 0; i < dungeons.length; i++) {
                if (dungeons[i][0] == IS_VISITED || dungeons[i][0] > health)
                    continue;

                int temp = dungeons[i][0];
                dungeons[i][0] = IS_VISITED;
                max = Math.max(max, dfs(health - dungeons[i][1], current + 1));
                dungeons[i][0] = temp;
            }
            return max;
        }
    }

    @SuppressWarnings("unused")
    static class SolutionBefore {
        int max = 0;
        int health = 0;
        int[][] dungeons;
        public int solution(int k, int[][] dungeons) {
            health = k;
            this.dungeons = dungeons;
            dfs(new Stack<>());
            return max;
        }

        void dfs(Stack<Integer> st){
            List<Integer> canGo = getCanGo(st);
            if(canGo.isEmpty()){
                max = Math.max(max, st.size());
                health += dungeons[st.pop()][1];
                return;
            }
            for(int i : canGo){
                st.push(i);
                health -= dungeons[i][1];
                dfs(st);
            }
            if(!st.isEmpty())
                health += dungeons[st.pop()][1];
        }

        List<Integer> getCanGo(Stack<Integer> st){
            List<Integer> result = new ArrayList<>();
            for(int i=0; i<dungeons.length; i++)
                if(!st.contains(i) && dungeons[i][0] <= health)
                    result.add(i);

            return result;
        }
    }
}
