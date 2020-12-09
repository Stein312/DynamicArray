package main.java.homework08.search;

import main.java.homework07.code.tree.Node;
import main.java.homework07.code.tree.binary.LinkedBinaryTree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {

    @Test
    void put() {
        RedBlackTree<Integer> rbt=new RedBlackTree<>();
        for(int i=0;i<100;i++){
            rbt.put(i);
        }
        for(int i=0;i<=50;i++){
            rbt.remove(i);
        }

        Iterator<Integer> it=rbt.iterator();
        Integer i=0;
        while (it.hasNext()){
            i=it.next();
            System.out.println(i);
            it.remove();
        }
    }

}