/**
 * Created by Wenjian on 2017/3/15, 0015.
 */

import java.util.Scanner;

/**
 * Delete the characters that appear least times
 */
public class Main {
    static final int ENGLISTH_CHAR_NUM = 26;
    static final int MAXIMUM_LENGTH = 20;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        String afterHandledStr = deleteLeastTimesChar(inputStr);
        System.out.println(afterHandledStr);
    }

    /**
     *
     * @param inputStr
     * @return
     */
    private static String deleteLeastTimesChar(String inputStr) {
        if(inputStr.length() > MAXIMUM_LENGTH) return inputStr;
        StringBuilder afterHandledStr = new StringBuilder();

        int[] record = new int[ENGLISTH_CHAR_NUM];
        for(int i = 0; i < inputStr.length(); i++) {
            if((inputStr.charAt(i) >= 'a') && (inputStr.charAt(i) <= 'z')) {
                record[inputStr.charAt(i) - 'a']++;
            }
            else {
                return inputStr;
            }
        }

        int minTimes = ENGLISTH_CHAR_NUM + 1;
        for(int i = 0; i < record.length; i++) {
            if(record[i] > 0 && record[i] < minTimes) {
                minTimes = record[i];
            }
        }

        for(int i = 0; i < inputStr.length(); i++) {
            if(record[inputStr.charAt(i) - 'a'] > minTimes) {
                afterHandledStr.append(inputStr.charAt(i));
            }
        }
        return afterHandledStr.toString();
    }
}
