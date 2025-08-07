package greedy;

public class JoyStick {
    public static void main(String[] args) {
        new Solution().solution("JEROEN");
    }
    static class Solution {
        public int solution(String name) {

            int[] intString = new int[name.length()];
            for(int i=0; i<name.length(); i++){
                int up = ((int)name.charAt(i)) - 'A';
                int down = (((int)name.charAt(i)) - 'Z') * -1 + 1;
                intString[i] = Math.min(up,down);
            }

            int answer = 0;
            int position = 0;
            while(true){
                answer += intString[position];
                intString[position] = 0;

                boolean finish = true;
                for(int i=0; i<intString.length; i++)
                    if(intString[i] != 0){
                        finish = false;
                        break;
                    }

                if(finish) break;

                int lPosition = position;
                int lMove=0;
                while(true){
                    lMove++;
                    lPosition--;
                    if(lPosition == -1)
                        lPosition = intString.length - 1;

                    if(intString[lPosition] != 0)
                        break;
                }

                int rPosition = position;
                int rMove=0;
                while(true){
                    rMove++;
                    rPosition++;
                    if(rPosition == intString.length)
                        rPosition = 0;

                    if(intString[rPosition] != 0)
                        break;
                }
                if(lMove < rMove){
                    answer += lMove;
                    position = lPosition;
                }else {
                    answer += rMove;
                    position = rPosition;
                }
            }

            return answer;
        }
    }
}
