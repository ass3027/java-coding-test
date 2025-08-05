package dfs;

import java.util.*;

public class DoughnutGraph {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}})));
    }

    static class Solution {
        Set<Integer> nodeNumInfo = new HashSet<>();
        Map<Integer,Set<Integer>> wiredInfoMap = new HashMap<>();
        int newNum;
        private int doughnutCnt;
        private int stickCnt;
        private int eightCnt;

        public int[] solution(int[][] edges) {
            init(edges);

            Set<Integer> graphStartNumSet = this.wiredInfoMap.get(this.newNum);
            for(int graphStartNum : graphStartNumSet)
                recursiveDFS(graphStartNum, graphStartNum);
//                whileDFS(graphStartNum, graphStartNum);

            return new int[]{newNum, this.doughnutCnt, this.stickCnt, eightCnt};
        }

        void init(int[][] edges) {

            for(int[] edge : edges){
                this.nodeNumInfo.add(edge[0]);
                this.nodeNumInfo.add(edge[1]);
                wiredInfoMap.computeIfAbsent(edge[0], k -> new HashSet<>())
                        .add(edge[1]);
                wiredInfoMap.putIfAbsent(edge[1], new HashSet<>());
            }

            this.newNum = getNewNum(edges);
        }

        private int getNewNum(int[][] edges) {
            Set<Integer> newNumCheck = new HashSet<>(this.nodeNumInfo);
            for(int[] edge : edges) {
                newNumCheck.remove(edge[1]);
            }

            for (int target : newNumCheck) {
                if (wiredInfoMap.get(target).size() >= 2) {
                    return target;
                }
            }
            throw new RuntimeException("Can't find newNum");
        }

        void whileDFS(int nodeNum, int startNum){
            while(true){
                Set<Integer> children = wiredInfoMap.get(nodeNum);
                int size = children.size();
                if(size == 0){
                    this.stickCnt++;
                    break;
                }
                if (size == 2){
                    this.eightCnt++;
                    break;
                }
                if (size == 1){
                    nodeNum = children.iterator().next();
                    if(nodeNum == startNum){
                        this.doughnutCnt++;
                        break;
                    }
                }
            }
        }

        void recursiveDFS(int nodeNum, int startNum){
            Set<Integer> children = wiredInfoMap.get(nodeNum);
            int size = children.size();
            if(size == 0){
                this.stickCnt++;
                return;
            }
            if (size == 2){
                this.eightCnt++;
                return;
            }
            int next = children.iterator().next();
            if (size == 1 && next == startNum){
                this.doughnutCnt++;
                return;
            }
            recursiveDFS(next, startNum);
        }
    }
    static class SimpleSolution {
        static int N = 1_000_000;

        public int[] solution(int[][] edges) {
            int[] ingoing = new int[N];
            int[] outgoing = new int[N];
            for(int[] edge : edges) {
                outgoing[edge[0]-1]++;
                ingoing[edge[1]-1]++;
            }
            int created = 0;
            int eight = 0;
            int stick = 0;
            for(int i=0; i<N; i++) {
                if(outgoing[i] >= 2) {
                    if(ingoing[i] == 0) {
                        created = i;
                    }else {
                        eight++;
                    }
                }else if(outgoing[i] == 0 && ingoing[i] > 0){
                    stick++;
                }
            }
            return new int[] {created+1,outgoing[created]-eight-stick,stick,eight};
        }
    }
}
