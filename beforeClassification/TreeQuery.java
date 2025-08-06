package beforeClassification;

import java.io.*;
import java.util.*;

public class TreeQuery {

static class Main {
    int N,R,qNum;
    int[] query;
    Node[] nodeInfo;
    ArrayList<Integer>[] wires;

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        readInput();
        Object[] result = logic();
        writeOutput(result);
    }

    private Object[] logic() {
        makeTree(R,0);
        countSubTree(R,0);
        return Arrays.stream(query).mapToObj(it -> nodeInfo[it].subTreeSize).toArray();
    }

    private void countSubTree(int current, int parent) {
        int count = 1;
        for(int child : nodeInfo[current].children){
            if(child == parent) continue;
            countSubTree(child,current);
            count += nodeInfo[child].subTreeSize;
        }
        nodeInfo[current].subTreeSize = count;
    }

    private void makeTree(int current, int parent) {
        for(int child : wires[current]){
            if(child == parent)
                continue;

            nodeInfo[current].children.add(child);
            makeTree(child, current);
        }
    }

    private void writeOutput(Object[] result) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Object i : result)
            bw.write(i + "\n");
        bw.flush();
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        qNum = Integer.parseInt(st.nextToken());

        nodeInfo = new Node[N+1];
        wires = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            nodeInfo[i] = new Node();
            wires[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[a].add(b);
            wires[b].add(a);
        }

        query = new int[qNum];
        for (int i=0;i<qNum;i++){
            query[i] = Integer.parseInt(br.readLine());
        }
    }

    static class Node{
        List<Integer> children = new ArrayList<>();
        int subTreeSize;
    }
}
}
