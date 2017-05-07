package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream password = new ByteArrayOutputStream();

        while (true) {
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                char c;
                switch (random.nextInt(3)) {
                    case 0:
                        c = (char) ('a' + random.nextInt('z' - 'a'));
                        break;
                    case 1:
                        c = (char) ('A' + random.nextInt('Z' - 'A'));
                        break;
                    default:
                        c = (char) ('0' + random.nextInt('9' - '0'));
                        break;
                }
                password.write(c);
            }

            if (password.toString().matches(".*[0-9]+.*") &&
                    password.toString().matches(".*[a-z]+.*") &&
                    password.toString().matches(".*[A-Z]+.*"))
            {
                return password;
            }
            else
                password.reset();
        }
    }
}