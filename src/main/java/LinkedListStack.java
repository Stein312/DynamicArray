package main.java;

public class LinkedListStack<T> extends Node<T> implements Stack<T> {

    @Override
    public void push(T t) {
        Node<T> o=new Node<T>();
        o.next=next;
        o.val=val;
        next=o;
        val=t;
    }
    public boolean isEmpty(){
        if(next==null && val==null)
        return true;
        else return false;
    }
    @Override
    public T pop() {
        T t;
        if(next!=null){
            if(val!=null){
                t=val;
                val= next.val;
                next=next.next;
                return t;
            }
        }
        t=val;
        val=null;
        return t;
    }
}
