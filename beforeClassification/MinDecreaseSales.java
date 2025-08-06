package beforeClassification;

import java.util.*;
import java.util.stream.Collectors;

public class MinDecreaseSales {

    public static void main(String[] args) {
        new Solution().solution(new int[]{5, 6, 5, 3, 4}, new int[][]{{2, 3}, {1, 4}, {2, 5}, {1, 2}});
    }

    static class Solution {
        int min = Integer.MAX_VALUE;
        int[] sales;
        int[][] links;
        List<Set<Integer>> subTreeList;

        public int solution(int[] sales, int[][] links) {
            this.sales = sales;
            this.links = links;

            subTreeList = initSubTree(links);

            getMin(new ArrayList<>());

            return this.min;
        }

        private void getMin(List<Integer> current) {
            if(current.size() == this.subTreeList.size()){
                int currentSum = new HashSet<>(current).stream().mapToInt(i->this.sales[i-1]).sum();
                this.min = Math.min(this.min, currentSum);
            }else{
                for(int i : this.subTreeList.get(current.size())){
                    current.add(i);
                    getMin(current);
                    current.remove(current.size()-1);
                }
            }
        }

        private List<Set<Integer>> initSubTree(int[][] links) {

            // return Arrays.stream(links)
            //         .collect(Collectors.groupingBy(
            //                 i -> i[0],Collectors.mapping(i -> i[1], Collectors.toSet())
            //         ))
            //         .entrySet().stream()
            //         .map(e -> {
            //             Set<Integer> set = new HashSet<>();
            //             set.add(e.getKey());
            //             set.addAll(e.getValue());
            //             return set;
            //         }).collect(Collectors.toList());
            List<Set<Integer>> result = new ArrayList<>();
            Map<Integer,Set<Integer>> map = new HashMap<>();
            // for(int[] link : links){
            for(int i=0;i<links.length;i++){
                int[] link = links[i];
                if(map.get(link[0]) == null)
                    map.put(link[0], new HashSet<>());
                Set<Integer> subTree = map.get(link[0]);

                subTree.add(link[1]);
            }

            return map.entrySet().stream()
                    .map(e -> {
                        Set<Integer> set = new HashSet<>();
                        set.add(e.getKey());
                        set.addAll(e.getValue());
                        return set;
                    }).collect(Collectors.toList());
        }
    }
}
