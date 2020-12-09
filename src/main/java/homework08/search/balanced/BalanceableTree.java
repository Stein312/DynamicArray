package main.java.homework08.search.balanced;

import main.java.homework07.code.tree.Node;
import main.java.homework08.search.BinarySearchTree;
import main.java.homework08.search.RedBlackTree;

public class BalanceableTree<E> extends BinarySearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    /**
     * Relinks a parent with child node
     */
    private void relink(NodeImpl<E> parent, NodeImpl<E> child, boolean makeLeftChild) {
        // todo
        child.setParent(parent);
        if (makeLeftChild) {
            parent.setLeft(child);
            return;
        }
        int cmp = compare(parent.getElement(), child.getElement());
        if (cmp > 0) {
            parent.setLeft(child);

        } else if (cmp < 0) {
            parent.setRight(child);
        }

    }

    /**
     * Rotates n with parent.
     */
    public void rotate(Node<E> n) {
        // todo
        NodeImpl<E> child = validate(n);
        NodeImpl<E> parent = child.getParent();
        if(parent.getLeft()==child) rotateRight(child);
        else rotateLeft(child);

    }

    /**
     * Performs a left-right/right-left rotations.
     */
    public Node<E> rotateTwice(Node<E> n) {
        // todo
        if(n instanceof RedBlackTree.RedBlackNode){
            if (parent(n) == left(parent(parent(n)))) {
                RedBlackTree.RedBlackNode<E> y = (RedBlackTree.RedBlackNode<E>) right(parent(parent(n)));
                if(RedBlackTree.colorOf(y)==BLACK){
                    if(n == right(parent(n))){
                        n=parent(n);
                        rotate(right(n));
                    }
                    ((RedBlackTree.RedBlackNode)parent(n)).setColor(BLACK);
                    ((RedBlackTree.RedBlackNode)parent(parent(n))).setColor(RED);
                    rotate(left(parent(parent(n))));
                }
            }
            else {
                RedBlackTree.RedBlackNode<E> y = (RedBlackTree.RedBlackNode<E>) left(parent(parent(n)));

                if(RedBlackTree.colorOf(y)==BLACK){
                    if (n == left(parent(n))) {
                        n = parent(n);
                        rotate(left(n));
                    }
                    ((RedBlackTree.RedBlackNode)parent(n)).setColor(BLACK);
                    ((RedBlackTree.RedBlackNode)parent(parent(n))).setColor(RED);
                    rotate(right(parent(parent(n))));
                }
            }

        }
        return n;
    }

    private void rotateLeft(NodeImpl<E> r) {
        NodeImpl<E> p = r.getParent();
        if (p != null) {
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

    private void rotateRight(NodeImpl<E> l) {
        NodeImpl<E> p = l.getParent();
        if (p != null) {
            p.setLeft(l.getRight());
            if (l.getRight() != null)
                l.getRight().setParent(p);
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

}
