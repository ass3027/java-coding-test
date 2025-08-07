package beforeClassification;

import java.util.*;

public class ParkingFeeCalc {
    public static void main(String[] args) {
        new Solution().solution(
                new int[]{180, 5000, 10, 600}
                , new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
    }
    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            final int DEFAULT_MINUTE = fees[0];
            final int DEFAULT_FEE = fees[1];
            final int TIME_UNIT = fees[2];
            final int FEE_UNIT = fees[3];
            final int MAX_OUT_TIME = parseMinutes("23:59");

            HashMap<String, List<Integer>> carNumberMinuteMap = new HashMap<>();
            for (String record : records) {
                StringTokenizer tokenizer = new StringTokenizer(record);
                String time = tokenizer.nextToken();
                int minute = parseMinutes(time);
                String carNumber = tokenizer.nextToken();
                carNumberMinuteMap.computeIfAbsent(carNumber, k -> new ArrayList<>())
                        .add(minute);
            }

            HashMap<String, Integer> result = new HashMap<>();
            for(String key : carNumberMinuteMap.keySet()){
                List<Integer> minuteList =  carNumberMinuteMap.get(key);
                if(minuteList.size() % 2 != 0)
                    minuteList.add(MAX_OUT_TIME);

                int totalTime = 0;
                for(int i = 0; i < minuteList.size(); i+=2){
                    int in =  minuteList.get(i);
                    int out = minuteList.get(i+1);
                    totalTime += out - in;
                }
                int parkingTime = totalTime - DEFAULT_MINUTE;
                parkingTime = Math.max(parkingTime, 0);
                int timeCount = parkingTime / TIME_UNIT;
                timeCount += (parkingTime % TIME_UNIT != 0) ? 1: 0;
                int totalFee = DEFAULT_FEE + timeCount * FEE_UNIT;
                result.put(key, totalFee);
            }

            return result.keySet().stream()
                    .sorted()
                    .mapToInt(result::get)
                    .toArray();
        }

        int parseMinutes(String time){
            String[] split = time.split(":");
            return Integer.parseInt(split[0]) * 60
                    + Integer.parseInt(split[1]);
        }
    }
}
