package main.java.homework08.search;


import main.java.homework07.code.tree.Node;
import main.java.homework07.code.tree.binary.LinkedBinaryTree;

import java.util.Comparator;

/**
 * @author Vital Severyn
 * @since 31.07.15
 */
public class BinarySearchTree<E> extends LinkedBinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Method for comparing two values
     *
     * @param val1
     * @param val2
     * @return
     */
    protected int compare(E val1, E val2) {
        // todo
        if(comparator!=null){
        return comparator.compare(val1,val2);}
        else {
            Comparable<? super E> k=(Comparable<? super E>) val1;
            return k.compareTo(val2);
        }
    }

    /**
     * Returns the node in n's subtree by val
     *
      * @param n
     * @param val
     * @return
     */
    public Node<E> treeSearch(Node<E> n, E val) {
        // todo
        NodeImpl<E> p=validate(n);
        while(p.getParent()!=null){
            p=p.getParent();
        }
        while(p!=null){
            int cmp = compare(p.getElement(),val);
            if (cmp < 0)
                p = p.getLeft();
            else if (cmp > 0)
                p = p.getRight();
            else
                return p;
        }
        return null;
    }
}
