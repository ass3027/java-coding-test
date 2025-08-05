package nonAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;

public class RobotDelivery {
    public static void main(String[] args) {
        int res =
          new Solution().solution(new int[][]{{1, 1}, {2, 2}, {3, 3}} , new int[][]{{1, 2, 1}, {3, 2, 1}} );
        System.out.println(res);
    }

static class Solution {
    int[][] points,routes;
    ArrayList<int[]> currentLoc = new ArrayList<>();
    LinkedList<int[]>[] moving;
    ArrayList<int[]> locInfoList = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        this.routes = routes;
        return logic();
    }

    int logic(){
        for (int[] target : routes) {
            int[] currentPoint = points[target[0] - 1];
            addLocInfo(0, currentPoint);

            for (int j = 1, time = 1; j < target.length; j++) {
                int[] destination = points[target[j] - 1];
                int destinationKey = getKey(destination);
                while (getKey(currentPoint) != destinationKey) {
                    int dy = currentPoint[0] - destination[0];
                    int dx = currentPoint[1] - destination[1];

                    if (dy == 0 && dx == 0)
                        break;

                    if (dy != 0)
                        currentPoint[0] = currentPoint[0] + dy > 0 ? -1 : 1;
                    else
                        currentPoint[1] = currentPoint[1] + dx > 0 ? -1 : 1;

                    addLocInfo(time++, currentPoint);
                }
            }
        }
        int sum = 0;
        for(int[] loc : locInfoList)
            if(loc[1] > 1) sum++;

        return sum;
    }

    void addLocInfo(int time,int[] point){
        if(locInfoList.size() <= time)
            locInfoList.add(time,new int[2]);
        locInfoList.get(time)[getKey(point)]++;
    }

    int getKey(int[] p){
        return p[0] * 101 + p[1];
    }

//    int logic(){
//        initMovingInfo();
//        HashMap<Integer,Integer> currentStatus = new HashMap<>();
//        int sum = 0;
//        boolean end = false;
//        while(!end){
//            for (int[] loc : currentLoc) {
//                int key = loc[0] * 101 + loc[1];
//                if(key < 0) continue;
//                currentStatus.merge(key, 1, Integer::sum);
//            }
//
//            for(int v : currentStatus.values())
//                if(v > 1) sum++;
//
//            currentStatus.clear();
//
//            end = true;
//            for(int j=0; j<currentLoc.size(); j++){
//                int[] target = currentLoc.get(j);
//                if(moving[j].isEmpty()){
//                    target[0] = -1;
//                    target[1] = -1;
//                    continue;
//                }
//
//                int[] movingOne = moving[j].pop();
//                target[0] += movingOne[0];
//                target[1] += movingOne[1];
//                end = false;
//            }
//        }
//        return sum;
//    }
//
//
//    void initMovingInfo(){
//        moving = new LinkedList[routes.length];
//
//        for(int i=0; i<routes.length; i++){
//            moving[i] = new LinkedList<>();
//            int[] route = routes[i];
//            for(int j=0; j<routes[i].length-1; j++){
//                int[] from = points[route[j] -1];
//                int[] to = points[route[j+1] -1];
//                int dy = to[0] - from[0];
//                int dx = to[1] - from[1];
//                for(int a=0; a<Math.abs(dy); a++)
//                    moving[i].add(new int[]{dy/Math.abs(dy),0});
//                for(int a=0; a<Math.abs(dx); a++)
//                    moving[i].add(new int[]{0,dx/Math.abs(dx)});
//            }
//            currentLoc.add(points[route[0] - 1].clone());
//        }
//    }
}
}
