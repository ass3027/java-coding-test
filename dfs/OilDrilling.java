package dfs;

public class OilDrilling {
    public static void main(String[] args) {
//        int[][] input = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] input = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        new Solution().solution(input);
    }

    static class Solution {
        int[][] hasOil;
        int height;
        int width;
        boolean[][] isVisited;
        int[][] groups;
        int[] groupSize;
        public int solution(int[][] land) {
            height = land.length;
            width = land[0].length;
            this.hasOil = land;
            isVisited = new boolean[height][width];
            groups = new int[height][width];
            groupSize = new int[height * width];
            int max = -1;
            int groupNum = 1;

            for(int w = 0; w< width; w++)
                for(int h = 0; h< height; h++)
                    groupNum += dfs(h, w, groupNum);

            for(int w=0; w < width; w++) {
                boolean[] prevGroups = new boolean[groupNum];
                int sum = 0;
                for(int h=0; h < height; h++){
                    int targetGroupNum = groups[h][w];
                    if(targetGroupNum == 0 || prevGroups[targetGroupNum])
                        continue;

                    sum += groupSize[targetGroupNum];
                    prevGroups[targetGroupNum] = true;
                }
                max = Math.max(max,sum);
            }

            return max;

        }
        int dfs(int y, int x, int groupNum){
            boolean isOutOfBound = x < 0 || x == width || y < 0 || y == height;
            if(isOutOfBound || isVisited[y][x] || hasOil[y][x] == 0)
                return 0;

            isVisited[y][x] = true;
            groups[y][x] = groupNum;
            groupSize[groupNum] += 1;

            for(int[] moved : getAllMove(y, x))
                dfs(moved[1],moved[0],groupNum);

            return 1;
        }

        int[][] getAllMove(int y, int x){
            return new int[][]{{x,y-1}, {x,y+1}, {x-1,y},{x+1,y}};
        }
    }
}