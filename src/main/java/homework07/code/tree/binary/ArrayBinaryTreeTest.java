package main.java.homework07.code.tree.binary;

import main.java.homework07.code.tree.Node;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBinaryTreeTest {

    @Test
    void left() {
        ArrayBinaryTree<Integer> arr=new ArrayBinaryTree<>();
        arr.add(5);
        arr.add(6);
        arr.add(4);
        arr.add(2);
        arr.add(8);
        arr.add(7);
        arr.add(-15);
        int i=arr.left(arr.root()).getElement();
        Assert.assertEquals(4,i);
    }

    @Test
    void right() {
        ArrayBinaryTree<Integer> arr=new ArrayBinaryTree<>();
        arr.add(5);
        arr.add(6);
        arr.add(4);
        arr.add(2);
        arr.add(8);
        arr.add(7);
        arr.add(-15);
        int i=arr.right(arr.root()).getElement();
    }

    @Test
    void remove() {
        ArrayBinaryTree<Integer> arr=new ArrayBinaryTree<>();
        arr.add(5);
        arr.add(6);
        arr.add(4);
        arr.add(2);
        arr.add(8);
        arr.add(7);
        arr.add(-15);
        arr.remove(-15);
        arr.remove(4);
        arr.remove(8);
        arr.remove(9);
        Iterator<Integer> it=arr.iterator();
        Integer i=0;
        while (it.hasNext()){
            i=it.next();
            System.out.println(i);
        }
    }

    @Test
    void add() {
        ArrayBinaryTree<Integer> arr=new ArrayBinaryTree<>();
        arr.add(5);
        arr.add(6);
        arr.add(4);
        arr.add(2);
        arr.add(8);
        arr.add(7);
        arr.add(-15);
        Iterator<Integer> it=arr.iterator();
        Integer i=0;
        while (it.hasNext()){
            i=it.next();
            System.out.println(i);
        }
    }

}