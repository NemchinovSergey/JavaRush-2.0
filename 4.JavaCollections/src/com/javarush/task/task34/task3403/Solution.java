package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int d = 2;
        while (n != 1) {
            if (n % d == 0) {
                System.out.print(d + " ");
                n /= d;
                recursion(n);
                return;
            }
            else {
                d ++;
            }
        }
    }

    public static void main(String[] args) {
        new Solution().recursion(1645894122);
    }
}
