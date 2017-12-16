package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит, принадлежит ли переданная точка многоугольнику.
Для граничных точек принадлежность не определена: isPointInPolygon может вернуть как true так и false.
Метод main не изменяй.


Требования:
1. Метод isPointInPolygon должен вернуть true, если в него передать точку с координатами: 5, 5 и многоугольник, созданный в методе main.
2. Метод isPointInPolygon должен вернуть false, если в него передать точку с координатами: 100, 100 и многоугольник, созданный в методе main.
3. Программа должна вывести 2 строки: true и false.
4. Метод isPointInPolygon должен корректно работать для любых входных данных.
5. Метод main не изменяй.

// количества пересечений луча, исходящего из данной точки в направлении горизонтальной
// оси, со сторонами многоугольника
// https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%97%D0%B0%D0%B4%D0%B0%D1%87%D0%B0_%D0%BE_%D0%BF%D1%80%D0%B8%D0%BD%D0%B0%D0%B4%D0%BB%D0%B5%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D0%B8_%D1%82%D0%BE%D1%87%D0%BA%D0%B8_%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D1%83


*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        boolean b = false;
        int j = polygon.size() - 1;

        for (int i = 0; i < polygon.size(); i++) {
            if (((polygon.get(i).y - point.y) * (polygon.get(j).y - point.y) < 0)
                    && (point.x > (point.y * (polygon.get(j).x - polygon.get(i).x) + polygon.get(j).y * polygon.get(i).x - polygon.get(i).y * polygon.get(j).x) / (polygon.get(j).y - polygon.get(i).y)))
            {
                b = !b;
            }

            j = i;
        }

        return b;
    }

}

