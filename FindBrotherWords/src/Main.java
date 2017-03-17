import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Wenjian on 2017/3/17, 0017.
 */
public class Main {
    static final int ENGLISH_CHARACTER_NUM = 26;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wordNum = sc.nextInt();
        List<String> dictList = new ArrayList<>();
        for(int i = 0; i < wordNum; i++) {
            dictList.add(sc.next());
        }
        String findThisStrBro = sc.next();
        int whichBro = sc.nextInt();

        findBrotherWords(dictList, findThisStrBro, whichBro);
    }

    /**
     *
     * @param dictList
     * @param findThisStrBro
     * @param whichBro
     */
    private static void findBrotherWords(List<String> dictList, String findThisStrBro, int whichBro) {
        List<String> brotherWords = new ArrayList<>();
        for(int i = 0; i < dictList.size(); i++) {
            if(brotherWord(dictList.get(i), findThisStrBro)) {
                brotherWords.add(dictList.get(i));
            }
        }

        Collections.sort(brotherWords);
        System.out.println(brotherWords.size());
        System.out.println(brotherWords.get(whichBro - 1));
    }

    /**
     * 
     * @param str1
     * @param str2
     * @return
     */
    private static boolean brotherWord(String str1, String str2) {

        if(str1.length() != str2.length()) return false;
        if(str1.equals(str2)) return false;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int[] eachCharacter = new int[ENGLISH_CHARACTER_NUM];

        for(int i = 0; i < str1.length(); i++) eachCharacter[str1.charAt(i) - 'a']++;
        for(int i = 0; i < str2.length(); i++) eachCharacter[str2.charAt(i) - 'a']--;
        for(int i = 0; i < eachCharacter.length; i++) {
            if(eachCharacter[i] != 0) return false;
        }
        return true;
    }
}
