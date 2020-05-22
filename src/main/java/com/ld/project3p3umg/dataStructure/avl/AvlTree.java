package com.ld.project3p3umg.dataStructure.avl;

import com.ld.project3p3umg.dataStructure.Tree;

/**
 * @author luisdany
 * @param <E>
 */
public class AvlTree<E> implements Tree<E> {

    private Node<E> root;

    @Override
    public void addNode(E element) {
        insert(root, element);
    }

    @Override
    public void deleteNode(E element) {

    }

    @Override
    public void findNode(E element) {

    }

    private int getBalance(final Node<E> node){
        if(node != null){
            return height(node.getRight()) - height(node.getLeft());
        }else{
            return 0;
        }
    }

    private int height(Node<E> node) {
        if (node == null)
            return -1;
        else return node.getHeight();
    }

    /*

              y                     x
             / \                   / \
            x   T2 Right --->     T1  y
           / \                       / \
          T1  z                     z  T2


     */


    private Node<E> rRight(Node<E> y) {
        Node<E> x = y.getLeft();
        Node<E> z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }


        /*

              y                     x
             / \                   / \
            T1  x    Left --->    y  T2
               / \               / \
              z   T2            T2  z


     */

    private Node<E> rLeft(Node<E> y) {
        Node<E> x = y.getRight();
        Node<E> z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<E> balance(Node<E> z){
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.getRight().getRight()) > height(z.getRight().getLeft())) {
                z = rLeft(z);
            } else {
                z.setRight(rRight(z.getRight()));
                z = rLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.getLeft().getLeft()) > height(z.getLeft().getRight()))
                z = rRight(z);
            else {
                z.setLeft(rLeft(z.getLeft())) ;
                z = rRight(z);
            }
        }
        return z;
    }


    private void updateHeight(Node<E> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight()))) ;
    }


    private Node<E> insert(Node<E> root, E value) {
        if (root == null) {
            return new Node<E>(value);
        } else if (node.key > key) {
            root.getLeft() = insert(root.getLeft(), key);
        } else if (node.key < key) {
            root.setRight(insert(root.getRight(), key));
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return balance(root);
    }
}
