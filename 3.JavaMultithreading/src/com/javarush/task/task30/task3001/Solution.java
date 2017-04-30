package com.javarush.task.task30.task3001;

import java.math.BigInteger;

import static com.javarush.task.task30.task3001.NumerationSystemType.*;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, _2);
        System.out.println(result);    //expected 110

        // exceptions expected
        //System.out.println(convertNumberToOtherNumerationSystem(new Number(NumerationSystemType._10, "-120"), _2));
        //System.out.println(convertNumberToOtherNumerationSystem(new Number(NumerationSystemType._10, "12.0"), _2));
        //System.out.println(convertNumberToOtherNumerationSystem(new Number(NumerationSystemType._2, "120"), _10));
        Number number1 = new Number(_2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        System.out.println(convertNumberToOtherNumerationSystem(number1, _3));
        System.out.println(convertNumberToOtherNumerationSystem(number1, _8));
        System.out.println(convertNumberToOtherNumerationSystem(number1, _10)); //expected 519949509846173492335554907166
        System.out.println(convertNumberToOtherNumerationSystem(number1, _16));

        number = new Number(NumerationSystemType._10, "519949509846173492335554907166");
        result = convertNumberToOtherNumerationSystem(number, _2);
        System.out.println(result);    //expected 110100100000000110000100111011011111110011001010100010011101101101100100101100110001100110000011110

    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        boolean validSystem = false;
        switch (number.getNumerationSystem().getNumerationSystemIntValue()) {
            case 16:
                validSystem = number.getDigit().matches("[\\da-fA-F]+");
                break;
            case 12:
                validSystem = number.getDigit().matches("[\\da-bA-B]+");
                break;
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
                String pattern = "[0-" + (number.getNumerationSystem().getNumerationSystemIntValue() - 1) + "]+";
                validSystem = number.getDigit().matches(pattern);
                break;
        }

        if (!validSystem) {
            throw new NumberFormatException();
        }

        BigInteger bigInteger = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());
        String expectedValue = bigInteger.toString(expectedNumerationSystem.getNumerationSystemIntValue());

        return new Number(expectedNumerationSystem, expectedValue);
    }
}
