/**
 * Created by Wenjian on 2017/3/1, 0001.
 */

import java.util.Scanner;

/**
 * 给定物品的价格，重要度，主件还是附件，以及一定的钱
 * 求出价值*重要度最大的清单
 * 要求：买了附件，必须买主件
 * classical 0-1 knapsack problem
 * using dynamic programming
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalMoney = sc.nextInt();
        int stuffNum = sc.nextInt();
        Commodity[] commodities = new Commodity[stuffNum + 1];
        for(int i = 1; i <= stuffNum; i++) {
            commodities[i] = new Commodity();
            commodities[i].value = sc.nextInt();
            commodities[i].importance = sc.nextInt();
            commodities[i].masterNo = sc.nextInt();
            //commodities[i].buy = false;
        }

        int maxValue = getMaxValue(totalMoney, commodities);
        System.out.println(maxValue);
    }

    /**
     *
     * @param totalMoney
     * @param commodities
     * @return
     */
    private static int getMaxValue(int totalMoney, Commodity[] commodities) {
        //int remainMoney = totalMoney;
        /*value[i][j], 用j这么多钱买前i件物品能获得的最大价值*/
        int[][] value = new int[commodities.length][totalMoney + 1];
        boolean[][] buy = new boolean[commodities.length][totalMoney + 1];
        for(int i = 0; i < commodities.length; i++) value[i][0] = 0;
        for(int j = 0; j < totalMoney + 1; j++) value[0][j] = 0;
        for(int i = 0; i < commodities.length; i++) {
            for (int j = 0; j < totalMoney + 1; j++) {
                buy[i][j] = false;
            }
        }
        for(int i = 1; i < commodities.length; i++) {
            for(int j = 0; j < totalMoney + 1; j++) {
                /*it is master*/
                if(commodities[i].masterNo == 0) {
                    value[i][j] = value[i - 1][j];
                    if((j >= commodities[i].value) && (value[i][j] < value[i-1][j-commodities[i].value]+commodities[i].value*commodities[i].importance)) {
                        value[i][j] = value[i-1][j-commodities[i].value]+commodities[i].value*commodities[i].importance;
                        buy[i][j] = true;
                        //commodities[i].buy = true;
                    }
                }
                else {
                    value[i][j] = value[i - 1][j];
                    if((j >= commodities[i].value) && (value[i][j] < value[i-1][j-commodities[i].value]+commodities[i].value*commodities[i].importance)) {
                        if(buy[i][j]) {
                        //if(commodities[i].buy){
                            value[i][j] = value[i-1][j-commodities[i].value]+commodities[i].value*commodities[i].importance;
                        }
                    }
                }
            }
        }
        return value[commodities.length-1][totalMoney];
    }

    static class Commodity {
        int value;
        int importance;
        int masterNo;
        //boolean buy;
    }
}
