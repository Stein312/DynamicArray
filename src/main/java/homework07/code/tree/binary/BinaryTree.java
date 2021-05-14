package main.java.homework07.code.tree.binary;

import main.java.homework07.code.tree.Node;
import main.java.homework07.code.tree.Tree;

/**
 * An interface for a binary tree, in which each node has at most two children
 *
 * @param <E> element
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     *
     * @param p node
     * @return the node of <i>node</i>'s left child (of null if no child exists)
     * @throws IllegalArgumentException
     */
    Node<E> left(Node<E> p) throws IllegalArgumentException;

    /**
     *
     * @param p node
     * @return the node of <i>node</i>'s right child (of null if no child exists)
     * @throws IllegalArgumentException
     */
    Node<E> right(Node<E> p) throws IllegalArgumentException;

    /**
     *
     * @param p node
     * @return the node of <i>node</i>'s sibling (of null if no sibling exists)
     * @throws IllegalArgumentException
     */
    Node<E> sibling(Node<E> p) throws IllegalArgumentException;

}
