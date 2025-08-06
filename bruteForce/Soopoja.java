package bruteForce;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("SpellCheckingInspection")
public class Soopoja {
    public static void main (String[] args){
        String[] participant = {"leo", "kiki", "eden"};
        Map<String, Long> participantMap = Arrays.asList(participant).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};

//        Map<String,Set<String>> map = Arrays.stream(id_list).collect(Collectors.toMap(it->it.toString(),new HashSet<String>()));
//        new HashSet<>().forEach(it->it.);


//        Arrays.stream(report).distinct().map(it->it.split(" ")).forEach( it-> map.get(it[0]).add(it[1]));
        new Solution().solution(new int[]{1, 2, 3, 4, 5});
    }
    static class Solution {
        public int[] solution(int[] answers) {
            int[] answer = {};


            int[][] patterns = {{1,2,3,4,5}
                    ,{2,1,2,3,2,4,2,5}
                    ,{3,3,1,1,2,2,4,4,5,5}};

            int[] score = {0,0,0};


            for(int i=0; i<answers.length; i++){
                for(int j=0; j < patterns.length ;j++){
                    if(answers[i] == patterns[j][i%patterns[j].length]){
                        System.out.println(i+"" +j);
                        score[j]++;
                    }
                }
                // if(answers[i] == a[i])
            }
            return score;
//         int max =0;
//         List<Integer> list = new ArrayList<>();

//         for(int s=0;s<score.length-2;s++){
//             if(score[s] > score[s+1]){
//                 max = score[s];
//             }
//         }
//         for(int s=0;s<score.length-1;s++){
//             if(score[s]==max){
//                 list.add(s);
//             }
//         }
//         answer = new int[list.size()];
//         for(int s=0;s<list.size();s++){
//             answer[s] = list.get(s) + 1;
//         }

//         return answer;
        }
    }
}


