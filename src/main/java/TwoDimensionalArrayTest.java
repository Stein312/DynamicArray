package main.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TwoDimensionalArrayTest {

    @Test
    void get() {
        int s1=1_000_000;
        int s2=1_000_000;

        TwoDimensionalArray<Integer> arr=new TwoDimensionalArray<>(s1,s2);

            for(int j=0;j<1_000_000;j++)
                arr.set(s1-1,j,j);







    }
}