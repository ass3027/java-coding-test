package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class javaTest {
    public static void main(String[] args){
        int[][] a = new int[][]{{1,2},{3,4}};
        int[] b = {1,2,3,3,4,4};
        String s = "ss";
        StringBuffer sb = new StringBuffer(s);
//        OptionalInt b = Arrays.stream(a).mapToInt(t-> t[1]).max().getAsInt();
        Arrays.sort(a,Comparator.comparing(it->it[0]));
        Arrays.stream(a).sorted(Comparator.comparing(it->it[0]));
        Arrays.stream(a).map(t-> t[1]).forEach(System.out::println);
        Arrays.stream(a).toArray();
        Arrays.stream(b).max().getAsInt();
        Arrays.stream(b).filter(i->i>7).count();
        int[] array = Arrays.stream(b).distinct().toArray();
        Set<Integer> collect = Arrays.stream(b).boxed().collect(Collectors.toSet());
        Integer[] collected = Arrays.stream(b).boxed().collect(Collectors.toSet()).toArray(new Integer[0]);
        Arrays.stream(b).boxed().collect(Collectors.toSet()).stream().mapToInt(t->t).toArray();
        Arrays.stream(collected).mapToInt(t->t).toArray();
        System.out.println();


//        Arrays.stream(a).max((i,j)->i[1]-j[1]);
    }
}
