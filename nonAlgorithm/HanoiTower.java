package nonAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class HanoiTower {
    public static void main(String[] args) {
        new Solution().solution(15);
    }

    static class Solution {
        List<int[]> list;
        public int[][] solution(int n) {
            list = new ArrayList<>();
            moveTower(n,1,3);

            return list.toArray(new int[0][0]);
        }
        void moveTower(int level,int from, int to){
            if(level==1){
                list.add(new int[]{from,to});
                return;
            }
            moveTower(level-1,from,6-from-to);
            list.add(new int[]{from,to});
            moveTower(level-1,6-from-to,to);
        }
    }
}
