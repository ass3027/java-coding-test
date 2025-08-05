package hash;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneNumberList {
    public static void main(String[] args) {
        new Solution().solution(new String[]{});
    }
    static class Solution {
        public boolean solution(String[] phone_book) {
            Set<String> set = Arrays.stream(phone_book)
                    .collect(Collectors.toSet());

            StringBuilder sb = new StringBuilder();
            for(String pn : phone_book){
                char[] ca = pn.toCharArray();
                for(int i=0; i<ca.length - 1; i++ ){
                    sb.append(ca[i]);
                    if(set.contains(sb.toString()))
                        return false;
                }
                sb.setLength(0);
            }
            return true;
        }
    }
    static class Solution2 {
        public boolean solution(String[] phone_book) {
            boolean answer = true;
            HashMap<Integer, HashSet<String>> map = new HashMap<>();
            for(String phoneNum : phone_book){
                int key = getKey(phoneNum);
                if(!map.containsKey(key))
                    map.put(key,new HashSet<>());

                map.get(key).add(phoneNum);
            }
            for(String phoneNum : phone_book){
                int key = getKey(phoneNum);
                for(int i : map.keySet()){
                    if(i > key) continue;

                    for(String s : map.get(i)){
                        if(phoneNum.startsWith(s) && !phoneNum.equals(s))
                            return false;
                    }
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
