package queue;

import java.util.LinkedList;

public class TruckOnTheBridge {
    public static void main(String[] args) {
        new Solution().solution(0, 0, new int[]{});
    }
    static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            LinkedList<Integer> brQue = new LinkedList<>();
            LinkedList<Integer> waitQue = new LinkedList<>();

            for(int tw : truck_weights)
                waitQue.add(tw);

            for(int i=0; i<bridge_length; i++)
                brQue.add(0);

            int time = 0;
            int brW = 0;
            while(!waitQue.isEmpty() || !brQue.isEmpty()){
                time++;

                int popV = brQue.poll();

                if(waitQue.isEmpty())
                    continue;

                brW -= popV;

                int nextTw = waitQue.peek();
                if(brW + nextTw <= weight){
                    waitQue.poll();
                    brQue.add(nextTw);
                    brW+=nextTw;
                }else
                    brQue.add(0);
            }

            return time;
        }
    }
}
