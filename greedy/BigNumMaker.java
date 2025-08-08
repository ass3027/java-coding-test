package greedy;

public class BigNumMaker {
    public static void main(String[] args) {
        new Solution().solution("4177252841",4);
    }
    static class Solution {
        public String solution(String number, int k) {
            StringBuilder answer = new StringBuilder(number);
            for(int i=0;i<answer.length() - 1;){
                if(answer.charAt(i) < answer.charAt(i+1)){
                    answer.deleteCharAt(i);
                    if(answer.length() == number.length() - k)
                        break;
                    continue;
                }
                i++;
            }

            while(answer.length() > number.length() - k)
                answer.deleteCharAt(answer.length()-1);

            return answer.toString();
        }
    }

    static class Solution2 {
        public String solution(String number, int k) {
            char[] answer = number.toCharArray();
            int count = 0;
            while(count != k){
                int i=0;
                for(;i<answer.length - 1;){
                    int nextValue = i + 1;
                    while(nextValue < answer.length && answer[nextValue] != Character.MIN_VALUE)
                        nextValue++;

                    if(nextValue == answer.length || answer[nextValue] == Character.MIN_VALUE)
                        break;

                    if(answer[i] != Character.MIN_VALUE && answer[i] < answer[nextValue])
                        break;

                    i = nextValue;
                }
                answer[i] = Character.MIN_VALUE;
                count++;
            }
            StringBuilder sb = new StringBuilder();
            for(char c : answer)
                if(c != Character.MIN_VALUE)
                    sb.append(c);

            return sb.toString();
        }
    }
}
