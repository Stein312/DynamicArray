package main.java.homework08.search.balanced;

import main.java.homework07.code.tree.Node;
import main.java.homework07.code.tree.binary.LinkedBinaryTree;
import main.java.homework08.search.BinarySearchTree;

import java.util.TreeMap;

public class BalanceableTree<E> extends BinarySearchTree<E> {

    /**
     * Relinks a parent with child node
     */
    private void relink(NodeImpl<E> parent, NodeImpl<E> child, boolean makeLeftChild) {
        // todo
        child.setParent(parent);
        if(makeLeftChild){
            parent.setLeft(child);
            return;
        }
        int cmp=compare(parent.getElement(),child.getElement());
        if(cmp>0){
            parent.setLeft(child);

        }
        else if(cmp<0){
            parent.setRight(child);
        }

    }

    /**
     * Rotates n with parent.
     */
    public void rotate(Node<E> n) {
        // todo
        NodeImpl<E> p=validate(n);
        NodeImpl<E> parent=p.getParent();
        parent.setParent(p);
        if(parent!=null){
            p.setParent(parent.getParent());
            int cmp=compare(p.getElement(),parent.getElement());
            if(cmp>0){

                parent.setRight(null);
                p.setLeft(parent);
            }
            if(cmp<0){
                parent.setLeft(null);
                p.setRight(parent);
            }
        }

    }

    /**
     * Performs a left-right/right-left rotations.
     */
    public Node<E> rotateTwice(Node<E> n) {
        // todo
        return null;
    }
    private void rotateLeft(NodeImpl<E> p) {
        if (p != null) {
            NodeImpl<E> r = p.getRight();
            p.setRight(r.getLeft());
            if (r.getLeft() != null)
                r.getLeft().setParent(p);
            r.setParent(p.getParent());
           if (p.getParent().getLeft() == p)
                p.getParent().setLeft(r);
            else
                p.getParent().setRight(r);
            r.setLeft(p);
            p.setParent(r);
        }
    }

}
