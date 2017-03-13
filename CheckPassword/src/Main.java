/**
 * Created by Wenjian on 2017/3/13, 0013.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 检查密码的合法性
 * 长度超过8
 * 包括大写字母，小写字母，数字，其他符号，4种至少3种
 * 不能有长度超过2的子串重复
 */
public class Main {
    static final int MIN_PASSWORD_LENGTH = 8;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> checkResult = new ArrayList<>();
        while(sc.hasNextLine()) {
            String password = sc.nextLine();
            if(password.equals("")) break;
            boolean isLegal = checkPasswordLegal(password);
            //if(isLegal) System.out.println("OK");
            //else System.out.println("NG");
            if(isLegal) checkResult.add("OK");
            else checkResult.add("NG");
        }
        for(int i = 0; i < checkResult.size(); i++) {
            System.out.println(checkResult.get(i));
        }
    }

    /**
     *
     * @param password
     * @return
     */
    private static boolean checkPasswordLegal(String password) {
        if(password.length() < MIN_PASSWORD_LENGTH) return false;

        int countUpper = 0;
        int countLower = 0;
        int countNumber = 0;
        int countOther = 0;
        for(int i = 0; i < password.length(); i++) {
            if((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z')) {
                countUpper = 1;
            }
            else if((password.charAt(i) >= 'a') && (password.charAt(i) <= 'z')) {
                countLower = 1;
            }
            else if((password.charAt(i) >= '0') && (password.charAt(i) <= '9')) {
                countNumber = 1;
            }
            else {
                countOther = 1;
            }
        }
        if(countUpper + countLower + countNumber + countOther < 3) return false;

        for(int i = 0; i < password.length(); i++) {
            for(int j = 3; j < password.length(); j++) {
                if(i + j < password.length()) {
                    String str = password.substring(i, i+j);
                    if(password.substring(i + 1).contains(password.substring(i, i + j))) return false;
                }
            }
        }
        return true;
    }
}
