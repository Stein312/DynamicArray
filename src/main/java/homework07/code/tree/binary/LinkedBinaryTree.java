package main.java.homework07.code.tree.binary;

import main.java.homework07.code.tree.Node;
import main.java.homework08.search.RedBlackTree;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    protected NodeImpl<E> root;

    // nonpublic utility

    /**
     * Validates the node is an instance of supported {@link NodeImpl} type and casts to it
     * @param n node
     * @return casted {@link NodeImpl} node
     * @throws IllegalArgumentException
     */
    protected NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        if(n instanceof NodeImpl){
            return (NodeImpl<E>) n;
        }
        else throw new IllegalArgumentException();

    }

    // update methods supported by this class

    /**
     * Places element <i>e</i> at the root of an empty tree and returns its new {@link Node}
     *
     * @param e element
     * @return created root
     * @throws IllegalStateException if tree is not empty
     */
    public Node<E> addRoot(E e) throws IllegalStateException {
        if(root==null){
        NodeImpl<E> p=new NodeImpl<>();
        p.value=e;
        root=p;
        return root;
        }
        else throw new IllegalStateException("Tree is not null");
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

    /**
     * Creates a new left child of {@link Node} <i>n</i> storing element <i>e</i>
     *
     * @param n node
     * @param e    element
     * @return created node
     * @throws IllegalArgumentException if <i>node</i> already has a left child
     */
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> r=validate(n);
        NodeImpl<E> p=new NodeImpl<>();
        if(searchNode(e)==null){
        if(r.left==null){
            Comparable<? super E> k=(Comparable<? super E>) e;
            int cmpL = k.compareTo(r.getElement());
                if(cmpL<0){
                    p.value=e;
                    p.parent=(NodeImpl<E>) n;
                    r.left=p;
                }
                else if(cmpL>0) throw new IllegalArgumentException("The Left child is large");
                else throw new IllegalArgumentException("The Left child equals parent");
            return p;
        }
        else throw new IllegalArgumentException("Node already has a left child");}
        else throw new IllegalArgumentException("This node already exist");
    }

    /**
     * Creates a new right child of {@link Node} <i>n</i> storing element <i>e</i>
     *
     * @param n node
     * @param e    element
     * @return created node
     * @throws IllegalArgumentException if <i>n</i> already has a right child
     */
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> r=validate(n);
        if(searchNode(e)==null){
        if(r.right==null){
            NodeImpl<E> p=new NodeImpl<>();
            Comparable<? super E> k=(Comparable<? super E>) e;
            int cmpL = k.compareTo(r.getElement());
            if(cmpL>0){
                p.value=e;
                p.parent=(NodeImpl<E>) n;
                r.right=p;
            }
            else if(cmpL<0) throw new IllegalArgumentException("The Right child is smaller");
            else throw new IllegalArgumentException("The Right child equals parent");
            return p;
        }
        else throw new IllegalArgumentException("Node already has a right child");}
        else throw new IllegalArgumentException("This node already exist");
    }


    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @param e element
     * @return replace element
     * @throws IllegalArgumentException
     */
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        Comparable<? super E> k=(Comparable<? super E>) e;
        NodeImpl<E> r=validate(n);
            int cmpL = k.compareTo(r.left.getElement());
            int cmpR=k.compareTo(r.right.getElement());
            if(cmpR<0){
                if(cmpL>0)
                    r.value=e;
                else throw new IllegalArgumentException("The Left child is large");
            }
            else throw new IllegalArgumentException("The right child is large");

        return r.value;

    }

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @return replace element
     * @throws IllegalArgumentException
     */
    public E remove(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> r=validate(n);
        Comparable<? super E> k=(Comparable<? super E>) r.getElement();
        if(r.parent!=null){
            int cmp=k.compareTo(r.parent.getElement());
            if(cmp>0){
                r.parent.right=r.right;
            }
            else {
                r.parent.left=r.right;
            }
            NodeImpl<E> right=r.right;
            NodeImpl<E> left=r.left;
            if(left!=null){
            while(right.left!=null){
                right=right.left;
            }
            right.left=left;
            }
        }
        E val=r.value;
        r=new NodeImpl<>();
        return val;
    }

    // {@link Tree} and {@link BinaryTree} implementations

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> r=validate(p);
        return (p == null) ? null : r.left;

    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> r=validate(p);
        return r.right;
    }

    @Override
    public Node<E> root() {
        while(root.getParent()!=null)
            root=root.parent;
        return root;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> r=validate(n);
        return r.parent;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return null;
    }

    protected static class NodeImpl<E> implements Node<E> {
        E value;
        public NodeImpl<E> parent;
        public NodeImpl<E> left;
        public NodeImpl<E> right;
        @Override
        public E getElement() {
            return value;
        }
        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public NodeImpl<E> getParent() {
            return parent;
        }

        public void setParent(NodeImpl<E> parent) {
            this.parent = parent;
        }

        public NodeImpl<E> getLeft() {
            return left;
        }

        public void setLeft(NodeImpl<E> left) {
            this.left = left;
        }

        public NodeImpl<E> getRight() {
            return right;
        }

        public void setRight(NodeImpl<E> right) {
            this.right = right;
        }
    }

}