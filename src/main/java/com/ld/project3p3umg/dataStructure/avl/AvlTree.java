package com.ld.project3p3umg.dataStructure.avl;

import com.ld.project3p3umg.dataStructure.Tree;
import com.ld.project3p3umg.domain.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luisdany
 * @param <T>
 */
@Slf4j
public class AvlTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void addNode(T element) throws Exception{
        root = insert(root, element);
    }

    @Override
    public void deleteNode(T element) {

    }

    @Override
    public void findNode(T element) {

    }

    private int getBalance(final Node<T> node){
        if(node != null){
            return height(node.getRight()) - height(node.getLeft());
        }else{
            return 0;
        }
    }

    private int height(Node<T> node) {
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


    private Node<T> rRight(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> z = x.getRight();
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

    private Node<T> rLeft(Node<T> y) {
        Node<T> x = y.getRight();
        Node<T> z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> balance(Node<T> z){
        log.info("Balance node");
        updateHeight(z);
        int balance = getBalance(z);
        log.info("Balance: {}", balance);
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


    private void updateHeight(Node<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight()))) ;
    }


    private Node<T> insert(Node<T> root, T value) throws Exception{
        if (root == null) {
            log.info("First node");
            return new Node<T>(value);
        } else if (root.getData().compareTo(value) < 0) {
            log.info("Node to the left");
            Node<T> left = insert(root.getLeft(), value);
            left.setFather(root);
            root.setLeft(left);
        } else if (root.getData().compareTo(value) > 0) {
            log.info("node to the right");
            Node<T> right = insert(root.getRight(), value);
            right.setFather(root);
            root.setRight(right);
        } else {
            log.error("Duplicated value");
            throw new Exception("duplicated value!");
        }
        return balance(root);
    }
}
