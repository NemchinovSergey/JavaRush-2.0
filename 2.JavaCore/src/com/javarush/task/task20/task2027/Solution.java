package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();

        for (String word : words) {
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++)
                {
                    String hDirectWord = getHorizontalDirectWord(crossword, x, y);
                    String hReversWord = getHorizontalReversWord(crossword, x, y);
                    String vDirectWord = getVerticalDirectWord(crossword, x, y);
                    String vReversWord = getVerticalReversWord(crossword, x, y);
                    String dDirectWord = getDiagonalDirectWord(crossword, x, y);
                    String dReversWord = getDiagonalReversWord(crossword, x, y);

                    // words matching
                    boolean found = false;
                    int endX = 0, endY = 0;
                    if (hDirectWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, hDirectWord, x, y));
                        endX = x + word.length() - 1;
                        endY = y;
                        found = true;
                    }
                    if (hReversWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, hReversWord, x, y));
                        endX = x - word.length() + 1;
                        endY = y;
                        found = true;
                    }
                    if (vDirectWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, vDirectWord, x, y));
                        endX = x;
                        endY = y + word.length() - 1;
                        found = true;
                    }
                    if (vReversWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, vReversWord, x, y));
                        endX = x;
                        endY = y - word.length() + 1;
                        found = true;
                    }
                    if (dDirectWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, dDirectWord, x, y));
                        endX = x + word.length() - 1;
                        endY = y + word.length() - 1;
                        found = true;
                    }
                    if (dReversWord.matches(word + ".*"))
                    {
                        System.out.println(String.format("The word '%s' is found in '%s' at x=%d, y=%d", word, dReversWord, x, y));
                        endX = x - word.length() + 1;
                        endY = y - word.length() + 1;
                        found = true;
                    }

                    if (found)
                    {
                        Word w = new Word(word);
                        w.setStartPoint(x, y);
                        w.setEndPoint(endX, endY);
                        list.add(w);
                    }
                }
            }
        }
        return list;
    }

    private static String getHorizontalDirectWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = x; i < crossword[y].length; i++)
        {
            char c = (char)crossword[y][i];
            result.append(c);
        }
        return result.toString();
    }

    private static String getHorizontalReversWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = x; i >= 0; i--)
        {
            char c = (char)crossword[y][i];
            result.append(c);
        }
        return result.toString();
    }

    private static String getVerticalDirectWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = y; i < crossword.length; i++)
        {
            char c = (char)crossword[i][x];
            result.append(c);
        }
        return result.toString();
    }

    private static String getVerticalReversWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = y; i >= 0; i--)
        {
            char c = (char)crossword[i][x];
            result.append(c);
        }
        return result.toString();
    }

    private static String getDiagonalDirectWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; x+i < crossword[y].length && y+i < crossword.length; i++)
        {
            char c = (char)crossword[y+i][x+i];
            result.append(c);
        }
        return result.toString();
    }

    private static String getDiagonalReversWord(int[][] crossword, int x, int y)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; x+i >= 0 && y+i >= 0; i--)
        {
            char c = (char)crossword[y+i][x+i];
            result.append(c);
        }
        return result.toString();
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
