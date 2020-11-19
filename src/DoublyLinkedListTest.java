import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    DoublyLinkedList<Integer> dll=new DoublyLinkedList<>();
    LinkedList<Integer> ll=new LinkedList<>();
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
        StopWatch.start();
        for(int i=0;i<=10_000_000;i++){
            dll.add(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=0;i<=10_000_000 ;i++){
            ll.add(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        Assert.assertArrayEquals(dll.toArray(),ll.toArray());

    }


    @Test
    void remove() {

        for(int i=0;i<=10_000_000;i++){
            dll.add(i);
        }

        for(int i=0;i<=10_000_000 ;i++){
            ll.add(i);
        }
        StopWatch.start();
        for(int i=10_000_000-1;i>=0;i--){
            dll.remove(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=10_000_000-1;i>=0;i--){
            ll.remove(i);
        }
        System.out.println(StopWatch.getElapsedTime());
        Assert.assertArrayEquals(dll.toArray(),ll.toArray());
    }

    @Test
    void testRemove() {
        for(int i=0;i<=10_000;i++){
            dll.add(i);
        }

        for(int i=0;i<=10_000 ;i++){
            ll.add(i);
        }
        StopWatch.start();
        for(int i=10_000-1;i>=0;i--){
            dll.remove((Integer)i);
        }
        System.out.println(StopWatch.getElapsedTime());
        StopWatch.start();
        for(int i=10_000-1;i>=0;i--){
            ll.remove((Integer)i);
        }
        System.out.println(StopWatch.getElapsedTime());
        Assert.assertArrayEquals(dll.toArray(),ll.toArray());
    }
}