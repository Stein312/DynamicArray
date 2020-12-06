package main.java;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class AssociativeArrayTest {
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
    void add() {

        AssociativeArray<Integer,Integer> aa=new AssociativeArray<>();
        StopWatch.start();
        for(int i=0;i<=1_000_000;i++)
            aa.add(i,i);
       System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
       for(int i=0;i<=1_000_000;i++)
            aa.remove(i);

         System.out.println(StopWatch.getElapsedTime());


       /* for(int i=0;i<=100_000;i++){
            System.out.println(aa.get(i));
        }*/
        System.out.println();

        HashMap<Integer,Integer> hm=new HashMap<>();
        StopWatch.start();
        for(int i=0;i<=1_000_000;i++)
            hm.put(i,i);
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=0;i<=1_000_000;i++){
            hm.remove(i);
        }
        System.out.println(StopWatch.getElapsedTime());



    }
}