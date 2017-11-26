package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        /*System.out.println(romanToInteger("I") + " = 1");
        System.out.println(romanToInteger("II") + " = 2");
        System.out.println(romanToInteger("III") + " = 3");
        System.out.println(romanToInteger("IV") + " = 4");
        System.out.println(romanToInteger("V") + " = 5");
        System.out.println(romanToInteger("VI") + " = 6");
        System.out.println(romanToInteger("VII") + " = 7");
        System.out.println(romanToInteger("VIII") + " = 8");
        System.out.println(romanToInteger("IX") + " = 9");
        System.out.println(romanToInteger("X") + " = 10");
        System.out.println(romanToInteger("XII") + " = 12");
        System.out.println(romanToInteger("XIV") + " = 14");
        System.out.println(romanToInteger("XXVII") + " = 27");
        System.out.println(romanToInteger("XL") + " = 40");
        System.out.println(romanToInteger("XLVI") + " = 46");
        System.out.println(romanToInteger("LXXX") + " = 80");
        System.out.println(romanToInteger("LXXXII") + " = 82");
        System.out.println(romanToInteger("MMCDLXXIV") + " = 2474");
        System.out.println(romanToInteger("MMMMCMXCIX") + " = 4999");*/

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        StringBuilder sbuf = new StringBuilder(s.trim().toUpperCase());

        Map<Integer, String> patterns = new LinkedHashMap<>();
        patterns.put(3000, "MMM");
        patterns.put(2000, "MM");
        patterns.put(900, "CM");
        patterns.put(1000, "M");
        patterns.put(800, "DCCC");
        patterns.put(700, "DCC");
        patterns.put(600, "DC");
        patterns.put(400, "CD");
        patterns.put(500, "D");
        patterns.put(300, "CCC");
        patterns.put(200, "CC");
        patterns.put(90, "XC");
        patterns.put(100, "C");
        patterns.put(80, "LXXX");
        patterns.put(70, "LXX");
        patterns.put(60, "LX");
        patterns.put(40, "XL");
        patterns.put(50, "L");
        patterns.put(30, "XXX");
        patterns.put(20, "XX");
        patterns.put(9, "IX");
        patterns.put(10, "X");
        patterns.put(8, "VIII");
        patterns.put(7, "VII");
        patterns.put(6, "VI");
        patterns.put(4, "IV");
        patterns.put(5, "V");
        patterns.put(3, "III");
        patterns.put(2, "II");
        patterns.put(1, "I");

        //Find Patterns
        int sum = 0;
        for (Map.Entry<Integer, String> pair : patterns.entrySet()) {
            int pos = sbuf.indexOf(pair.getValue());
            if (pos >= 0) {
                sum += pair.getKey();
                sbuf.delete(pos, pos + pair.getValue().length());
            }
        }
        return sum;
    }
}
