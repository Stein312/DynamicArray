package main.java.homework07.code.tree.binary;

import main.java.homework07.code.tree.Node;

import java.util.*;
import java.util.function.Consumer;


/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    NodeImpl<E> root;
    int size;
    int modCount;
    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> r=(NodeImpl<E>) p;
        return r.left;
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> r=(NodeImpl<E>) p;
        return r.right;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    public NodeImpl<E> searchNode(E p){
        Comparable<? super E> k=(Comparable<? super E>) p;
        NodeImpl<E> r=root;
        while(r!=null){
            int cmp = k.compareTo(r.getElement());
            if (cmp < 0)
                r = r.left;
            else if (cmp > 0)
                r = r.right;
            else
                return r;
        }
        return null;
    }
    public E remove(E val){
        NodeImpl<E> p=searchNode(val);
        E e=p==null?null:p.value;
        if(p!=null){
            deleteEntry(p);
        }
        return e;
    }
    public E add(E val){
        Comparable<? super E> k=(Comparable<? super E>)val;
        NodeImpl<E> p=new NodeImpl<>();
        p.value=val;
        if(root == null){
            root=new NodeImpl<>();
            root.value=val;
            return root.value;
        }
        NodeImpl<E> r=root;
        while(r!=null){
            int cmp = k.compareTo(r.getElement());
            if (cmp < 0){
                if(r.left==null) {
                    p.parent=r;
                    r.left = p;
                    return r.left.getElement();
                }
                r = r.left;
            }
            else if (cmp > 0){
                if(r.right==null) {
                    p.parent=r;
                    r.right = p;
                    return r.right.getElement();
                }
                r = r.right;
            }
            else
                return r.getElement();
        }
        size++;
        modCount++;
        return val;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> r=searchNode(n.getElement());
        return r.parent;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {

        return new Itr(getFirstEntry());
    }
    final NodeImpl<E> getFirstEntry() {
        NodeImpl<E> p = root;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return new IterableImpl();
    }
    static <E> NodeImpl<E> successor(NodeImpl<E> t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            NodeImpl<E> p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            NodeImpl<E> p = t.parent;
            NodeImpl<E> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    private void deleteEntry(NodeImpl<E> p) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            NodeImpl<E> s = successor(p);

            p.value = s.value;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
         NodeImpl<E> replacement = (p.left != null ? p.left : p.right);

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

        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }
    private class IterableImpl implements Iterable<Node<E>>{

        @Override
        public Iterator<Node<E>> iterator() {
            return new ItrNode(getFirstEntry());
        }

    }
    static <E> NodeImpl<E>  predecessor(NodeImpl<E>  t) {
        if (t == null)
            return null;
        else if (t.left != null) {
            NodeImpl<E>  p = t.left;
            while (p.right != null)
                p = p.right;
            return p;
        } else {
            NodeImpl<E>  p = t.parent;
            NodeImpl<E>  ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    private class Itr implements Iterator<E>{
        NodeImpl<E> next;
        NodeImpl<E> lastReturned;
        int expectedModCount;

        Itr(NodeImpl<E> first) {
            expectedModCount = modCount;
            lastReturned = null;
            next = first;
        }

        public final boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            return nextEntry().value;
        }

        final NodeImpl<E> nextEntry() {
            NodeImpl<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final NodeImpl<E> prevEntry() {
            NodeImpl<E> e = next;
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
        NodeImpl<E> next;
        NodeImpl<E> lastReturned;
        int expectedModCount;

        ItrNode(NodeImpl<E> first) {
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
            NodeImpl<E> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final Node<E> prevEntry() {
            NodeImpl<E> e = next;
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
    protected static class NodeImpl<E> implements Node<E> {
        E value;
        NodeImpl<E> parent;
        NodeImpl<E> left;
        NodeImpl<E> right;


        @Override
        public E getElement() {
            return value;
        }

    }
}
