package main.java.homework08.search;

import main.java.homework07.code.tree.Node;

import main.java.homework08.search.balanced.BalanceableTree;

import java.util.*;

public class RedBlackTree<E> extends BalanceableTree<E> {
    protected RedBlackNode<E> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private final Comparator<? super E> comparator;
    private int size = 0;
    private int modCount = 0;

    public RedBlackTree() {
        this.comparator = null;
    }

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;

    }

    private static <E> RedBlackNode<E> parentOf(RedBlackNode<E> p) {
        return (p == null ? null : (RedBlackNode<E>) p.getParent());
    }

    private static <E> RedBlackNode<E> leftOf(RedBlackNode<E> p) {
        return (p == null) ? null : (RedBlackNode<E>) p.getLeft();
    }

    private static <E> RedBlackNode<E> rightOf(RedBlackNode<E> p) {
        return (p == null) ? null : (RedBlackNode<E>) p.getRight();
    }

    public static <E> boolean colorOf(RedBlackNode<E> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <E> void setColor(RedBlackNode<E> p, boolean c) {
        if (p != null)
            p.color = c;
    }
    public void printTree(){
        printTree(root);
    }

    private void printTree(RedBlackNode<E> node){
        if (node != null) {

            System.out.print(" "+node.getElement());
            printTree((RedBlackNode<E>) node.getLeft());
            printTree((RedBlackNode<E>) node.getRight());
        }

    }
    static <E> RedBlackNode<E> successor(RedBlackNode<E> t) {
        if (t == null)
            return null;
        else if (t.getRight() != null) {
            RedBlackNode<E> p = (RedBlackNode<E>) t.getRight();
            while (p.getLeft() != null)
                p = (RedBlackNode<E>) p.getLeft();
            return p;
        } else {
            RedBlackNode<E> p = (RedBlackNode<E>) t.getParent();
            RedBlackNode<E> ch = t;
            while (p != null && ch == p.getRight()) {
                ch = p;
                p = (RedBlackNode<E>) p.getParent();
            }
            return p;
        }
    }

    static <E> RedBlackNode<E> predecessor(RedBlackNode<E> t) {
        if (t == null)
            return null;
        else if (t.getLeft() != null) {
            RedBlackNode<E> p = (RedBlackNode<E>) t.getLeft();
            while (p.getRight() != null)
                p = (RedBlackNode<E>) p.getRight();
            return p;
        } else {
            RedBlackNode<E> p = (RedBlackNode<E>) t.getParent();
            RedBlackNode<E> ch = t;
            while (p != null && ch == p.getLeft()) {
                ch = p;
                p = (RedBlackNode<E>) p.getParent();
            }

            return p;
        }
    }

    private void rotateLeft(RedBlackNode<E> p) {
        if (p != null) {
            RedBlackNode<E> r = (RedBlackNode<E>) p.getRight();
            p.setRight(r.getLeft());
            if (r.getLeft() != null)
                r.getLeft().setParent(p);
            r.setParent(p.getParent());
            if (p.getParent() == null)
                root = r;
            else if (p.getParent().getLeft() == p)
                p.getParent().setLeft(r);
            else
                p.getParent().setRight(r);
            r.setLeft(p);
            p.setParent(r);
        }
    }

    private void rotateRight(RedBlackNode<E> p) {
        if (p != null) {
            RedBlackNode<E> l = (RedBlackNode<E>) p.getLeft();
            p.setLeft(l.getRight());
            if (l.getRight() != null) l.getRight().setParent(p);
            l.setParent(p.getParent());
            if (p.getParent() == null)
                root = l;
            else if (p.getParent().getRight() == p)
                p.getParent().setRight(l);
            else p.getParent().setLeft(l);
            l.setRight(p);
            p.setParent(l);
        }
    }

    private void fixAfterInsertion(RedBlackNode<E> x) {
        x.color = RED;

        while (x != null && x != root && colorOf((RedBlackNode<E>) x.getParent()) == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                RedBlackNode<E> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    rotateTwice(x);
                    root= (RedBlackNode<E>) super.root;
                }
            } else {
                RedBlackNode<E> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    rotateTwice(x);
                    root= (RedBlackNode<E>) super.root;
                }
            }
        }
        root.color = BLACK;
    }



    private void addEntryToEmptyMap(E key) {
        compare(key, key); // type (and possibly null) check
        root = new RedBlackNode<>(key, null);
        size = 1;
        modCount++;
    }

    public E put(E val) {
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
                cmp = cpr.compare(val, t.getValue());
                if (cmp < 0)
                    t = (RedBlackNode<E>) t.getLeft();
                else if (cmp > 0)
                    t = (RedBlackNode<E>) t.getRight();
            } while (t != null);
        } else {
            Objects.requireNonNull(val);
            @SuppressWarnings("unchecked")
            Comparable<? super E> k = (Comparable<? super E>) val;
            do {
                parent = t;
                cmp = k.compareTo(t.getValue());
                if (cmp < 0)
                    t = (RedBlackNode<E>) t.getLeft();
                else if (cmp > 0)
                    t = (RedBlackNode<E>) t.getRight();
                else {
                    E oldValue = t.getValue();
                    if (oldValue == null) {
                        t.setValue(val);
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
            parent.setLeft(e);
        else
            parent.setRight(e);
        fixAfterInsertion(e);
        size++;
        modCount++;
    }

    final RedBlackNode<E> getEntryUsingComparator(Object key) {
        @SuppressWarnings("unchecked")
        E k = (E) key;
        Comparator<? super E> cpr = comparator;
        if (cpr != null) {
            RedBlackNode<E> p = (RedBlackNode<E>) root;
            while (p != null) {
                int cmp = cpr.compare(k, p.getValue());
                if (cmp < 0)
                    p = (RedBlackNode<E>) p.getLeft();
                else if (cmp > 0)
                    p = (RedBlackNode<E>) p.getRight();
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
        RedBlackNode<E> p = (RedBlackNode<E>) root;
        while (p != null) {
            int cmp = k.compareTo(p.getValue());
            if (cmp < 0)
                p = (RedBlackNode<E>) p.getLeft();
            else if (cmp > 0)
                p = (RedBlackNode<E>) p.getRight();
            else
                return p;
        }
        return null;
    }

    public E remove(Object key) {
        RedBlackNode<E> p = getEntry(key);
        if (p == null)
            return null;

        E oldValue = p.getValue();
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

                if (colorOf(leftOf(sib)) == BLACK &&
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
                    x = (RedBlackNode<E>) root;
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
                    x = (RedBlackNode<E>) root;
                }
            }
        }

        setColor(x, BLACK);
    }

    private void deleteEntry(RedBlackNode<E> p) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.getLeft() != null && p.getRight() != null) {
            RedBlackNode<E> s = successor(p);
            p.setValue(s.getValue());
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        RedBlackNode<E> replacement = (RedBlackNode<E>) (p.getLeft() != null ? p.getLeft() : p.getRight());

        if (replacement != null) {
            // Link replacement to parent
            replacement.setParent(p.getParent());
            if (p.getParent() == null)
                root = replacement;
            else if (p == p.getParent().getLeft())
                p.getParent().setLeft(replacement);
            else
                p.getParent().setRight(replacement);

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
        RedBlackNode<E> p = (RedBlackNode<E>) root;
        if (p != null)
            while (p.getLeft() != null)
                p = (RedBlackNode<E>) p.getLeft();
        return p;
    }
    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(getFirstEntry());
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return new IterableImpl();
    }

    public static class RedBlackNode<E> extends NodeImpl<E> {




        boolean color = BLACK;

        public RedBlackNode(E key, RedBlackNode<E> parent) {
            this.setParent(parent);
            this.setValue(key);
        }
        public boolean isColor() {
            return color;
        }
        public void setColor(boolean color) {
            this.color = color;
        }

    }

    private class Itr implements Iterator<E> {
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
            return nextEntry().getValue();
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
            if (lastReturned.getLeft() != null && lastReturned.getRight() != null)
                next = lastReturned;
            deleteEntry(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }

    private class ItrNode implements Iterator<Node<E>> {
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
            if (lastReturned.getLeft() != null && lastReturned.getRight() != null)
                next = lastReturned;
            deleteEntry(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }

    private class IterableImpl implements Iterable<Node<E>> {

        @Override
        public Iterator<Node<E>> iterator() {
            return new ItrNode(getFirstEntry());
        }

    }
}
