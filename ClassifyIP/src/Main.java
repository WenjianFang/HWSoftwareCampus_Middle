/**
 * Created by Wenjian on 2017/3/9, 0009.
 */

import java.util.Scanner;

/**
 * Classify Ip
 */
public class Main {
    enum IP_CATEGORY{
        IP_A,
        IP_B,
        IP_C,
        IP_D,
        IP_E,
        IP_ILLEGAL,
        MASK_ILLEGAL
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //String inputString = sc.nextLine();

        //String[] ipAndMask = inputString.split(" ");
        String[] ipAndMask = new String[4];
        for(int i = 0; i < 4; i++) {
            ipAndMask[i] = sc.nextLine();
        }
        classifyIP(ipAndMask);
    }
    private static void classifyIP(String[] ipAndMask) {
        int ACategory = 0;
        int BCategory = 0;
        int CCategory = 0;
        int DCategory = 0;
        int ECategory = 0;
        int IpIllegalCategory = 0;
        int MaskIllegalCategory = 0;
        int PrivateCategory = 0;
        for(int i = 0; i < ipAndMask.length; i++) {
            String[] ipMaskArray = ipAndMask[i].split("~");
            String ip = ipMaskArray[0];
            String mask = ipMaskArray[1];
            IP_CATEGORY returnCode = checkIpCategory(ip, mask);
            boolean whetherPrivate = false;
            if(returnCode == IP_CATEGORY.IP_A || returnCode == IP_CATEGORY.IP_B || returnCode == IP_CATEGORY.IP_C) {
                whetherPrivate = checkIpPrivate(ip, mask);
            }
            switch (returnCode) {
                case IP_A:
                    ACategory ++;
                    break;
                case IP_B:
                    BCategory++;
                    break;
                case IP_C:
                    CCategory++;
                    break;
                case IP_D:
                    DCategory++;
                    break;
                case IP_E:
                    ECategory++;
                    break;
                case IP_ILLEGAL:
                    IpIllegalCategory++;
                    break;
                case MASK_ILLEGAL:
                    MaskIllegalCategory++;
                    break;
                default:
                    break;
            }
            if(whetherPrivate) PrivateCategory++;
        }
        System.out.println(ACategory + " " + BCategory + " " + CCategory + " " + DCategory + " " + ECategory + " "
                            + (IpIllegalCategory+MaskIllegalCategory) + " " + PrivateCategory);
    }

    /**
     *
     * @param ip
     * @param mask
     * @return
     */
    private static IP_CATEGORY checkIpCategory(String ip, String mask) {
        String[] ipArray = ip.split("\\.");
        String[] maskArray = mask.split("\\.");
        if(!isLegalIp(ipArray)) return IP_CATEGORY.IP_ILLEGAL;
        if(!isLegalMask(maskArray)) return IP_CATEGORY.MASK_ILLEGAL;
        /*just check the first part*/
        try {
            int netNumber = Integer.valueOf(ipArray[0]) & Integer.valueOf(maskArray[0]);
            if((netNumber >= 1) && (netNumber <= 126)) return IP_CATEGORY.IP_A;
            else if((netNumber >= 128) && (netNumber <= 191)) return IP_CATEGORY.IP_B;
            else if((netNumber >= 192) && (netNumber <= 223)) return IP_CATEGORY.IP_C;
            else if((netNumber >= 224) && (netNumber <= 239)) return IP_CATEGORY.IP_D;
            else if((netNumber >= 240) && (netNumber <= 255)) return IP_CATEGORY.IP_E;
            return IP_CATEGORY.IP_ILLEGAL;
        }catch (Exception ex){
            return IP_CATEGORY.IP_ILLEGAL;
        }
    }

    /**
     *
     * @param ipArray
     * @return
     */
    private static boolean isLegalIp(String[] ipArray) {
        if(ipArray.length != 4) return false;
        for(int i = 0; i < ipArray.length; i++) {
            try {
                if ((Integer.valueOf(ipArray[i]) < 0) || (Integer.valueOf(ipArray[i]) > 255)) {
                    return false;
                }
            }catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param maskArray
     * @return
     */
    private static boolean isLegalMask(String[] maskArray) {
        if(maskArray.length != 4) return false;
        boolean isTheLast = false;
        for(int i = 0; i < maskArray.length; i++) {
            try {
                if ((Integer.valueOf(maskArray[i]) < 0) || (Integer.valueOf(maskArray[i]) > 255)) {
                    return false;
                }
                else {
                    if(isTheLast && (Integer.valueOf(maskArray[i]) == 255)) return false;
                    else if(Integer.valueOf(maskArray[i]) < 255) {
                        /*must be the last part*/
                        isTheLast = true;
                    }
                }
            }catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param ip
     * @param mask
     * @return
     */
    private static boolean checkIpPrivate(String ip, String mask) {
        /*ensure the ip and mask both legal*/
        int[] andResult = new int[4];
        String[] ipArray = ip.split("\\.");
        String[] maskArray = mask.split("\\.");
        for(int i = 0; i < 4; i++) {
            andResult[i] = Integer.valueOf(ipArray[i]) & Integer.valueOf(maskArray[i]);
        }

        if ((andResult[0] == 10)
                && ((andResult[1] >= 0) && (andResult[1] <= 255))
                && ((andResult[2] >= 0) && (andResult[2] <= 255))
                && ((andResult[3] >= 0) && (andResult[3] <= 255)))
            return true;
        else if ((andResult[0] == 172)
                && ((andResult[1] >= 16) && (andResult[1] <= 31))
                && ((andResult[2] >= 0) && (andResult[2] <= 255))
                && ((andResult[3] >= 0) && (andResult[3] <= 255)))
            return true;
        else if ((andResult[0] == 192)
                && (andResult[1] == 168)
                && ((andResult[2] >= 0) && (andResult[2] <= 255))
                && ((andResult[3] >= 0) && (andResult[3] <= 255)))
            return true;
        return false;
    }
}
