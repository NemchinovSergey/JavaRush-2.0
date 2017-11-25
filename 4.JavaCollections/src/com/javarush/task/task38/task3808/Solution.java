package com.javarush.task.task38.task3808;

import java.util.ArrayList;
import java.util.List;

/* 
Неверные аннотации
*/

public class Solution {
    @Main
    public static void main(String[] args) {
        Solution solution = new Solution().new SubSolution();
        solution.overriddenMethod();
    }

    public void overriddenMethod() {
    }

    public class SubSolution extends Solution {
        @SafeVarargs
        public void overriddenMethod() {
            System.out.println(uncheckedCall());
        }

        @Override
        List uncheckedCall() {
            List list = new ArrayList();
            list.add("hello");
            return list;
        }
    }


}
