package hash;

import java.util.*;

import static java.util.Comparator.comparing;

public class BestAlbum {
    public static void main(String[] args) {
        new Solution().solution(new String[]{}, new int[]{});
    }

    static class Solution {
        public int[] solution(String[] genres, int[] plays) {
            Map<String,List<Integer>> map = new HashMap<>();

            for(int i=0; i < genres.length; i++){
                String genre = genres[i];
                int cnt = plays[i];
                map.putIfAbsent(genre, new ArrayList<>());
                map.get(genre).add(i);
            }

            List<String> sortedKeySet = new ArrayList<>(map.keySet());
            // sortedKeySet.sort(comparing(i -> map.get(i).size()));
            sortedKeySet.sort(comparing(i -> map.get(i).stream().mapToInt(a -> plays[a]).sum()).reversed());

            List<Integer> answerList = new ArrayList<>();
            for(String k : sortedKeySet){
                List<Integer> info = map.get(k);
                info.sort(comparing((Integer i) -> plays[i]).reversed()
                        .thenComparing(i -> i));
                // info.sort((a,b) -> {
                //     int result = Integer.valueOf(plays[b]).compareTo(plays[a]);
                //     if(result == 0)
                //         return Integer.valueOf(a).compareTo(b);
                //     return result;
                // });

                answerList.add(info.get(0));
                if(info.size() > 1)
                    answerList.add(info.get(1));
            }
            return answerList.stream().mapToInt(i -> i).toArray();
        }
    }
}
