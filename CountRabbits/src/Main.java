/**
 * Created by Wenjian on 2017/3/29, 0029.
 */

import java.util.Scanner;

/**
 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，
 * 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int whichMonth = sc.nextInt();
        int totalRabbits = countTotalRabbitsInMonth(whichMonth);
        System.out.println(totalRabbits);
    }

    /**
     *
     * @param whichMonth
     * @return
     */
    private static int countTotalRabbitsInMonth(int whichMonth) {
        if(whichMonth <= 2) return 1;
        int rabbitCount1 = 1;
        int rabbitCount2 = 1;

        int tmp;
        for(int i = 2; i < whichMonth; i++) {
            tmp = rabbitCount2;
            rabbitCount2 = rabbitCount1 + rabbitCount2;
            rabbitCount1 = tmp;
        }
        return rabbitCount2;
    }
}
