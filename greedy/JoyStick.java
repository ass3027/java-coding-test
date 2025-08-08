package greedy;

public class JoyStick {
    public static void main(String[] args) {
        new Solution().solution("JEROEN");
    }
    static class Solution {
        public int solution(String name) {
            int len = name.length();
            int sum = 0;
            int min = len - 1;
            for (int i = 0; i < len; i++) {
                char current = name.charAt(i);
                int up = current - 'A';
                int down = 'Z' - current + 1;
                sum += Math.min(up, down);

                if (current == 'A') {
                    int beforeNotA = i;
                    while (beforeNotA > 0 && name.charAt(beforeNotA) == 'A')
                        beforeNotA--;

                    int nextNotA = i;
                    while (nextNotA < len && name.charAt(nextNotA) == 'A')
                        nextNotA++;

                    int returnCount = len - nextNotA;
                    int goAndReturn = beforeNotA * 2 + returnCount;
                    int returnAndGo = returnCount * 2 + beforeNotA;
                    min = Math.min(min, Math.min(goAndReturn, returnAndGo));
                }
            }
            //            "JAAAVA"
            //BBBBBBAAAABAAB
            return sum + min;
        }
    }
}
