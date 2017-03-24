import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Wenjian on 2017/3/24, 0024.
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstString = sc.next();
        String secondString = sc.next();

        String mergedString = mergeString(firstString, secondString);

        String sortedString = sortString(mergedString);

        String transferedString = transferString(sortedString);

        System.out.println(transferedString);
    }

    private static String mergeString(String firstString, String secondString) {
        return firstString + secondString;
    }

    /**
     * sort ch in even index and odd index respectively.
     * @param string
     * @return
     */
    private static String sortString(String string) {
        char[] evenIndexChar = new char[(string.length() + 1) / 2];
        char[] oddIndexChar = new char[string.length() / 2];

        int evenIndex = 0;
        int oddIndex = 0;
        for(int i = 0; i < string.length(); i++) {
            if((i % 2) == 0) {
                evenIndexChar[evenIndex] = string.charAt(i);
                evenIndex++;
            }
            else {
                oddIndexChar[oddIndex] = string.charAt(i);
                oddIndex++;
            }
        }

        Arrays.sort(evenIndexChar);
        Arrays.sort(oddIndexChar);

        StringBuilder sortedStringBuilder = new StringBuilder();
        int i = 0;
        while((i < evenIndexChar.length) || (i < oddIndexChar.length)) {
            if(i < evenIndexChar.length) {
                sortedStringBuilder.append(evenIndexChar[i]);
            }
            if(i < oddIndexChar.length) {
                sortedStringBuilder.append(oddIndexChar[i]);
            }
            i++;
        }
        return sortedStringBuilder.toString();
    }

    /**
     * for each ch, if it is a hex ch, transfer this ch, else reamin
     * @param string
     * @return
     */
    private static String transferString(String string) {
        StringBuilder transferedSB = new StringBuilder();
        for(int i = 0; i < string.length(); i++) {
            char thisChar = string.charAt(i);
            if(((thisChar >= '0') && (thisChar <= '0'))
                || ((thisChar >= 'a') && (thisChar <= 'f'))
                || ((thisChar >= 'A') && thisChar <= 'F')) {
                String newChar = transferChar(thisChar);
                transferedSB.append(newChar);
            }
            else transferedSB.append(thisChar);

        }
        return transferedSB.toString();
    }

    /**
     * transfer a ch to binary string => reverse this binary string => transfer to hex string
     * @param ch
     * @return
     */
    private static String transferChar(char ch){
        int integer = 0;
        if((ch >= '0') && (ch <= '9')) {
            integer = Integer.parseInt(ch + "");
        }
        else if((ch >= 'a')&&(ch <= 'f')){
            integer = 10 + (ch - 'a');
        }
        else{
            integer = 10 + (ch - 'A');
        }
        String binary = Integer.toBinaryString(integer);
        String reverseBinary = new StringBuilder(binary).reverse().toString();
        int newInteger = 0;
        for(int i = 0; i < reverseBinary.length() - 1; i++) {
            newInteger = (newInteger + Integer.parseInt(reverseBinary.charAt(i) + "")) * 2;
        }
        newInteger += Integer.parseInt(reverseBinary.charAt(reverseBinary.length() - 1) + "");
        return  Integer.toHexString(newInteger).toUpperCase();
    }
}
