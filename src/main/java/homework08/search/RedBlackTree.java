package main.java.homework08.search;

import main.java.DoublyLinkedList;
import main.java.homework07.code.tree.Node;
import main.java.homework07.code.tree.binary.ArrayBinaryTree;
import main.java.homework07.code.tree.binary.LinkedBinaryTree;

import java.util.*;

public class RedBlackTree<E> extends LinkedBinaryTree<E> {
    public RedBlackNode<E> getRoot() {
        return root;
    }

    RedBlackNode<E> root;
    private final Comparator<? super E> comparator;
    private static final boolean RED   = false;
    private static final boolean BLACK = true;
    private int size=0;
    private int modCount = 0;

    public RedBlackTree() {
        this.comparator=null;
    }

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    private static <E> RedBlackNode<E> parentOf(RedBlackNode<E> p) {
        return (p == null ? null: p.parent);
    }
    private static <E> RedBlackNode<E> leftOf(RedBlackNode<E> p) {
        return (p == null) ? null: p.left;
    }

    private static <E> RedBlackNode<E> rightOf(RedBlackNode<E> p) {
        return (p == null) ? null: p.right;
    }

    private static <E> boolean colorOf(RedBlackNode<E> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <E> void setColor(RedBlackNode<E> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private void rotateLeft(RedBlackNode<E> p) {
        if (p != null) {
            RedBlackNode<E>  r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }
    private void rotateRight(RedBlackNode<E> p) {
        if (p != null) {
            RedBlackNode<E> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }


    private void fixAfterInsertion(RedBlackNode<E> x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                RedBlackNode<E> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                RedBlackNode<E> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }
    final int compare(Object k1, Object k2) {
        return comparator==null ? ((Comparable<? super E>)k1).compareTo((E)k2)
                : comparator.compare((E)k1, (E)k2);
    }

    private void addEntryToEmptyMap(E key) {
        compare(key, key); // type (and possibly null) check
        root = new RedBlackNode<>(key, null);
        size = 1;
        modCount++;
    }
    public E put(E val){
        RedBlackNode<E> t = root;
        if (t == null) {
            addEntryToEmptyMap(val);
            return null;
        }
        int cmp;
        RedBlackNode<E> parent;
        // split comparator and comparable paths
        Comparator<? super E> cpr = comparator;
        if (cpr != null) {
            do {
                parent = t;
                cmp = cpr.compare(val, t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
            } while (t != null);
        } else {
            Objects.requireNonNull(val);
            @SuppressWarnings("unchecked")
            Comparable<? super E> k = (Comparable<? super E>) val;
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else {
                    E oldValue = t.key;
                    if (false || oldValue == null) {
                        t.key = val;
                    }
                    return oldValue;
                }
            } while (t != null);
        }
        addEntry(val, parent, cmp < 0);
        return null;
    }
    private void addEntry(E key, RedBlackNode<E> parent, boolean addToLeft) {
        RedBlackNode<E> e = new RedBlackNode<>(key, parent);
        if (addToLeft)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        size++;
        modCount++;
    }
    final RedBlackNode<E> getEntryUsingComparator(Object key) {
        @SuppressWarnings("unchecked")
        E k = (E) key;
        Comparator<? super E> cpr = comparator;
        if (cpr != null) {
            RedBlackNode<E> p = root;
            while (p != null) {
                int cmp = cpr.compare(k, p.key);
                if (cmp < 0)
                    p = p.left;
                else if (cmp > 0)
                    p = p.right;
                else
                    return p;
            }
        }
        return null;
    }
    final RedBlackNode<E> getEntry(Object key) {
        // Offload comparator-based version for sake of performance
        if (comparator != null)
            return getEntryUsingComparator(key);
        Objects.requireNonNull(key);
        @SuppressWarnings("unchecked")
        Comparable<? super E> k = (Comparable<? super E>) key;
        RedBlackNode<E> p = root;
        while (p != null) {
            int cmp = k.compareTo(p.key);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }
    public E remove(Object key) {
        RedBlackNode<E> p = getEntry(key);
        if (p == null)
            return null;

        E oldValue = p.key;
        deleteEntry(p);
        return oldValue;
    }
    private void fixAfterDeletion(RedBlackNode<E> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                RedBlackNode<E> sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else { // symmetric
                RedBlackNode<E> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
    }
    static <E> RedBlackNode<E> successor(RedBlackNode<E> t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            RedBlackNode<E> p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            RedBlackNode<E> p = t.parent;
            RedBlackNode<E> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    static <E> RedBlackNode<E> predecessor(RedBlackNode<E> t) {
        if (t == null)
            return null;
        else if (t.left != null) {
            RedBlackNode<E> p = t.left;
            while (p.right != null)
                p = p.right;
            return p;
        } else {
            RedBlackNode<E> p = t.parent;
            RedBlackNode<E> ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    private void deleteEntry(RedBlackNode<E> p) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            RedBlackNode<E> s = successor(p);
            p.key = s.key;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        RedBlackNode<E> replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }
    final RedBlackNode<E> getFirstEntry() {
        RedBlackNode<E> p = root;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }
    @Override
    public Iterator<E> iterator() {
        return new Itr(getFirstEntry());
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return new IterableImpl();
    }

    private class Itr implements Iterator<E>{
        RedBlackNode<E> next;
        RedBlackNode<E> lastReturned;
        int expectedModCount;

        Itr(RedBlackNode<E> first) {
            expectedModCount = modCount;
            lastReturned = null;
            next = first;
        }

        public final boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            return nextEntry().key;
        }

        final RedBlackNode<E> nextEntry() {
            RedBlackNode<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final RedBlackNode<E> prevEntry() {
            RedBlackNode<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = predecessor(e);
            lastReturned = e;
            return e;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            // deleted entries are replaced by their successors
            if (lastReturned.left != null && lastReturned.right != null)
                next = lastReturned;
            deleteEntry(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }
    private class ItrNode implements Iterator<Node<E>>{
        RedBlackNode<E> next;
        RedBlackNode<E> lastReturned;
        int expectedModCount;

        ItrNode(RedBlackNode<E> first) {
            expectedModCount = modCount;
            lastReturned = null;
            next = first;
        }

        public final boolean hasNext() {
            return next != null;
        }

        @Override
        public Node<E> next() {
            return nextEntry();
        }

        final Node<E> nextEntry() {
            RedBlackNode<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final Node<E> prevEntry() {
            RedBlackNode<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = predecessor(e);
            lastReturned = e;
            return e;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            // deleted entries are replaced by their successors
            if (lastReturned.left != null && lastReturned.right != null)
                next = lastReturned;
            deleteEntry(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }
    private class IterableImpl implements Iterable<Node<E>>{

        @Override
        public Iterator<Node<E>> iterator() {
            return new ItrNode(getFirstEntry());
        }

    }

    static class RedBlackNode<E> extends NodeImpl<E>{
        RedBlackNode<E> parent;
        RedBlackNode<E> left;
        RedBlackNode<E> right;
        E key;
        boolean color=BLACK;

        public RedBlackNode(E key,RedBlackNode<E> parent) {
            this.parent = parent;
            this.key=key;
        }
    }
}
