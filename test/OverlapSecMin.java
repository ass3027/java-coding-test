package test;

public class OverlapSecMin {

    public static void main(String[] args) {
        new OverlapSecMin2().solution(0,5,30,0,7,0);
    }

    static class OverlapSecMin2 {

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
