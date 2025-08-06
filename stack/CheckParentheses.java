package stack;

import java.util.Stack;

public class CheckParentheses {
    public static void main(String[] args) {
        new Solution().solution("()()");
    }
    static class Solution {
        boolean solution(String s) {
//         int cnt = 0;

//         for(char c : s.toCharArray()){
//             cnt += c == '(' ? 1 : -1;
//             if(cnt == -1)
//                 return false;
//         }

//         return cnt == 0;
            Stack<Boolean> stack = new Stack<>();
            int v = 0;
            for(char c : s.toCharArray()){
                if(c == '(')
                    stack.push(true);
                else {
                    if(stack.isEmpty())
                        return false;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }
}
