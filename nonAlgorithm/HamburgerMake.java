package nonAlgorithm;

import java.util.Stack;
public class HamburgerMake {
    public static void main(String[] args){

        int[] testCase = {2, 1, 1, 2, 3, 1, 2, 3, 1};
        new Solution().solution(testCase);
    }

    static class Solution {
        public int solution(int[] ingredient) {
            int BREAD = 1;
            int VEGETABLE = 2;
            int MEAT= 3;
            Stack<Integer> stack = new Stack<>();
            int answer = 0;
            for(int ig : ingredient){
                stack.push(ig);
                int size = stack.size();
                if(size >= 4
                    && stack.elementAt(size-4) == BREAD
                    && stack.elementAt(size-3) == VEGETABLE
                    && stack.elementAt(size-2) == MEAT
                    && stack.elementAt(size-1) == BREAD
                ){
                    answer++;
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                }
            }
            return answer;
        }
    }
}
