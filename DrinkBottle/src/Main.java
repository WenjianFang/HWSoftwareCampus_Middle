/**
 * Created by Wenjian on 2017/3/14, 0014.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 三个空瓶子换一瓶饮料，如果有2个可以借一个，然后喝完给老板三个空瓶子
 * Given the bottle number
 */
public class Main {
    static final int EMPTY_BOTTLE_TO_DRINK = 3;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> bottleList = new ArrayList<>();

        while(sc.hasNext()) {
            int bottleNum = sc.nextInt();
            if(bottleNum == 0) break;
            else bottleList.add(bottleNum);
        }

        List<Integer> drinkList = new ArrayList<>();
        calcAllDrinkNumber(bottleList, drinkList);
        for(int i = 0; i < drinkList.size(); i++) {
            System.out.println(drinkList.get(i));
        }
    }

    /**
     *
     * @param bottleList
     * @param drinkList
     */
    private static void calcAllDrinkNumber(List<Integer> bottleList, List<Integer> drinkList) {
        for(int i = 0; i < bottleList.size(); i++) {
            int thisBottleNum = bottleList.get(i);
            int thisDrinkNum = calcDrinkNumber(thisBottleNum);
            drinkList.add(thisDrinkNum);
        }
    }

    /**
     * 
     * @param bottleNum
     * @return
     */
    private static int calcDrinkNumber(int bottleNum) {
        /*less than 2, only have 1 or not one, no drink*/
        if(bottleNum < EMPTY_BOTTLE_TO_DRINK - 1) return 0;
        int drink = 0;

        while(bottleNum > EMPTY_BOTTLE_TO_DRINK - 1) {
            int thisTimeDrink = bottleNum / EMPTY_BOTTLE_TO_DRINK;
            bottleNum = bottleNum % EMPTY_BOTTLE_TO_DRINK + thisTimeDrink;
            drink = drink + thisTimeDrink;
        }
        if(bottleNum == EMPTY_BOTTLE_TO_DRINK - 1) {
            drink++;
        }
        return drink;
    }
}
