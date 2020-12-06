package main.java.homework07.code.tree;

import java.util.Iterator;

/**
 * An abstract base class providing some functionality of the Tree interface
 *
 * @param <E> element
 */
public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal(Node<E> n) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isExternal(Node<E> n) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isRoot(Node<E> n) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     *
     * @return an iterable collection of nodes of the tree in preorder
     */
    public Iterable<Node<E>> preOrder() {
        return null;
    }

    /**
     *
     * @return an iterable collection of nodes of the tree in postorder
     */
    public Iterable<Node<E>> postOrder() {
        return null;
    }

    /**
     *
     * @return an iterable collection of nodes of the tree in breadth-first order
     */
    public Iterable<Node<E>> breadthFirst() {
        return null;
    }

    /**
     * Adapts the iteration produced by {@link Tree#nodes()}
     */
    private class ElementIterator implements Iterator<E> {
        private Iterator<Node<E>> it = nodes().iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {

        }
    }
}
