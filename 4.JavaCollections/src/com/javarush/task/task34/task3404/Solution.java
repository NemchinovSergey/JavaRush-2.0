package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);

        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);

        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);

        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);

        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);

        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);

        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);

        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);

        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);

        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);

        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);

        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);

        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);

        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);

        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);

        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);

        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);

        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);

        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);/**/
    }

    public void recursion(final String expression, int countOperation) {
        boolean hasOpenBracketPos = false;
        int openBracketPos = 0;
        int closeBracketPos = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' && hasOpenBracketPos) {
                openBracketPos = i;
            } else if (c == '(') {
                openBracketPos = i;
                hasOpenBracketPos = true;
            }
            if (c == ')') {
                closeBracketPos = i;
                break;
            }
        }

        // вычисление подвыражения:
        String subExpression;
        if (openBracketPos == 0 && closeBracketPos == 0) {
            subExpression = expression;
        } else {
            subExpression = expression.substring(openBracketPos + 1, closeBracketPos);
        }
        subExpression = subExpression.replaceAll(" ", "");


        List<String> operations = new ArrayList<>();
        List<String> digitals = new ArrayList<>();
        String number = "";
        boolean isOperationFirst = false;
        boolean isNumber = false;

        // формирование списка операций и чисел
        for (int i = 0; i < subExpression.length(); i++) {
            char c = subExpression.charAt(i);
            if (c == '[') {
                isNumber = true;
                continue;
            }
            if (c == ']') {
                isNumber = false;
                digitals.add(number);
                number = "";
                continue;
            }

            if (isNumber || Character.isDigit(c) || c == '.') {
                if (i < subExpression.length() - 1) {
                    number += c;
                    continue;
                } else {
                    number += c;
                }
                if (isNumber) continue;
            }
            if (!Objects.equals(number, "")) {
                digitals.add(number);
                number = "";
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (i == 0)
                    isOperationFirst = true;

                operations.add(String.valueOf(c));
            }
        }
        int operationsAmount = operations.size();
        int startSizeDigitals = digitals.size();

        int indexFirst = 0;
        if (isOperationFirst) {
            indexFirst = 1;
        }

        double result = 0;
        if (operations.size() == 0)
            result = Double.valueOf(digitals.get(0));

        while (operations.size() > 0) {
            //вычисление степени числа
            int index = 0;
            while (operations.contains("^")) {
                if (operations.get(index + indexFirst).equals("^")) {
                    double num = Double.valueOf(digitals.get(index));
                    double power = Double.valueOf(digitals.get(index + 1));
                    double res = Math.pow(num, power);
                    digitals.set(index, res + "");

                    digitals.remove(index + 1);
                    operations.remove(index + indexFirst);
                } else
                    index++;
            }

            index = 0;
            while (operations.contains("*") || operations.contains("/")) {
                switch (operations.get(index + indexFirst)) {
                    case "*": {
                        double num = Double.valueOf(digitals.get(index));
                        double power = Double.valueOf(digitals.get(index + 1));
                        double res = num * power;
                        digitals.set(index, res + "");

                        digitals.remove(index + 1);
                        operations.remove(index + indexFirst);
                        break;
                    }
                    case "/": {
                        double num = Double.valueOf(digitals.get(index));
                        double power = Double.valueOf(digitals.get(index + 1));
                        double res = num / power;
                        digitals.set(index, res + "");

                        digitals.remove(index + 1);
                        operations.remove(index + indexFirst);
                        break;
                    }
                    default:
                        index++;
                        break;
                }
            }


            // вычисление сложения и вычитания
            if (!isOperationFirst) {
                result = Double.valueOf(digitals.get(0));
                digitals.remove(0);
            }

            for (int i = 0; i < operations.size(); i++) {
                switch (operations.get(i)) {
                    case "-":
                        result -= Double.valueOf(digitals.get(i));
                        break;
                    case "+":
                        result += Double.valueOf(digitals.get(i));
                        break;
                }
            }
            operations.clear();
        }


        // после вычисления подвыражения, проверка и вычисление синуса, косинуса или тангенса:
        String trigoStr = null;
        if (openBracketPos >= 3) {
            String symbolsBeforeOpenBracket = expression.substring(openBracketPos - 3, openBracketPos);
            switch (symbolsBeforeOpenBracket) {
                case "sin":
                    result = Math.sin(Math.toRadians(result));
                    operationsAmount++;
                    trigoStr = "sin";
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(result));
                    operationsAmount++;
                    trigoStr = "cos";
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(result));
                    operationsAmount++;
                    trigoStr = "tan";
                    break;
                default:
                    break;
            }
        }
        result = Math.rint(100 * result) / 100.0;


        //Конкатенация нового expression для рекурсии:
        String newExpression;
        if ((trigoStr != null) && (expression.substring(openBracketPos - 3, openBracketPos).equals(trigoStr))) {
            newExpression = expression.substring(0, openBracketPos - 3) +
                            "[" + result + "]" +
                            (closeBracketPos == expression.length() - 1 ? "" : expression.substring(closeBracketPos + 1));
        } else {
            if (openBracketPos == 0 && closeBracketPos == 0) {
                newExpression = String.valueOf(result);
            } else {
                newExpression = expression.substring(0, openBracketPos) +
                                "[" + result + "]" +
                                (closeBracketPos == expression.length() - 1 ? "" : expression.substring(closeBracketPos + 1));
            }
        }

        if (operationsAmount == 0 || newExpression.equals(expression)) {
            double d = Double.valueOf(expression.replace("[", "").replace("]", ""));
            BigDecimal bd = new BigDecimal(d);
            DecimalFormat df = new DecimalFormat("0.##");
            System.out.println(df.format(bd) + " " + countOperation);
            return;
        }

        //    System.out.println(newExpression);
        recursion(newExpression, countOperation + operationsAmount);
    }

    public Solution() {
        //don't delete
    }
}
