/**
 * Created by Wenjian on 2017/3/27, 0027.
 */

import java.util.Scanner;

/**
 * Given a string, output its longest symmetrical substring
 * longest common substring of original string and its reverse string
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String originalCode = sc.nextLine();
        int validLength = getValidLength(originalCode);
        System.out.println(validLength);
    }

    private static int getValidLength(String originalCode) {
        String reversedCode = new StringBuilder(originalCode).reverse().toString();

        return getLCSLength(originalCode, reversedCode);
    }

    /**
     *
     * @param firstString
     * @param secondString
     * @return
     */
    private static int getLCSLength(String firstString, String secondString){
        /** Dynamic Programming
         * use length[i][j] to record the LCS's length of firstString[0->i] and secondString[0->j]
         * If firstString[i]==secondString[j], length[i][j] = length[i - 1][j - 1] + 1
         * else length[i][j] = 0;
         * we can use a maxLength variable to record the max length during the dynamic programming process
         */
        /*as the requirement, change all the characters to lower case*/
        firstString = firstString.toLowerCase();
        secondString = secondString.toLowerCase();
        /*matrix to record the length lcs*/
        int[][] length = new int[firstString.length()][secondString.length()];
        int maxLength = 0;
        /*initialization of length*/
        for(int i = 0; i < firstString.length(); i++) length[i][0] = 0;
        for(int i = 0; i < secondString.length(); i++) length[0][i] = 0;

        for(int i = 0; i < firstString.length(); i++){
            for(int j = 0; j < secondString.length(); j++){
                /*first string and second string have the same character*/
                if(firstString.charAt(i) == secondString.charAt(j)){
                    /*this is the first character of one string, the length is initialized to 1*/
                    if(i <= 0 || j <= 0) length[i][j] = 1;
                    /*else it is the length[i - 1][j - 1] plus this same character*/
                    else length[i][j] = length[i - 1][j - 1] + 1;
                }
                /*else the consecutive string is broken, the length should be set to 0*/
                else length[i][j] = 0;
                /*record the max length*/
                if(length[i][j] > maxLength) maxLength = length[i][j];
            }
        }
        return maxLength;
    }
}
