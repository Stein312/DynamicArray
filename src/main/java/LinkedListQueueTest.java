package main.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LinkedListQueueTest {

    @Test
    void add() {
        LinkedListQueue<Integer> llq=new LinkedListQueue<>();
     /*   llq.add(123);
        llq.add(12);
        llq.remove();
        int a=llq.remove();
        Assert.assertEquals(12,a);*/
        for(int i=0;i<=10_000;i++){
            llq.add(i);
        }

        for(int i=0;i<=10_000;i++){
            int a=llq.remove();
            Assert.assertEquals(i,a);
    }
    }

    @Test
    void remove(){
    }
}