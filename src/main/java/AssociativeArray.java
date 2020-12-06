package main.java;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class AssociativeArray<K,V> {
    private final int DEFAULT_SIZE=50;



    private int size=0;
    transient Set<K> keySet;
    transient int modCount=0;
    private Node<K,V>[] arr;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    int threshold;

    public int getSize() {
        return size;
    }
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : Math.abs(key.hashCode() % arr.length);
    }
    public AssociativeArray() {
        arr=new Node[DEFAULT_SIZE];
        threshold=(int)(DEFAULT_SIZE*1.80);

    }
    public Set<K> keySet(){
        Set<K> ks=keySet;
         if (ks == null) {
            ks = new KeySet();
            keySet = ks;
        }
         return ks;
    }
    public V get(K key){
        Node<K,V> e=arr[hash(key)];
        if(e!=null){
            if(e.key.equals(key)){
                return e.value;
            }
            else{
                while(e.next!=null){
                    e=e.next;
                    if(e.key.equals(key)){
                        return e.value;
                    }
                }
            }
        }
        return null;
    }
    public V add(K key,V val){
        if(size>threshold){
            resize();
        }
        int hash= hash(key);
        size++;
        modCount++;
        return addVal(hash,key,val);
    }

    public V remove(K key){
            V val=null;
            int hash=hash(key);
            if(arr[hash]!=null){
                Node<K,V> e=arr[hash];
                if(e.key.equals(key)){
                    val=e.value;
                    e=e.next;
                    arr[hash]=e;
                }
                else if(e.next!=null){
                    Node<K,V> old=e;
                    Node<K,V> last;
                    while(e.next!=null){
                        last=e;
                        e=e.next;
                        if(e.key.equals(key)){
                                val=e.value;
                                e=e.next;
                                last.next=e;
                                Node<K,V> n;
                                break;
                        }
                    }

                }
                else{
                    val=null;
                }
            }
            size--;
            modCount++;
        return val;
    }
    final Node<K,V>[] resize(){
        Node<K,V>[] oldTab=arr;
        int oldCap=arr.length;
        int newCap;
        int newThr;
            if(oldCap< MAXIMUM_CAPACITY){
                newCap=oldCap<<1;
                newThr=(int)(newCap*1.8);
            }
            else{
                newThr=oldCap;
                newCap=oldCap;
            }
        threshold=newThr;
        @SuppressWarnings({"unchecked"})
        Node<K,V>[] newTab =(Node<K, V>[]) new Node[newCap];
        arr=newTab;
        for(int j = 0; j < oldCap; ++j){
            Node<K,V> e;
            e=oldTab[j];
            if(e!=null){
                int k=(int)e.value;
            addVal(hash(e.key),e.key,e.value);
            while(e.next!=null){
                e=e.next;
                int h=hash(e.key);
                addVal(hash(e.key),e.key,e.value);
            }
            }
        }

        return newTab;
    }
   /* final main.java.Node<K,V>[] resize(){
        main.java.Node<K,V>[] oldTab=arr;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY)
                newThr = oldThr << 1;
    }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = DEFAULT_SIZE;
        }
        threshold=newThr;
        @SuppressWarnings({"unchecked"})
        main.java.Node<K,V>[] newTab =(main.java.Node<K, V>[]) new main.java.Node[newCap];
        arr=newTab;
        if(oldTab!=null){
            for(int j = 0; j < oldCap; ++j){
                main.java.Node<K,V> e;
                e=oldTab[j];
                if(e!=null){
                addVal(hash(e.key),e.key,e.value);
                while(e.next!=null){
                    e=e.next;
                    addVal(hash(e.key),e.key,e.value);
                }
                }
            }
        }

        return newTab;
    }*/
    public V addVal(int hash,K key, V val){
        if(arr[hash]==null){
            arr[hash]=new Node<K, V>(hash,key,val,null);

        }
        else if(arr[hash].key.equals(key)){
            arr[hash].setValue(val);
        }
        else{
            Node<K,V> e=arr[hash];
            while(e.next!=null){
                e=e.next;
            }
            e.next=new Node<K, V>(hash,key,val,null);
        }
        return val;
    }
    public void clear() {
        Node<K,V>[] tab;
        modCount++;
        if ((tab = arr) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
    public boolean containsKey(Object key) {
        return get((K)key) != null;
    }
    abstract class HashIterator {
        Node<K,V> next;        // next entry to return
        Node<K,V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = arr;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = arr) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            AssociativeArray.this.remove(p.key);
            expectedModCount = modCount;
        }
    }
    final <T> T[] prepareArray(T[] a) {
        int size = this.size;
        if (a.length < size) {
            return (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
    <T> T[] keysToArray(T[] a) {
        Object[] r = a;
        Node<K,V>[] tab;
        int idx = 0;
        if (size > 0 && (tab = arr) != null) {
            for (Node<K,V> e : tab) {
                for (; e != null; e = e.next) {
                    r[idx++] = e.key;
                }
            }
        }
        return a;
    }

    final class KeyIterator extends HashIterator
            implements Iterator<K> {
        public final K next() { return nextNode().key; }
    }
    final class KeySet extends AbstractSet<K> {
        public final int size()                 { return size; }
        public final void clear()               { AssociativeArray.this.clear(); }
        public final Iterator<K> iterator()     { return new KeyIterator(); }
        public final boolean contains(Object o) { return containsKey(o); }
        public final boolean remove(Object key) {
            return AssociativeArray.this.remove((K)key) != null;
        }

        public Object[] toArray() {
            return keysToArray(new Object[size]);
        }

        public <T> T[] toArray(T[] a) {
            return keysToArray(prepareArray(a));
        }

        public final void forEach(Consumer<? super K> action) {
            Node<K,V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = arr) != null) {
                int mc = modCount;
                for (Node<K,V> e : tab) {
                    for (; e != null; e = e.next)
                        action.accept(e.key);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    static class Node<K,V>{
        final int hash;
        final K key;
        V value;

        Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}
