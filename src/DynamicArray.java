import java.util.*;
import java.util.function.Consumer;

public class DynamicArray<T> {
    private int size =0;
    private final int DEFAULT_SIZE=10;
    transient Object[] array;
    protected transient int modCount;

    public DynamicArray(){
        array=new Object[DEFAULT_SIZE];
    }
    public DynamicArray(int initialCapacity){
        if(initialCapacity>0) array=new Object[initialCapacity];
        else if (initialCapacity==0) array=new Object[DEFAULT_SIZE];
        else throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
    }
    private class ListItr extends Itr implements ListIterator<T> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = DynamicArray.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) elementData[lastRet = i];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(Object o) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                DynamicArray.this.set(lastRet, o);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }

        @Override
        public void add(Object o) {
            checkForComodification();

            try {
                int i = cursor;
                DynamicArray.this.add(i, o);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }
    }

    private class Itr implements Iterator<T>{
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;


        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public T next() {
            checkForComodification();
            int i=cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DynamicArray.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                DynamicArray.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            final int size = DynamicArray.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = array;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept((T)es[i]);
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
    /*public DynamicArray(Collection<? extends T> arr) {
        Object[] a=arr.toArray();
        if ((index = a.length) != 0) {
                this.array = Arrays.copyOf(a, index, Object[].class);
        } else {
            // replace with empty array.
            this.array = new Object[DEFAULT_SIZE];
        }
    }*/
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }
    public int add(Object e){
        modCount++;
        if(size == array.length) array = Arrays.copyOf(array, (int) (array.length * 1.5));
        array[size]=e;
        return size++;
    }
    public void add(int i,Object e){

        if(i>=0 && i<= size){
            modCount++;
            if (size ==array.length)
                array=Arrays.copyOf(array,(int)(array.length*1.5));
            System.arraycopy(array, i,
                    array, i + 1,
                    size - i);
            array[i]=e;
            size++;
        }
        else{
            throw new IndexOutOfBoundsException("Index: "+i+", Size: "+ size);
        }
    }
    public Object set(int i, Object e){
        Object prev=array[i];
        array[i]=e;
        return prev;
    }
    public void remove(int i){
        final int newSize;
        Objects.checkIndex(i,size);
        modCount++;
        if ((newSize = size - 1) > i)
            System.arraycopy(array, i + 1, array, i, newSize - i);
        array[size = newSize] = null;
    }
    public Object get(int i){
        return array[i];
    }
    public int remove(Object o){
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if (array[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(array[i]))
                        break found;
            }
            return -1;
        }

        remove(i);
        return i;
    }
    public int size(){
        return size;
    }
    public int indexOf(Object e){
        int i=0;
        if(e==null)
            for(;i<size;i++){
                if(array[i]==null)
                    return i;
            }
        for(;i<size;i++){
            if(e.equals(array[i]))
                return i;
        }
        return -1;
    }
    public boolean contains(Object e){
        if(e==null)
            for(int i=0;i<size;i++){
                if(array[i]==null)
                    return true;
            }
        for(int i=0;i<size;i++){
            if(e.equals(array[i]))
                return true;
        }
        return false;
    }
    public Object[] toArray(){

        return Arrays.copyOf(array,size);
    }

    public static void main(String[] args) {
        DynamicArray<Integer> darr=new DynamicArray<>();
        darr.add(123);
        darr.remove(100);
    }

}
