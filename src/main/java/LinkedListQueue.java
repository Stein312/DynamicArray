package main.java;

public class LinkedListQueue<T> extends AbstractLinkedListQueue<T> {
    LinkedListQueue<T> next;
    T val;
    boolean added=false;

    public LinkedListQueue() {
        this.next = null;
        this.val = null;
    }

    @Override
    public boolean add(T t) {
        LinkedListQueue<T> llq;
        if(next!=null){
            llq = next;
            while(llq.next!=null){
                llq=llq.next;
            }
            llq.next=new LinkedListQueue<>();
            llq.next.val=t;
        }
        else if(added) {
            llq=new LinkedListQueue<T>();
            llq.next=null;
            llq.val=t;
            next=llq;
        }
        else {
            val=t;
        }
        added=true;
        return true;
    }

    @Override
    public T remove() {
        T t=val;
        if(next!=null){
        val= next.val;
        next=next.next;}
        else{
            val=null;
        }
        return t;
    }

}
