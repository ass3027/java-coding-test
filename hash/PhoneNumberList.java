package hash;

import java.util.*;
import java.util.stream.Collectors;


// hash / sort
public class PhoneNumberList {
    public static void main(String[] args) {
        new Solution().solution(new String[]{});
        new Solution2().solution(new String[]{});
        new Solution3().solution(new String[]{});
    }

    static class Solution {
        public boolean solution(String[] phone_book) {
            Set<String> set = Arrays.stream(phone_book).collect(Collectors.toSet());

            StringBuilder sb = new StringBuilder();
            for(String pn : phone_book){
                char[] ca = pn.toCharArray();
                for(int i=0; i<ca.length - 1; i++ )
                    if(set.contains(sb.append(ca[i]).toString()))
                        return false;
                sb.setLength(0);
            }
            return true;
        }
    }

    static class Solution3 {
        public boolean solution(String[] phone_book) {
            Arrays.sort(phone_book);
            for (int i=0; i<phone_book.length-1; i++)
                if (phone_book[i+1].startsWith(phone_book[i]))
                    return false;
            return true;
        }
    }

    static class Solution2 {
        public boolean solution(String[] phone_book) {
            Arrays.sort(phone_book);
            HashMap<Integer, HashSet<String>> map = new HashMap<>();
            for(String phoneNum : phone_book)
                map.computeIfAbsent(getKey(phoneNum), k -> new HashSet<>())
                        .add(phoneNum);

            for(String phoneNum : phone_book){
                int key = getKey(phoneNum);
                for(int i : map.keySet()){
                    if(i > key) continue;

                    for(String s : map.get(i))
                        if(phoneNum.startsWith(s) && !phoneNum.equals(s))
                            return false;
                }
            }
            return true;
        }

        int getKey(String s){
            int key = 0;
            for(char c : s.toCharArray())
                key += Character.getNumericValue(c);

            return key;
        }
    }
}
