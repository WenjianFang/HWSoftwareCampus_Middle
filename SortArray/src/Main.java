/**
 * Created by Wenjian on 2017/3/20, 0020.
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * sort the array, 0 is ascend, and 1 is descend
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalNum = sc.nextInt();
        if(totalNum <= 0) return;
        int[] array = new int[totalNum];
        for(int i = 0; i < totalNum; i++) {
            array[i] = sc.nextInt();
        }

        int sortFlag = sc.nextInt();

        int[] sortedArray = sortArray(array, sortFlag);

        for(int i = 0; i < sortedArray.length - 1; i++) {
            System.out.print(sortedArray[i] + " ");
        }
        System.out.println(sortedArray[sortedArray.length - 1]);
    }

    /**
     *
     * @param array
     * @param sortFlag
     * @return
     */
    private static int[] sortArray(int[] array, int sortFlag){
        int[] sortedArray = new int[array.length];

        Arrays.sort(array);

        if(0 == sortFlag) {
            for(int i = 0; i < sortedArray.length; i++) {
                sortedArray[i] = array[i];
            }
        }
        else {
            int j = 0;
            for(int i = sortedArray.length - 1; i >= 0; i--) {
                sortedArray[j] = array[i];
                j++;
            }
        }
        return sortedArray;
    }
}
