package beforeClassification;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


class PrivacyCollectingTerm {
    public static void main(String[] args){
        String[] s = "2022.05.10".split("\\.");
        new Solution().solution("",s, s);
        System.out.println(s.length);
    }

    static class Solution {
        Map<String,Integer> termsMap;
        public int[] solution(String today, String[] terms, String[] privacies) {
            int[] answer = {};
            List<Integer> list = new ArrayList<>();

            int intToday = convertDateToInt(today);
            Map<String,Integer> termsMap = parseTerms(terms);

            int i=0;
            for(String privacy : privacies){
                i++;
                if(intToday < parsePrivacy(privacy))
                    list.add(i);
            }
            answer = new int[list.size()];

            for(int j=0;j<list.size();j++){
                answer[j]=list.get(j);
            }
            return answer;
        }

        int convertDateToInt(String date){
            String[] s = date.split(".");
            System.out.println(s.length);
            return Integer.parseInt(s[0])* 28 * 12 + Integer.parseInt(s[1])*28 + Integer.parseInt(s[2]);
        }

        Map<String,Integer> parseTerms(String[] terms){
            Map<String,Integer> map = new HashMap<>();

            String[] temp = null;
            for(String term : terms){
                temp = term.split(" ");
                map.put(temp[0], Integer.parseInt(temp[1]));
            }
            return map;
        }

        int parsePrivacy(String privacy){
            String[] temp = privacy.split(" ");

            return convertDateToInt(temp[0]) + termsMap.get(temp[1]) * 28;
        }
    }
}



