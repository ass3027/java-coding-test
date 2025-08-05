package hash;

import java.util.*;

public class SingoResult {
    public static void main(String[] args){
        new Solution2().solution(
                new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2
        );
    }

    static class Solution2 {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = {};
            answer = new int[id_list.length];
            Map<String,Set<String>> rm = new HashMap<>();
            Map<String,Integer> mcm = new HashMap<>();

            for(String id : id_list){
                rm.put(id,new HashSet<>());
                mcm.put(id,0);
            }

            Arrays.stream(report).distinct()
                    .map(it->it.split(" "))
                    .forEach( it-> rm.getOrDefault(it[1], new HashSet<>()).add(it[0]));
                // reportCount.put(it[0],reportCount.getOrDefault(it[0],0) + 1);

            rm.entrySet().stream()
                    .filter(it->it.getValue().size() >= k)
                    .forEach( it -> {
//                    mcm.put(it.getKey(),mcm.getOrDefault(it.getKey(),0)+1 );
                        it.getValue()
                                .forEach( b -> mcm.put(b,mcm.getOrDefault(b,0)+1 ));
                    });

            for(int i=0;i<id_list.length;i++){
                answer[i] = mcm.get(id_list[i]);
            }

            return answer;
        }
    }
}