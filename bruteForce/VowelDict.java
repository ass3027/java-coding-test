package bruteForce;

public class VowelDict {
    public static void main(String[] args) {
        // 6 10 1563 1189
        String[] td = {"AAAAE", "AAAE", "I", "EIO"};
        int result = new Solution().solution(td[3]);
        System.out.println(result);
//        System.out.println(new Solution().getCountByIndex(5, 0));
    }

    //완전탐색
    static class Solution {
        static final int MAX_NUMBER = 5;
        static final int MAX_LENGTH = 5;

        public int solution(String word) {
            int answer = 0;
            String number = toNumericString(word);
            StringBuffer increasingString = new StringBuffer();

            while(!number.contentEquals(increasingString)){
                if(increasingString.length() < MAX_LENGTH)
                    increasingString.append("1");
                else
                    increase(increasingString, 4);
                ++answer;
            }
            return answer;
        }

        private void increase(StringBuffer increasingString, int offset) {
            int lastNum = Character.getNumericValue(increasingString.charAt(offset));
            if(lastNum + 1 <= MAX_NUMBER)
                increasingString.setCharAt(offset, Character.forDigit(lastNum + 1,10));
            else{
                increasingString.deleteCharAt(offset);
                increase(increasingString,offset -1);
            }
        }

        String toNumericString(String word) {
            String result = "";
            for (int i = 0; i < word.length(); i++) {
                result += switch (word.charAt(i)) {
                    case 'A' -> '1';
                    case 'E' -> '2';
                    case 'I' -> '3';
                    case 'O' -> '4';
                    case 'U' -> '5';
                    default -> '0';
                };
            }
            return result;
        }
    }

    //5Radix
    static class Solution2 {
        static final int MAX_NUMBER = 5;
        static final int MAX_LENGTH = 5;

        public int solution(String word) {
            String numericString = toNumericString(word);
            int answer = 0;

            for (int i = 0; i < numericString.length(); i++) {
                int number = Character.getNumericValue(numericString.charAt(i));
                int countByEachIndex = (number - 1) * getCountByIndex(MAX_LENGTH - i, 0);
                answer += countByEachIndex + 1; // -1 까지의 개수를 계산하기때문 AA000 ~ AAUUU = 156
            }
            return answer + numericString.length();
        }

        String toNumericString(String word) {
            String result = "";
            for (int i = 0; i < word.length(); i++) {
                result += switch (word.charAt(i)) {
                    case 'A' -> '1';
                    case 'E' -> '2';
                    case 'I' -> '3';
                    case 'O' -> '4';
                    case 'U' -> '5';
                    default -> '0';
                };
            }
            return result;
        }

        int getCountByIndex(int index, int result) {
            result += (int) Math.pow(MAX_NUMBER, index - 1);
            index--;
            if (index > 0)
                return getCountByIndex(index, result);
            return result;
        }
    }
}
