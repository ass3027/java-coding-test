package queue;

import java.util.LinkedList;

public class OSProcessScheduler {
    public static void main(String[] args) {
        new Solution().solution(new int[]{}, 0);
    }
    static class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;

            LinkedList<Integer> queue = new LinkedList<>();
            for(int i=0; i<priorities.length; i++){
                queue.add(i);
            }

            int max = 0;
            int cur = -1;
            int priority = 0;
            int cnt = 0;
            while(cur != location){
                max = queue.stream().mapToInt(i -> priorities[i]).max().getAsInt();

                while(true){
                    cur = queue.poll();
                    priority = priorities[cur];

                    if(priority == max)
                        break;

                    queue.add(cur);
                };

                cnt+=1;
            }

            return cnt;
        }
    }
}
