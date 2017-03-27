/**
 * Created by Wenjian on 2017/3/27, 0027.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 对字符串中的所有单词进行倒排。
 * 说明：
 * 1、每个单词是以26个大写或小写英文字母构成；
 * 2、非构成单词的字符均视为单词间隔符；
 * 3、要求倒排后的单词间隔符以一个空格表示；如果原字符串中相邻单词间有多个间隔符时，倒排转换后也只允许出现一个空格间隔符；
 * 4、每个单词最长20个字母；
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String reversedString = reverseString(inputString);
        System.out.println(reversedString);
        sc.close();
    }

    /**
     *
     * @param inputString
     * @return
     */
    private static String reverseString(String inputString) {

        StringBuilder reversedSb = new StringBuilder();
        List<String> strList = new ArrayList<>();
        boolean lastIsEnChar = false;

        /*get each word*/
        StringBuilder newSb = new StringBuilder();
        for(int i = 0; i < inputString.length(); i++) {

            if(((inputString.charAt(i) >= 'a')&&(inputString.charAt(i) <= 'z'))
                ||((inputString.charAt(i) >= 'A')&&(inputString.charAt(i) <= 'Z'))) {
                lastIsEnChar = true;
                newSb.append(inputString.charAt(i));
            }
            else {
                if(lastIsEnChar) {/*a new word*/
                    lastIsEnChar = false;
                    String newStr = newSb.toString();
                    strList.add(newStr);
                    newSb.delete(0, newSb.length());
                }
            }
        }

        if(newSb.length() > 0) {/*the last word*/
            String newStr = newSb.toString();
            strList.add(newStr);
        }

        /*from end->front*/
        if(strList.size() > 0) {
            for (int i = strList.size() - 1; i > 0; i--) {
                reversedSb.append(strList.get(i));
                reversedSb.append(" ");
            }
            reversedSb.append(strList.get(0));
        }

        return reversedSb.toString();
    }
}
