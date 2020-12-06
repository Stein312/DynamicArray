package main.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LinkedListStackTest {
    @Test
    void push(){
        LinkedListStack<Integer> lls=new LinkedListStack<>();

        for(int i=0;i<=10_000_000;i++){
            lls.push(i);
        }
        for(int i=10_000_000;i>0;i--){
            int a=lls.pop();
            Assert.assertEquals(i,a);
        }


    }

}