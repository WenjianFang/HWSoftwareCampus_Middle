/**
 * Created by Wenjian on 2017/3/15, 0015.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 判断是否能一笔完成所有点的连接
 */
public class Main {
    static class Line{
        int start;
        int end;
        Line(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lineNumber = sc.nextInt();
        List<Line> lineList = new ArrayList<>();
        int nodeNumber = -1;
        for(int i = 0; i < lineNumber; i++) {
            /*point id start from 1, but the index start from 0*/
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            if(start > nodeNumber) nodeNumber = start;
            if(end > nodeNumber) nodeNumber = end;
            Line newLine = new Line(start, end);
            lineList.add(newLine);
        }

        int[][] Graph = new int[nodeNumber + 1][nodeNumber + 1];
        constructGraph(lineList, Graph);

        List<Integer> path = new ArrayList<>();

        if(getOneTimePath(Graph, path)) {
            System.out.println("true");
            //System.out.println(path.toString());
        }
        else {
            System.out.println("false");
        }
    }

    private static void constructGraph(List<Line> lineList, int[][] Graph) {
        for(int i = 0; i < lineList.size(); i++) {
            Graph[lineList.get(i).start][lineList.get(i).end] = 1;
            Graph[lineList.get(i).end][lineList.get(i).start] = 1;
        }
    }

    private static boolean getOneTimePath(int[][] Graph, List<Integer> path) {
        for(int i = 0; i < Graph.length; i++) {
            List<Integer> thisPath = new ArrayList<>();
            thisPath.add(i);
            int[][] isVisited = new int[Graph.length][Graph.length];
            if(getPathRecu(Graph, i, thisPath, isVisited)) {
                path.addAll(thisPath);
                return true;
            }
        }
        return false;
    }
    private static boolean getPathRecu(int[][] Graph, int start, List<Integer> thisPath, int[][] isVisited) {
        if(allVisited(isVisited, Graph)) return true;

        for(int i = 0; i < Graph.length; i++) {
            if((isVisited[start][i] == 0) && (Graph[start][i] == 1)) {/*have not been visited*/
                isVisited[start][i] = 1;
                isVisited[i][start] = 1;
                thisPath.add(i);
                if(allVisited(isVisited, Graph)) return true;
                if(!getPathRecu(Graph, i, thisPath, isVisited)) {
                    isVisited[start][i] = 0;
                    isVisited[i][start] = 0;
                    thisPath.remove(thisPath.size() - 1);
                }
                else return true;
            }
        }
        return false;
    }
    private static boolean allVisited(int[][] isVisited, int[][] Graph) {
        for(int i = 0; i < isVisited.length; i++) {
            for(int j = 0; j < isVisited[0].length; j++) {
                if((isVisited[i][j] == 0) && (Graph[i][j] == 1)) return false;
            }
        }
        return true;
    }
}
