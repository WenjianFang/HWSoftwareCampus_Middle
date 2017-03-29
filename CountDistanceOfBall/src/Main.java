/**
 * Created by Wenjian on 2017/3/29, 0029.
 */

import java.util.Scanner;

/**
 * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int initHeight = sc.nextInt();

        double distance = getTotalDistance(initHeight, 5);
        double finalHeight = getFinalHeight(initHeight, 5);
        System.out.println(distance);
        System.out.println(finalHeight);
    }

    /**
     *
     * @param initHeight
     * @param timesOfLanding
     * @return
     */
    private static double getTotalDistance(int initHeight, int timesOfLanding) {
        if(timesOfLanding <= 0) return 0;

        double totalDistance = initHeight;
        double currentHeight = initHeight;
        if(timesOfLanding <= 1) return totalDistance;

        for(int i = 1; i < timesOfLanding; i++) {
            currentHeight = currentHeight / 2;
            totalDistance += (currentHeight * 2);
        }
        return totalDistance;
    }

    /**
     *
     * @param initHeight
     * @param timesOfLanding
     * @return
     */
    private static double getFinalHeight(int initHeight, int timesOfLanding) {
        if(timesOfLanding <= 0) return initHeight;
        double currentHeight = initHeight;
        for(int i = 1; i < timesOfLanding; i++) {
            currentHeight = currentHeight / 2;
        }
        return currentHeight / 2;
    }
}
