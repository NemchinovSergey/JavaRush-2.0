package com.javarush.task.task20.task2025;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {

    @Test
    public void getNumbers() throws Exception {
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L}, Solution.getNumbers(7));
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L}, Solution.getNumbers(100));
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L}, Solution.getNumbers(407));
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L}, Solution.getNumbers(1000));
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L, 1634L, 8208L, 9474L, 54748L, 92727L, 93084L}, Solution.getNumbers(100000));
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L, 1634L, 8208L, 9474L, 54748L, 92727L, 93084L, 548834L, 1741725L, 4210818L, 9800817L, 9926315L, 24678050L, 24678051L, 88593477L}, Solution.getNumbers(100000000));
    }
}