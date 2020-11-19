import java.util.*;
import java.util.function.Consumer;

public class DoublyLinkedList<T> extends AbstractList<T> implements List<T>{
    int size;
    transient Element<T> first;
    transient Element<T> last;
    transient int modCount;
    public boolean add(T val){
        final Element<T> l=last;
       final Element<T> e= new Element<>(l, val, null);
        last = e;

        if (l == null)
            first = e;
        else
            l.next = e;
        size++;
        modCount++;
        return true;
    }



    public void add(int i,T e){
        checkPositionIndex(i);
        if (i == size)
            add(e);
        else
            linkBefore(e, node(i));

    }
    public T remove(int i){
        checkPositionIndex(i);
        return unlink(node(i));
        }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }


    public boolean remove(Object e){
        if (e == null) {
            for (Element<T> x = first; x != null; x = x.next) {
                if (x.val == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Element<T> x = first; x != null; x = x.next) {
                if (e.equals(x.val)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    public T get(int i){
        return node(i).val;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    private void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
    T unlink(Element<T> x) {
        final T element = x.val;
        final Element<T> next = x.next;
        final Element<T> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.val = null;
        size--;
        modCount++;
        return element;

    }
    void linkBefore(T e, Element<T> succ) {

        final Element<T> pred = succ.prev;
        final Element<T> newNode = new Element<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }
    Element<T> node(int index) {
        if (index < (size >> 1)) {
            Element<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Element<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    private static class Element<T>{
        Element<T> prev;
        Element<T> next;
        T val;
        Element(Element<T> prev, T element, Element<T> next){
            this.val = element;
            this.next = next;
            this.prev = prev;
        }
    }
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Element<T> x = first; x != null; x = x.next)
            result[i++] = x.val;
        return result;
    }
    private class ListItr implements ListIterator<T> {
        private Element<T> lastReturned;
        private Element<T> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {

            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.val;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.val;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Element<T> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(T e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.val = e;
        }

        public void add(T e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                add(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.val);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
    private static class Node<T> {
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





}
