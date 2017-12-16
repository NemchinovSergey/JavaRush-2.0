package com.javarush.task.task39.task3909;

/* 
Одно изменение
Реализуй метод isOneEditAway(String first, String second) который будет возвращать true, если возможно изменить/добавить/удалить один символ в одной из строк и получить другую.

Символы в анализируемой строке ограничены кодировкой ASCII.
Регистр символов учитывается.


Требования:
1. Метод isOneEditAway должен корректно работать для строк одинаковой длины.
2. Метод isOneEditAway должен корректно работать для строк разной длины.
3. Метод isOneEditAway должен корректно работать для пустых строк.
4. Метод isOneEditAway должен быть публичным.
*/
public class Solution {
    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "13";
        System.out.println(s1 + "   " + s2);
        System.out.println(isOneEditAway(s1, s2));

        s1 = "123";
        s2 = "143";
        System.out.println(s1 + "   " + s2);
        System.out.println(isOneEditAway(s1, s2));

        s1 = "123466";
        s2 = "123666";
        System.out.println(s1 + "   " + s2);
        System.out.println(isOneEditAway(s1, s2));

        s1 = "123";
        s2 = "143";
        System.out.println(s1 + "   " + s2);
        System.out.println(isOneEditAway(s1, s2));

    }

    public static boolean isOneEditAway(String first, String second) {
        char[] firstCh, secondCh;
        if (first.length() < second.length()) {
            firstCh = first.toCharArray();
            secondCh = second.toCharArray();
        } else {
            firstCh = second.toCharArray();
            secondCh = first.toCharArray();
        }

        if (firstCh.length - secondCh.length < -1) return false;

        boolean b = true;
        boolean b2 = false;
        int dop_i = 0;

        for (int i = 0; i < firstCh.length; i++) {
            if(firstCh[i] == secondCh[i + dop_i])continue;

            if(firstCh.length == secondCh.length && firstCh[i] != secondCh[i + dop_i])
            {
                if(!b2)
                    b2 = true;
                else
                {b = false;break;}
                continue;
            }

            if (i + dop_i < secondCh.length && firstCh[i] != secondCh[i + dop_i]) {
                if (dop_i == 1 || b2) {
                    b = false;
                    break;
                }

                if (i + 1 < secondCh.length) {
                    if(firstCh[i] == secondCh[i + 1]) {
                        dop_i = 1;
                    }
                    else if(i + 1 < firstCh.length && firstCh[i+1] == secondCh[i + 1])
                    {
                        b2 = true;
                    }
                    else
                    {
                        b = false;break;
                    }
                }
            }
            else if(i+1 == firstCh.length && !b2)
            {
                b = true;
            }
            else
            {b = false;break;}
        }

        return b;
    }
}
