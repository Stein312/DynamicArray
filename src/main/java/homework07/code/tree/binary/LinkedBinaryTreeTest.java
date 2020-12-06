package main.java.homework07.code.tree.binary;

import main.java.homework07.code.tree.Node;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedBinaryTreeTest {

    @Test
    void addRoot() {
        LinkedBinaryTree<Integer> lbt=new LinkedBinaryTree<>();
        Node<Integer> n=lbt.addRoot(8);
        int i=n.getElement();
        Assert.assertEquals(i,8);
    }

    @Test
    void add() {
        LinkedBinaryTree<Integer> lbt=new LinkedBinaryTree<>();
        Node<Integer> n=lbt.addRoot(8);
        Node<Integer> r;
        Node<Integer> l;
        l=lbt.addLeft(n,7);
        r=lbt.addRight(n,9);
        int i=l.getElement();
        Assert.assertEquals(i,7);
        i=r.getElement();
        Assert.assertEquals(i,9);

    }

    @Test
    void set() {
        LinkedBinaryTree<Integer> lbt=new LinkedBinaryTree<>();
        Node<Integer> n=lbt.addRoot(8);
        lbt.addLeft(n,7);
        lbt.addRight(n,10);
        lbt.set(n,11);
        int i=n.getElement();
        Assert.assertEquals(i,9);
    }

    @Test
    void remove() {
        LinkedBinaryTree<Integer> lbt=new LinkedBinaryTree<>();
        Node<Integer> n=lbt.addRoot(8);
        Node<Integer>r=n;
        lbt.addLeft(n,7);
        n=lbt.addRight(n,10);
        Node<Integer> l=lbt.addLeft(n,9);
        lbt.addRight(n,11);
        lbt.remove(n);
        int i=lbt.left(lbt.right(r)).getElement();
        Assert.assertEquals(i,9);
    }
}