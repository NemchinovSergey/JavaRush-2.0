package com.javarush.task.task20.task2026;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a)
    {
        List<Rect> list = new ArrayList<>();

        for (int y = 0; y < a.length; y++)
        {
            for (int x = 0; x < a[y].length; x++)
            {
                if (a[y][x] == 1 && !isRectExists(list, x, y))
                {
                    // вычисляем ширину прямоугольника
                    int width = 0;
                    for (int j = x; j < a[y].length; j++)
                    {
                        if (a[y][j] == 1)
                            width++;
                        else
                            break;
                    }

                    // вычисляем высоту прямоугольника
                    int height = 0;
                    for (int j = y; j < a.length; j++)
                    {
                        if (a[j][x] == 1)
                            height++;
                        else
                            break;
                    }

                    //System.out.println(String.format("Rect: x = %d, y = %d, width = %d, height = %d", x, y, width, height));

                    list.add(new Rect(x, y, width, height));
                }
            }
        }
        return list.size();
    }

    static class Rect
    {
        private int x;
        private int y;
        private int width;
        private int height;

        public Rect(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        boolean isContains(int X, int Y)
        {
            return (X >= getLeft() && X <= getRight() && Y >= getTop() && Y <= getBottom());
        }

        int getLeft()
        {
            return x;
        }

        int getRight()
        {
            return x + width - 1;
        }

        int getTop()
        {
            return y;
        }

        int getBottom()
        {
            return y + height - 1;
        }
    }

    private static boolean isRectExists(List<Rect> list, int x, int y)
    {
        for (Rect rect : list)
        {
            if (rect.isContains(x, y))
                return true;
        }
        return false;
    }
}
