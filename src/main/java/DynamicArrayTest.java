package main.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

class DynamicArrayTest {
    DynamicArray<Integer> dArr=new DynamicArray<>();
    ArrayList<Integer> arr=new ArrayList<>();
    private static class StopWatch{
        static long start;
        static long start(){
            start=System.currentTimeMillis();
            return start;
        }
        static long getElapsedTime(){
            return System.currentTimeMillis()- start;
        }
    }



    @Test
    void testAdd() {
        StopWatch.start();
        for(int i=0;i<=10_000_000;i++){
            dArr.add(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=0;i<=10_000_000 ;i++){
            arr.add(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        Assert.assertArrayEquals(arr.toArray(),dArr.toArray());
    }

    @Test
    void remove() {

        for(int i=0;i<10_000_000 ;i++){
            dArr.add(i);
        }
        for(int i=0;i<10_000_000 ;i++){
            arr.add(i);
        }
        StopWatch.start();
        for(int i=10_000_000-1;i>=0;i--){
            dArr.remove(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=10_000_000-1;i>=0;i--){
            arr.remove(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        System.out.println(dArr.size());
        System.out.println(arr.size());
        arr.stream().filter(num-> num%5==0);
        Assert.assertArrayEquals(arr.toArray(),dArr.toArray());
    }

    @Test
    void get() {
    }

    @Test
    void testRemove() {

        for(int i=0;i<10_000;i++){
            dArr.add(i);
        }
        for(int i=0;i<10_000 ;i++){
            arr.add(i);
        }

        StopWatch.start();
        for(int i=10_000-1;i>=0;i--){
            dArr.remove((Integer)i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=10_000-1;i>=0;i--){
            arr.remove((Integer)i);
        }
        System.out.println(StopWatch.getElapsedTime());
        Assert.assertArrayEquals(arr.toArray(),dArr.toArray());
    }
    @Test
    void testListIteratorHasNext(){
        for(int i=0;i<100;i++){
            dArr.add(i);
        }
        for(int i=0;i<100 ;i++){
            arr.add(i);
        }
        ListIterator<Integer> arrIterator= arr.listIterator();
        ListIterator<Integer> dArrIterator= dArr.listIterator();
        while (arrIterator.hasNext()) {
            Integer element = arrIterator.next();
            System.out.println(element);
        }
        while (dArrIterator.hasNext()) {
            Integer element = dArrIterator.next();
            System.out.println(element);
        }
        Assert.assertArrayEquals(arr.toArray(),dArr.toArray());

    }
    @Test
    void testListIteratorADD(){
        for(int i=0;i<100;i++){
            arr.add(i);
        }
        for(int i=0;i<100 ;i++){
            dArr.add(i);
        }
        ListIterator<Integer> arrIterator= arr.listIterator();
        ListIterator<Integer> dArrIterator= dArr.listIterator();
        int x=100;
        while (arrIterator.hasNext()){
            Integer element = arrIterator.next();
            arrIterator.add(x);
            x++;
        }
        x=100;
        while (dArrIterator.hasNext()){
            Integer element = dArrIterator.next();
            dArrIterator.add(x);
            x++;
        }
        System.out.println(Arrays.toString(arr.toArray()));
        System.out.println(Arrays.toString(dArr.toArray()));
        Assert.assertArrayEquals(arr.toArray(),dArr.toArray());

    }

    @Test
    void indexOf() {
    }

    @Test
    void contains() {
    }
}