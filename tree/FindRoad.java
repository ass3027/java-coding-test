package tree;

import java.util.Arrays;
import java.util.Comparator;

public class FindRoad {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});
    }
    static class Solution {
        public int[][] solution(int[][] nodeinfo) {
            int[][] answer = new int[2][nodeinfo.length];
            int[][] nodeInfo2 = new int[nodeinfo.length][3];

            for(int i=0;i < nodeinfo.length;i++){
                nodeInfo2[i] = new int[] {nodeinfo[i][0],nodeinfo[i][1],i+1};
            }

            Arrays.sort(nodeInfo2,Comparator.comparing(it->it[1] * -1));

            Node topNode = new Node(nodeInfo2[0]);
            int[][] nodeInfo3 = Arrays.copyOfRange(nodeInfo2, 1, nodeInfo2.length);
            Arrays.stream(nodeInfo3).forEach(it -> {
                if(it[1] == topNode.y) return;
                Node curNode = new Node(it);
                Node targetNode = topNode;

                while(true){
                    int i = curNode.x < targetNode.x ? 0 : 1;
                    if(targetNode.childrenNode[i] == null){
                        targetNode.childrenNode[i] = curNode;
                        break;
                    }else {
                        targetNode = targetNode.childrenNode[i];
                    }
                }
            });

//        Node targetNode;
//        Stack<Node> stack = new Stack<>();
//        Stack<Node> stack2 = new Stack<>();
//        int j=0;
//        stack.push(topNode);
//        while(!stack.isEmpty()){
//            targetNode = stack.pop();
//            answer[0][j++] = targetNode.num;
//            if(targetNode.childrenNode[1] != null){
//                stack.push(targetNode.childrenNode[1]);
//            }
//            if(targetNode.childrenNode[0] != null){
//                stack.push(targetNode.childrenNode[0]);
//            }
//        }
//        j=0;
//        stack.push(topNode);
//        while(!stack.isEmpty()){
//            targetNode = stack.pop();
//            stack2.push(targetNode);
//            if(targetNode.childrenNode[0] != null){
//                stack.push(targetNode.childrenNode[0]);
//            }
//            if(targetNode.childrenNode[1] != null){
//                stack.push(targetNode.childrenNode[1]);
//            }
//        }
//        while(!stack2.isEmpty()){
//            targetNode = stack2.pop();
//            answer[1][j++] = targetNode.num;
//        }
            answer[0] = preorderIter(topNode,new int[nodeinfo.length]);
            j=0;
            answer[1] = postorderIter(topNode,new int[nodeinfo.length]);
            return answer;
        }
        int j=0;
        int[] preorderIter(Node node,int[] result){
            if(node == null) return null;
            result[j++] = node.num;
            preorderIter(node.childrenNode[0],result);
            preorderIter(node.childrenNode[1],result);
            return result;
        }

        int[] postorderIter(Node node,int[] result){
            if(node == null) return null;
            postorderIter(node.childrenNode[0],result);
            postorderIter(node.childrenNode[1],result);
            result[j++] = node.num;
            return result;
        }

        static class Node{
            Node[] childrenNode;
            int x,y,num;

            Node(int[] info){
                this.childrenNode = new Node[2];
                this.x = info[0];
                this.y = info[1];
                this.num = info[2];
            }

        }
    }
}
