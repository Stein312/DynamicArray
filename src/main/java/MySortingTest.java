package main.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySortingTest {

    @Test
    void bubbleSort() {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0;i<=10;i++){
            arr.add((int)(Math.random() * (100 - 1) + 1));
        }
        arr.add(Integer.MAX_VALUE);
        System.out.println(Arrays.toString(arr.toArray()));
        System.out.println();
        ArrayList<Integer> arr2= new ArrayList<>(MySorting.bubbleSort(arr));

        System.out.println(Arrays.toString(arr2.toArray()));
    }
    @Test
    void insertionSort() {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0;i<=10;i++){
            arr.add((int)(Math.random() * (100 - 1) + 1));
        }
        arr.add(Integer.MAX_VALUE);
        System.out.println(Arrays.toString(arr.toArray()));
        System.out.println();
        ArrayList<Integer> arr2= new ArrayList<>(MySorting.insertionSort(arr));

        System.out.println(Arrays.toString(arr2.toArray()));

    }
    @Test
    void quickSort() {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0;i<=10;i++){
            arr.add((int)(Math.random() * (100 - 1) + 1));
        }
        System.out.println(Arrays.toString(arr.toArray()));
        System.out.println();
        ArrayList<Integer> arr2= new ArrayList<>(MySorting.quickSort(arr));
        System.out.println(Arrays.toString(arr2.toArray()));

    }
    @Test
    void mergeSort() {
        ArrayList<Integer> arr=new ArrayList<>();

        for(int i=0;i<=50;i++) {
            arr.add((int) (Math.random() * (100 - 1) + 1));
        }

        System.out.println(Arrays.toString(arr.toArray()));
        System.out.println();
        ArrayList<Integer> arr2= new ArrayList<>(MySorting.mergeSort(arr));
        System.out.println(Arrays.toString(arr2.toArray()));

    }
}