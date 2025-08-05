package dfs;

import java.util.Stack;

public class TargetNumber {
    static class Solution {
        public int solution(int[] numbers, int target) {
            int answer = 0;
            int[] currentArray = new int[numbers.length];
            Stack<int[]> stack = new Stack<>();

            stack.push(new int[]{0, numbers[0]});
            stack.push(new int[]{0, numbers[0] * -1});

            while(!stack.isEmpty()){
                int[] top = stack.pop();
                int index = top[0];
                currentArray[index] = top[1];

                if(index < numbers.length - 1){
                    index++;
                    stack.push(new int[]{index, numbers[index]});
                    stack.push(new int[]{index, numbers[index] * -1});
                }else {
                    int sum = 0;
                    for(int i : currentArray)
                        sum += i;

                    if(sum == target)
                        answer++;
                }
            }

            return answer;
        }
    }
}
