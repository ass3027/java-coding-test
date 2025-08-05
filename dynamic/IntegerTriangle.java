package dynamic;

public class IntegerTriangle {
    public static void main(String[] args) {
        int[][] arg = new int[][]{{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
        new Solution().solution(arg);
    }

static class Solution {
    public int solution(int[][] triangle) {
        int max = 0;
        int[] accArr = new int[]{triangle[0][0]};
        for(int i=1; i< triangle.length; i++){
            int[] cur = triangle[i];
            int[] newAccArr = new int[i+1];
            newAccArr[0] = accArr[0] + cur[0];
            newAccArr[i] = accArr[i-1] + cur[i];
            for(int j=1; j<cur.length-1; j++) {
                int value = cur[j] + Math.max(accArr[j - 1], accArr[j]);
                newAccArr[j] = value;
                max = Math.max(max, value);
            }
            accArr = newAccArr;
        }
        return max;
    }
}
}
