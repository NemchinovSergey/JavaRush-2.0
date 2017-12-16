package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
Реализуй метод int maxSquare(int[][] matrix), возвращающий площадь самого большого квадрата состоящего из единиц в двумерном массиве matrix.
Массив matrix заполнен только нулями и единицами.


Требования:
1. Метод maxSquare должен быть реализован в соответствии с условием задачи.
2. Метод maxSquare должен эффективно работать с большими массивами.
3. Метод maxSquare должен быть публичным.
4. Метод maxSquare должен возвращать число типа int.
5. Метод maxSquare должен быть статическим.
*/

public class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {1, 1, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 1, 1},
                        {1, 1, 1, 1}
                };

        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int side = 0;

        for (int iy = 0; iy < matrix.length; iy++) {
            for (int ix = 0; ix < matrix[0].length; ix++) {
                if (ix * iy == 0) continue;

                if (matrix[iy][ix] == 1) {
                    matrix[iy][ix] = Math.min(matrix[iy - 1][ix], Math.min(matrix[iy][ix - 1], matrix[iy - 1][ix - 1])) + 1;
                }

                if (matrix[iy][ix] > side) {
                    side = matrix[iy][ix];
                }
            }
        }

        return side * side;
    }
}
