/**
 * Created by Wenjian on 2017/3/16, 0016.
 */

import java.util.Scanner;

/**
 *
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *规则1：英文字母从A到Z排列，不区分大小写。
 * 如，输入：Type 输出：epTy
 * 规则2：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 如，输入：BabA 输出：aABb
 * 规则3：非英文字母的其它字符保持原来的位置。
 * 如，输入：By?e 输出：Be?y
 * 样例：
 * 输入：
 * A Famous Saying: Much Ado About Nothing(2012/8).
 * 输出：
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class Main {
    static final int ENGLISH_LETTER_NUM = 26;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();

        String sortedStr = sortByRules(inputStr);
        System.out.println(sortedStr);
    }

    /**
     * 找出每个字母出现的字符串，将这些字符串串成一串
     * 按顺序，将非字母字符插入，重新组成一串
     * @param inputStr
     * @return
     */
    private static String sortByRules(String inputStr) {
        /*数组，存放每个字母出现的字符串*/
        StringBuilder[] eachLetter = new StringBuilder[ENGLISH_LETTER_NUM + 1];
        for(int i = 0; i < eachLetter.length; i++) {
            eachLetter[i] = new StringBuilder();
        }

        /*对每个字符，放到对应数组位置的StringBuilder*/
        for(int i = 0; i < inputStr.length(); i++) {
            if((inputStr.charAt(i) >= 'a') && (inputStr.charAt(i) <= 'z')) {
                eachLetter[inputStr.charAt(i) - 'a'].append(inputStr.charAt(i));
            }
            else if((inputStr.charAt(i) >= 'A')&&(inputStr.charAt(i) <= 'Z')) {
                eachLetter[inputStr.charAt(i) - 'A'].append(inputStr.charAt(i));
            }
        }

        /*串成一串*/
        StringBuilder sortedCharacters = new StringBuilder();
        for(int i = 0; i < eachLetter.length; i++) {
            if(eachLetter[i].length() > 0) {
                sortedCharacters.append(eachLetter[i]);
            }
        }

        /*按顺序加上非字母字符，重新串一串*/
        StringBuilder sortedString = new StringBuilder();
        int indexOfSortedStr = 0;
        for(int i = 0; i < inputStr.length(); i++) {
            if(((inputStr.charAt(i) >= 'a') && (inputStr.charAt(i) <= 'z'))
                    ||((inputStr.charAt(i) >= 'A')&&(inputStr.charAt(i) <= 'Z'))) {
                sortedString.append(sortedCharacters.charAt(indexOfSortedStr));
                indexOfSortedStr++;
            }
            else {
                sortedString.append(inputStr.charAt(i));
            }
        }

        return sortedString.toString();
    }
}
