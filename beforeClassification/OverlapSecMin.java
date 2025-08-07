package beforeClassification;

public class OverlapSecMin {

    public static void main(String[] args) {
        new Solution().solution(0,5,30,0,7,0);
    }

    static class Solution2 {

        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
//            int[] start = new int[]{h1, m1, s1};
//            int[] middleStart =  new int[]{h1, m1 + s1 == 0 ? 0 : 1, s1};
//            int[] middleEnd = new int[]{h2, m2, s2};
//            int[] end = new int[]{h2, m2, s2};
            int m1m = m1 + (s1 == 0 ? 0 :1);
            int h1m = h1 + (m1m == 60 ? 1 : 0);
            m1m = m1m % 60;

            int startCount = (s1 <= h1 * 5 ? 1 : 0) + (s1 <= m1 ? 1 : 0);
            int endCount = (s2 <= h2 * 5 ? 1 : 0) + (s2 <= m2 ? 1 : 0);

            boolean has12 = h1m < 12 && h2 >= 12 || h1m == 12 && m1m == 0 || h2 == 12 && m2 == 0;
            int countOfMinute = ((h2 * 60 + m2) - (h1 * 60 + m1)) * 2;
            int middleCount = countOfMinute < 0 ? 0 : countOfMinute
                    + (h1 == 0 && m1 == 0 ? 1 : 0)
                    + (has12  ? 1 : 0);
            return startCount + endCount + middleCount;
        }
    }

    static class Solution {

        int startTime;
        int endTime;
        double hSpeed = (double)360/60/60/24;
        double mSpeed = (double)360/60/60;
        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
            int count = 0;
            startTime = getTime(h1,m1,s1);
            endTime = getTime(h2,m2,s2);

            int remainTime = endTime - startTime;
            while(true){
                if(remainTime > 60*60 ){
                    count += 119;
                    remainTime -= 3600;
                }
                else if(remainTime > 60){
                    count += 2;
                    remainTime -= 60;
                }else {

                    break;
                }
            }
            return count - startTime == 0 ? 1 : 0;
        }

        int getTime(int h, int m, int s){
            return h * 60 * 60 + m* 60 + s;
        }
        boolean isOverlapInMinuteHHMM(int currentTime){
            double hPosition = hSpeed * currentTime;
            double mPosition = mSpeed * currentTime % 360;
            return hPosition-mPosition > 0 && hPosition-mPosition < (mSpeed-hSpeed) * 60;
        }

        boolean isOverlapInLimitHHMM(int currentTime,int timeLimit){
            double hPosition = hSpeed * currentTime;
            double mPosition = mSpeed * currentTime % 360;
            return hPosition-mPosition > 0 && hPosition-mPosition < (mSpeed-hSpeed) * timeLimit;
        }

    }
}
