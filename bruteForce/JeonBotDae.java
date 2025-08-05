package bruteForce;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class JeonBotDae {

    public static void main(String[] args) {
        int[][] a = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        new JeonBotDae().solution(9, a);
    }

    // 선의 개수 = n-1
    // 결국 1개의 노드가 끊어진다
    int n;
    int[][] wires;
//    List<Set<Integer>> linkedInfo;
    List<Set<Integer>> linkedInfo;
    int skipNodeNumber;

    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;

        initConnectionInfo();

//        int min = n;
        int min = n-1;

        //1을 때면 무조건 n-1? 내가 그린 트리에서는 그런데? 이게 맞남?
        for(int i=2; i<n+1; i++){
            this.skipNodeNumber = i;
            int child = count(1,0);
            min = Math.min(min, Math.abs(child - (n - child)));
        }

        System.out.println(min);
        return min;
    }

    private void initConnectionInfo(){
        this.linkedInfo = new ArrayList<>();
        for(int i=0; i<n+1; i++){
            linkedInfo.add(new HashSet<>());
        }
//        this.linkedInfo = IntStream.range(0, n+1)
//                .mapToObj(HashSet<Integer>::new)
//                .collect(Collectors.toList());


        for(int[] wire : wires){
            linkedInfo.get(wire[0]).add(wire[1]);
            linkedInfo.get(wire[1]).add(wire[0]);
        }
    }

    private int count(int start, int before){
        int cnt = 1;

        for (int child : linkedInfo.get(start)) {
            if (child == before || child == this.skipNodeNumber)
                continue;
            cnt += count(child, start);
        }
        return cnt;

    }
}
