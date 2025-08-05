package nonAlgorithm;
import java.util.Arrays;

public class WallpaperOrganize {
    public static void main(String[] args){
        String[] testCase1 = {
                ".#...",
                "..#..",
                "...#."
        };
        new Solution().solution(testCase1);
    }

    static class Solution {
        public int[] solution(String[] wallpaper) {
            int[][] files = new int[50 * 50][2];
            int fp = -1;
            String[] line;
            for (int i = 0; i < wallpaper.length; i++) {
                line = wallpaper[i].split("");
                for (int j = 0; j < line.length; j++) {
                    if (line[j].equals("#")) {
                        files[++fp] = new int[]{i, j};
                    }
                }
            }
//            files = Arrays.copyOfRange(files,0,fp+1);
            int[] yArray = Arrays.stream(files).sequential().limit(fp + 1).mapToInt(it -> it[1]).toArray();
            int lux = files[0][0];
            int luy = Arrays.stream(yArray).min().getAsInt();
            int rdx = files[fp][0] + 1;
            int rdy = Arrays.stream(yArray).max().getAsInt() + 1;

            return new int[]{lux, luy, rdx, rdy};
        }
    }
}
