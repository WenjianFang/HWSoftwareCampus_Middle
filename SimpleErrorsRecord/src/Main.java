/**
 * Created by Wenjian on 2017/3/10, 0010.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *处理：
 * 1、 记录最多8条错误记录，循环记录，对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 * 3、 输入的文件可能带路径，记录文件名称不能带路径
 */
public class Main {
    static class ErrorRecord {
        String fileName;
        int lineNumber;
        int errorNumber;

        ErrorRecord() {
            this.fileName = "";
            this.lineNumber = -1;
            this.errorNumber = 0;
        }
        @Override
        public boolean equals(Object obj) {
            if((fileName.equals(((ErrorRecord) obj).fileName)) && (lineNumber == ((ErrorRecord) obj).lineNumber))
                return true;
            else return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<ErrorRecord> errorList = new ArrayList<>();
        while (sc.hasNextLine()){
            String nextLine = sc.nextLine();
            if(nextLine.equals("")) break;
            ErrorRecord newError = new ErrorRecord();
            String[] inputInfo = nextLine.split("\\s+");
            String path = inputInfo[0];
            String fileName = path.substring(path.lastIndexOf("\\") + 1);//
            if(fileName.length() > 16) {
                newError.fileName = fileName.substring(fileName.length() - 16);
            }
            else newError.fileName = fileName;
            newError.lineNumber = Integer.valueOf(inputInfo[1]);

            if(errorList.contains(newError)) {
                errorList.get(errorList.indexOf(newError)).errorNumber++;
            }
            else {
                newError.errorNumber++;
                errorList.add(newError);
            }
        }
        int i = 0;
        if(errorList.size() > 8) i = errorList.size() - 8;
        for(; i < errorList.size(); i++) {
            System.out.println(errorList.get(i).fileName + " " + errorList.get(i).lineNumber + " " + errorList.get(i).errorNumber);
        }
    }
}
