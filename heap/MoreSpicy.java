package heap;

import java.util.PriorityQueue;

public class MoreSpicy {
    public static void main(String[] args) {
        new Solution().solution(new int[]{}, 0);
    }
    static class Solution {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for(int i : scoville)
                pq.offer(i);

            int cnt = 0;
            for(;pq.size() > 1 && pq.peek() < K; cnt++)
                pq.offer(pq.poll() + pq.poll() * 2);

            return pq.poll() < K ? -1 : cnt;
        }
    }
}
