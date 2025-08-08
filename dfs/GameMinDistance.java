package dfs;

import java.util.Stack;

public class GameMinDistance {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{});
    }
    static class Solution {
        int w,h;
        Stack<Integer> st = new Stack<>();
        int min= 10_000;
        int[][] maps;
        int answer = -1;
        boolean arrived = false;
        boolean[][] visited = new boolean[100][100];
        public int solution(int[][] maps) {
            this.h = maps.length;
            this.w = maps[0].length;
            this.maps = maps;
            dfs(0,0,0);
            return arrived ? min+1 : -1;
        }
        void dfs(int x, int y, int cnt){
            if(x < 0 || x >= this.w || y < 0 || y >= this.h || this.maps[y][x] == 0 || this.visited[y][x])
                return;

            if(x == this.w-1 && y == this.h-1){
                this.min = Math.min(this.min, cnt);
                this.arrived = true;
            }


            int index = x + y * this.w;
            // if(this.st.contains(index))
            if(this.visited[y][x])
                return;
            // this.st.add(index);
            this.visited[y][x] = true;
            cnt++;
            dfs(x-1,y, cnt);
            dfs(x+1,y, cnt);
            dfs(x,y+1, cnt);
            dfs(x,y-1, cnt);
            // this.st.pop();
            this.visited[y][x] = false;
        }
    }
}
