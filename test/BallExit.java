package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BallExit {
    public static void main(String[] args){
        new BallExit2().run();
    }
}

class BallExit2 {
    int x;
    int y;
    String[][] boardArray;
    BufferedReader br;
    Ball blueBall;
    Ball redBall;
    Coor exit;
    int minMove = -1;

    public void run(){
        readInput();
        calculateAnswer();
        // System.out.println(minMove);
        System.out.println(minMove > 10 ? -1 : minMove);
    }

    void readInput(){
        br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        readFirstLine();
        readRemainLine();
    }

    void readFirstLine(){
    
        String[] firstLine = null;
        try{
            firstLine = br.readLine().split(" ");
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        y = Integer.parseInt(firstLine[0]);
        x = Integer.parseInt(firstLine[1]);
        boardArray = new String[y][x];
        // System.out.println(boardArray[0][0]);
        // System.out.println(x);
        // System.out.println(firstLine[1]);
    }

    void readRemainLine(){
        String[] currentLine = null;
        int i=0;
        int j=0;
        try{
            for(; i<y; i++){
                currentLine = br.readLine().split("");
                for(j=0; j<x; j++){
                    switch(currentLine[j]){
                        case "R":  redBall = new Ball(j,i,boardArray); break;
                        case "B":  blueBall = new Ball(j,i,boardArray); break;
                        case "O":  exit = new Coor(j,i); break;
                    }
                    boardArray[i][j] = currentLine[j];
                    // System.out.print(boardArray[i][j]);
                }
                // System.out.println("");
            }
            boardArray[redBall.coor.y][redBall.coor.x] = ".";
            boardArray[blueBall.coor.y][blueBall.coor.x] = ".";
        }catch(Exception e){
            // System.out.println("x and y : " + x + ", " + y);
            // System.out.println("i and j : " + i + ", " + j);
            // e.printStackTrace();
            return;
            // throw e;
        }
        // System.out.println("blue ball :" + blueBall.coor.x + "," + blueBall.coor.y);
        // System.out.println("red ball :" + redBall.coor.x + "," + redBall.coor.y);
        // System.out.println("exit :" + exit.x + "," + exit.y);
        // System.out.println();
    }

    void calculateAnswer(){
        dpsTest(0,null,new ArrayList<Coor>(),null);
    }

    void dpsTest(int moveCount,Direction excludedDirection,ArrayList<Coor> prevCoorListRed,Coor prevCoorBlue){
        // Direction[] posibleDirection = getPosibleDirection(prevDirection)
        //zz 체스판 갱신안됨;;
        System.out.println("dpsTest");
        // if(excludedDirection != null)
            // System.out.println("excludedDirection :" + excludedDirection.name());
        moveCount++;
        if(moveCount > 10) return;
        prevCoorListRed.add(redBall.coor.copy());
        Coor currentRedBallCoor = redBall.coor.copy();
        Coor currentBlueBallCoor = blueBall.coor.copy();

        //나중에 옆에 #있으면 excludedDirection에 추가해버리자 posibleDirection
        for(Direction direction : Direction.values()){
            if(direction == excludedDirection)
                continue;

            tilt(direction);
            //제자리 or 파란공 나감
            if(
//                prevCoorListRed.contains(redBall.coor)
                    ( prevCoorListRed.get(prevCoorListRed.size()-1).equals(redBall.coor) ) && blueBall.coor.equals(currentBlueBallCoor)
                            || blueBall.isOut()){
                 System.out.println(prevCoorListRed.contains(redBall.coor) ? "제자리" : "blue out");
                // System.out.print("prevCoorListRed :");
                // for(Coor c: prevCoorListRed){
                //     System.out.print(c.x + " " + c.y + ", ");
                // }
                // System.out.println();
                // System.out.println("prevCoorBlue : " + prevCoorBlue.x + " " + prevCoorBlue.y);
                redBall.coor = currentRedBallCoor.copy();
                blueBall.coor = currentBlueBallCoor.copy();
                continue;
            }
            // drawChessBoard();
            System.out.println("direction :" + direction.name());
             System.out.print("prevCoorListRed :");
             for(Coor c: prevCoorListRed){
                 System.out.print(c.x + " " + c.y + ", ");
             }
             System.out.println();
            System.out.println("red :" + redBall.coor.x + " " + redBall.coor.y);
            System.out.println("blue :" + blueBall.coor.x + " " + blueBall.coor.y);
            System.out.println("moveCount :" + moveCount);
            
            //빨간공만 나감
            if(redBall.isOut()){
                System.out.println("isOut, moveCount :" + moveCount);
                minMove = minMove == -1 ?
                        moveCount :
                        moveCount < minMove ?
                                moveCount : minMove;
                redBall.coor = currentRedBallCoor.copy();
                blueBall.coor = currentBlueBallCoor.copy();
                // System.out.println(redBall.coor.x + " " + redBall.coor.y);
                // continue;
                break;
            }
            
            dpsTest(moveCount,direction.getReverse(),prevCoorListRed,currentBlueBallCoor.copy());
        }
        System.out.println("dpsTest Out");

        //다돌았다는뜻
        if(prevCoorListRed.size() -1 == 0 || prevCoorBlue == null)
            return;
        
        //마지막 없애기
        prevCoorListRed.remove(prevCoorListRed.size()-1);
        redBall.coor = prevCoorListRed.get(prevCoorListRed.size() - 1).copy();
        // try{
            
        // }catch(Exception e){
        //     System.out.println("prevCoorListRed.size() at Error :" + prevCoorListRed.size());
        //     throw e;
        // }
        blueBall.coor = prevCoorBlue.copy();
    }

    void tilt(Direction direction){
        //TODO 공이 구멍으로 빠졌을때
        //그리고 빨강 파랑이 같은 선상에 있을때
        int axis = direction.getAxis();
        int value = direction.getValue();
        // which one move first
//        System.out.println("red :" + redBall.coor.x + " " + redBall.coor.y);
//        System.out.println("blue :" + blueBall.coor.x + " " + blueBall.coor.y);
//        System.out.println("direction :" + direction.name());
         if(redBall.getCoorByAxis(axis) * value> blueBall.getCoorByAxis(axis) * value){
             redBall.move(direction,blueBall);
             blueBall.move(direction,redBall);
         }else {
             blueBall.move(direction,redBall);
             redBall.move(direction,blueBall);
         }
    }
}
class Ball {
    
    Coor coor;
    String[][] boardArray;

    Ball(int x, int y, String[][] boardArray){
        this.coor = new Coor(x,y);
        this.boardArray = boardArray;
    }

    int getCoorByAxis(int axis){
        return axis == 1 ? this.coor.x : this.coor.y;
    }

    boolean isOut(){
        return this.coor.x == -1; 
    }

    void move(Direction direction, Ball otherBall){
        // move last . or O
        // directionSetting
        //와 이거 int처리 어떻게 몬하나?
        // eraseSelfInChessBoard();
        String movePoint;

        while(true){
            this.coor.moveByDirection(direction);
            try{
                movePoint = boardArray[this.coor.y][this.coor.x];
            }catch(Exception e){
                System.out.println(direction.getAxis() + " " + direction.getValue() + " " + this.coor.x + " " + this.coor.y);
                e.printStackTrace();
                throw e;
            }
            
//            if(!movePoint.equals(".")){
//                //나가면 x좌표를 -1
//                if(movePoint.equals("O")){
//                    this.coor.x = -1;
//                    return;
//                    // this.coor.y = -1;
//                }else {
//                    this.coor.moveByDirection(direction.getReverse());
//                }
//                break;
//            }
            if(!movePoint.equals(".")){
                //나가면 x좌표를 -1
                if(movePoint.equals("O")){
                    this.coor.x = -1;
                    return;
                }else {
                    this.coor.moveByDirection(direction.getReverse());
                }
                break;
            }else if(this.coor.equals(otherBall.coor)){
                this.coor.moveByDirection(direction.getReverse());
                break;
            }
        }
        // redrawInChessBoard();
    }
    void eraseSelfInChessBoard(){
        this.boardArray[this.coor.y][this.coor.x] = ".";
    }
    void redrawInChessBoard(){
        this.boardArray[this.coor.y][this.coor.x] = "R";
    }
}
class Coor {
    int x;
    int y;

    public Coor(int x, int y){
        this.x = x;
        this.y = y;
    }

    void moveByDirection(Direction direction){
        if(direction.getAxis() == 1){
            this.x += direction.getValue();
        } else {
            this.y += direction.getValue();
        }
    }

    Coor copy(){
        return new Coor(this.x,this.y);
    }

    public boolean equals(Coor coor){
        return this.x == coor.x && this.y == coor.y;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Coor) && equals((Coor)o);
    }
}

enum Direction {
    UP(0,-1),
    DOWN(0,1),
    LEFT(1,-1),
    RIGHT(1,1);

    private final int axis;
    private final int value;
    Direction(int axis, int value) { 
        this.axis = axis;
        this.value = value;
    }
    public int getValue() { return this.value;}
    public int getAxis() { return this.axis;}
    public Direction getReverse(){
        for(Direction d : Direction.values()){
            if(this.axis == d.axis 
            && this.value * -1 == d.value){
                return d;
            }
        }
        System.out.print(this.axis + " " + this.value);
        return null;
    }
}