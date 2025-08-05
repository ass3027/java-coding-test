package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{});
    }

    static class Solution {
        public int solution(int[][] jobs) {
            PriorityQueue<int[]> insertTimes = new PriorityQueue<>(
                    Comparator.comparing(it -> it[1])
            );

            for(int i=0; i<jobs.length; i++)
                insertTimes.offer(new int[]{i, jobs[i][0], jobs[i][1]});

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    Comparator.comparing(it -> it[2] * 1000000 + it[1] * 1000 + it[0] * 1 )
            );

            int time = 0;
            int[] processTimes = new int[jobs.length];

            while(!insertTimes.isEmpty() || !pq.isEmpty()){
                while(!insertTimes.isEmpty() && insertTimes.peek()[1] <= time)
                    pq.offer(insertTimes.poll());

                if(pq.isEmpty()){
                    time++;
                    continue;
                }

                int[] curJob = pq.poll();
                int stTime = curJob[1];
                int endTime = time += curJob[2];
                processTimes[curJob[0]] = endTime - stTime;
                // processTimes[curJob[0]] = endTime;
                // processTimes[curJob[0]] = endTime;
            }
            int sum = 0;
            for(int i : processTimes)
                sum += i;

            return sum / processTimes.length;
            // return sum;
            // return processTimes[1];
        }
    }
}
