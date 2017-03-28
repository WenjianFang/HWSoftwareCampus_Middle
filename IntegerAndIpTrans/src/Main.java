/**
 * Created by Wenjian on 2017/3/28, 0028.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * IP and Integer transfer
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip1String = sc.nextLine();
        Long ip2Integer = sc.nextLong();

        Long ip1Integer = ipStringToInteger(ip1String);
        String ip2String = ipIntegerToString(ip2Integer);

        System.out.println(ip1Integer);
        System.out.println(ip2String);
    }

    /**
     *
     * @param ipString
     * @return
     */
    private static Long ipStringToInteger(String ipString) {
        String[] ipStrs = ipString.split("\\.");
        /*assume the ipStrs have 4 ips, because not rule to handle this*/
        Long ipInteger = (long) 0;
        for(int i = 0; i < ipStrs.length; i++) {
            ipInteger += (long) (Integer.parseInt(ipStrs[i]) << (8 * (ipStrs.length - i - 1)));
        }
        return ipInteger;
    }

    /**
     *
     * @param ipInteger
     * @return
     */
    private static String ipIntegerToString(Long ipInteger) {
        List<Long> ipList  = new ArrayList<>();
        StringBuilder ipSb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            ipList.add((ipInteger >> (8 * i))  & 0xFF);
        }

        for(int i = ipList.size() - 1; i >= 0; i--) {
            ipSb.append(ipList.get(i));
            if(i > 0) {
                ipSb.append(".");
            }
        }
        return ipSb.toString();
    }
}
