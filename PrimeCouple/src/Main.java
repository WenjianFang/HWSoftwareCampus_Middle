/**
 * Created by Wenjian on 2017/3/20, 0020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 给定一些数，两两组合，求它们能组合出素数最多的组数
 * 如，2 5 6 13
 * 2+5=7
 * 6+13=19
 * 两组
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalNum = sc.nextInt();
        int[] nums = new int[totalNum];
        for(int i = 0; i < totalNum; i++) {
            nums[i] = sc.nextInt();
        }

        int maxCouple = primeCouple(nums);
        System.out.println(maxCouple);
        sc.close();
    }

    private static int primeCouple(int[] nums){
        /**
         * divide to 2 set, one is even, one is odd
         * using hungary algorithm to get its maximum matching
         *
         */

        List<Integer> oddNums = new ArrayList<>();
        List<Integer> evenNums = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(!isEven(nums[i])) oddNums.add(nums[i]);
            else evenNums.add(nums[i]);
        }

        boolean[][] graph = new boolean[oddNums.size()][evenNums.size()];

        for(int i = 0; i < oddNums.size(); i++) {
            for(int j = 0; j < evenNums.size(); j++) {
                if(isPrime(oddNums.get(i) + evenNums.get(j))) graph[i][j] = true;
            }
        }
        return hungary(graph);

    }

    private static int hungary(boolean[][] graph) {
        //System.out.println(graph.length);
        //System.out.println(graph[0].length);
        int maxMatching = 0;
        int[] couple = new int[graph[0].length];
        for(int i = 0; i < graph[0].length; i++) couple[i] = -1;
        boolean[] matchingState = new boolean[graph[0].length];
        for(int i = 0; i < graph[0].length; i++) matchingState[i] = false;

        for (int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[0].length; j++) matchingState[j] = false;
            if (isMatched(graph, i, couple, matchingState)) {
                //System.out.println(Arrays.toString(couple));
                //System.out.println(Arrays.toString(matchingState));
                maxMatching++;
            }
        }
        return maxMatching;
    }

    private static boolean isMatched(boolean[][] graph, int whichOdd, int[] couple, boolean[] matchingState) {
        for (int i = 0; i < graph[0].length; i++){
            if ((graph[whichOdd][i]) && (!matchingState[i])) {
                matchingState[i] = true;
                if ((-1 == couple[i]) || (isMatched(graph, couple[i], couple, matchingState))) {
                    couple[i] = whichOdd;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Check if the number is a prime or not
     * @param num
     * @return, false: NOT a prime; true: A prime
     */
    public static boolean isPrime(int num){
        if(num <= 1) return false; /*less or equal 1 is not prime*/
        for(int i = 2; i <= Math.sqrt(num); i++){ /*start from 2 and end at sqrt(num)*/
            if(num % i == 0) return false; /*have factor except 1 and itself*/
        }
        return true;
    }

    /**
     * check if the number is an even number or not
     */
    public static boolean isEven(int num){
        if(0 == (num % 2)) return true;
        else return false;
    }
}
