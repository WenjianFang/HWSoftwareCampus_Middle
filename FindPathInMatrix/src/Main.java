import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Wenjian on 2017/3/31, 0031.
 */
public class Main {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if((this.x == ((Point)obj).x) && (this.y == ((Point)obj).y)) {
                return true;
            }
            else return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int column = sc.nextInt();
        int row = sc.nextInt();

        int[][] maze = new int[column][row];

        for(int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        List<Point> path = new ArrayList<>();
        if(getPath(maze, path)) {
            for(int i = 0; i < path.size(); i++) {
                System.out.println("(" + path.get(i).x + "," + path.get(i).y + ")");
            }
        }
    }

    /**
     *
     * @param maze
     * @param path
     * @return
     */
    private static boolean getPath(int[][] maze, List<Point> path) {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(maze.length-1, maze[0].length-1);
        /*recursively solve*/
        if(getPathRecu(startPoint, endPoint, maze, path)){
            path.add(endPoint);
            return true;
        }
        return false;
    }

    /**
     *
     * @param startPoint
     * @param endPoint
     * @param maze
     * @param path
     * @return
     */
    private static boolean getPathRecu(Point startPoint, Point endPoint, int[][] maze, List<Point> path) {
        if(startPoint.equals(endPoint)) return true;

        path.add(startPoint);
        if((startPoint.x < endPoint.x) && (startPoint.y < endPoint.y)) {
            if (maze[startPoint.x][startPoint.y + 1] == 0){
                if(getPathRecu(new Point(startPoint.x, startPoint.y + 1), endPoint, maze, path)) return true;
                else return false;
            }
            else if(maze[startPoint.x + 1][startPoint.y] == 0){
                if(getPathRecu(new Point(startPoint.x + 1, startPoint.y), endPoint, maze, path)) return true;
                else return false;
            }
            else return false;
        }
        else if(startPoint.x == endPoint.x) {
            if(getPathRecu(new Point(startPoint.x, startPoint.y + 1), endPoint, maze, path)) return true;
            else return false;
        }
        else if(startPoint.y == endPoint.y) {
            if(getPathRecu(new Point(startPoint.x + 1, startPoint.y), endPoint, maze, path)) return true;
            else return false;
        }
        else{
            /*not path, back*/
            path.remove(startPoint);
            return false;
        }
    }
}
