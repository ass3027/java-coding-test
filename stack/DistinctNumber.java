package stack;

import java.util.ArrayList;

public class DistinctNumber {
    public static void main(String[] args) {
        new Solution().solution(new int[]{});
    }
    static class Solution {
//     public int[] solution(int []arr) {
//         int[] stack = new int[1000000];
//         int sp = 0;

//         stack[0] = arr[0];
//         for(int i=1;i<arr.length;i++){
//             if(arr[i] == arr[i-1]) continue;
//             stack[++sp] = arr[i];
//         }
//         return Arrays.copyOfRange(stack,0,sp+1);
//     }

        // public Stack<Integer> solution(int []arr) {
        //     Stack<Integer> stack = new Stack<>();
        //     stack.push(arr[0]);
        //     for(int i : arr){
        //         if(i != stack.peek()) stack.push(i);
        //     }
        //     return stack;
        // }

        public ArrayList<Integer> solution(int []arr) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(arr[0]);
            for(int i : arr){
                if(i != list.get(list.size()-1)) list.add(i);
            }
            return list;
        }
    }
}
