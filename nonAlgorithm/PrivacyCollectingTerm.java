package nonAlgorithm;

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
        final int MONTH_DAYS = 28;
        public int[] solution(String today, String[] terms, String[] privacies) {
            int daysToday = convertDateToDays(today);

            Map<String,Integer> termsMap = new HashMap<>();
            for(int i=0; i < terms.length; i++){
                String[] temp = terms[i].split(" ");
                termsMap.put(temp[0], Integer.parseInt(temp[1]) * MONTH_DAYS);
            }

            List<Integer> result = new ArrayList<>();
            for(int i=0; i < privacies.length; i++){
                String[] info = privacies[i].split(" ");
                int privacyDays = convertDateToDays(info[0]);
                int expiredDays = privacyDays + termsMap.get(info[1]);
                if(daysToday >= expiredDays)
                    result.add(i);
            }
            return result.stream().mapToInt(i -> i+1).toArray();
        }

        int convertDateToDays(String date){
            String[] s = date.split("\\.");
            return (Integer.parseInt(s[0]) - 2000) * MONTH_DAYS * 12
                    + Integer.parseInt(s[1])* MONTH_DAYS
                    + Integer.parseInt(s[2]);
        }
    }
}



