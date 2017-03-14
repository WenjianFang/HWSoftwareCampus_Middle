/**
 * Created by Wenjian on 2017/3/14, 0014.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 明文子串，根据手机上规则1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0进行加密
 * 大写字母转小写字母后移一位
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> publicPasswordList = new ArrayList<>();
        List<String> encryptPasswordList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String newPassword = sc.nextLine();
            if(newPassword.equals("")) break;
            else publicPasswordList.add(newPassword);
        }
        encryptPassword(publicPasswordList, encryptPasswordList);
        for(int i = 0; i < encryptPasswordList.size(); i++) {
            System.out.println(encryptPasswordList.get(i));
        }
    }

    /**
     *
     * @param publicPasswordList
     * @param encryptPasswordList
     */
    private static void encryptPassword(List<String> publicPasswordList, List<String> encryptPasswordList) {
        for(int i = 0; i < publicPasswordList.size(); i++) {
            String publicPassword = publicPasswordList.get(i);
            String encryptPassword = encryptOnePassword(publicPassword);
            encryptPasswordList.add(encryptPassword);
        }
    }

    /**
     *
     * @param publicPassword
     * @return
     */
    private static String encryptOnePassword(String publicPassword) {
        StringBuilder encryptPassword = new StringBuilder();
        for(int i = 0; i < publicPassword.length(); i++) {
            char thisChar = publicPassword.charAt(i);
            if((thisChar >= 'A') && (thisChar < 'Z')) {
                encryptPassword.append((char)(thisChar - 'A' + 'a' + 1));
            }
            else if(thisChar == 'Z'){
                encryptPassword.append('a');
            }
            else if((thisChar >= 'a') && (thisChar <= 'c')) {
                encryptPassword.append('2');
            }
            else if((thisChar >= 'd') && (thisChar <= 'f')) {
                encryptPassword.append('3');
            }
            else if((thisChar >= 'g') && (thisChar <= 'i')) {
                encryptPassword.append('4');
            }
            else if((thisChar >= 'j') && (thisChar <= 'l')) {
                encryptPassword.append('5');
            }
            else if((thisChar >= 'm') && (thisChar <= 'o')) {
                encryptPassword.append('6');
            }
            else if((thisChar >= 'p') && (thisChar <= 's')) {
                encryptPassword.append('7');
            }
            else if((thisChar >= 't') && (thisChar <= 'v')) {
                encryptPassword.append('8');
            }
            else if((thisChar >= 'w') && (thisChar <= 'z')) {
                encryptPassword.append('9');
            }
            else {
                encryptPassword.append(thisChar);
            }
        }
        return encryptPassword.toString();
    }
}
