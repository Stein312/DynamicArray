package main.java;

public class Node<T> {
    Node<T> next;
    T val;

    public void reverse() {
        T nextVal=val;
        val=next.val;
        next.val=nextVal;

    }

    public void swap(Node<T> n1, Node<T> n2){
        Node<T> rev=n1.next;
        n1.next=n2.next;
        n2.next=n1.next;

    }


}
