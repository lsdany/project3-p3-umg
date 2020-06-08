package com.ld.project3p3umg.dataStructure.avl;

import com.ld.project3p3umg.dataStructure.Tree;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.file.FileWriter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @author luisdany
 */
@Slf4j
public class AvlTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void addNode(T element) throws Exception {
        this.root = insert(this.root, element);
    }

    @Override
    public void deleteNode(T element) {
        this.root = delete(this.root, element);
    }

    @Override
    public T findNode(T element) {
        return findByServer(root, element);
    }

    private T findByServer(Node<T> node, T element) {
        log.info("findByServer {} {}", node, element);
        if (node != null)
            if (node.getData().compareTo(element) > 0) {
                return findByServer(node.getRight(), element);
            } else if (node.getData().compareTo(element) < 0) {
                return findByServer(node.getLeft(), element);
            } else
                return node.getData();
        else
            return null;
    }

    private int getBalance(final Node<T> node) {
        if (node != null) {
            return height(node.getRight()) - height(node.getLeft());
        } else {
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
        if (x != null) x.setFather(y.getFather());
        x.setRight(y);
        if (z != null) y.setFather(y);
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
              z   T2            T1  z


     */

    private Node<T> rLeft(Node<T> y) {

        Node<T> x = y.getRight();
        Node<T> z = x.getLeft();
        if (x != null) x.setFather(y.getFather());
        x.setLeft(y);
        y.setFather(x);
        if (z != null) z.setFather(y);
        y.setRight(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> balance(Node<T> z) {
        log.info("Balance node");
        updateHeight(z);
        int balance = getBalance(z);
        log.info("Balance: {}", balance);
        if (balance > 1) {
            FileWriter.writeFile("Factor de Equilibrio = " + balance + " ");
            if (height(z.getRight().getRight()) > height(z.getRight().getLeft())) {
                z = rLeft(z);
                FileWriter.writeFile("; Nodos en rotacion = X, Y , Z; Rotacion I \n");
            } else {
                z.setRight(rRight(z.getRight()));
                z = rLeft(z);
                FileWriter.writeFile("; Nodos en rotacion = X, Y , Z; Rotacion I \n");
            }
        } else if (balance < -1) {
            if (height(z.getLeft().getLeft()) > height(z.getLeft().getRight())) {
                z = rRight(z);
                FileWriter.writeFile("; Nodos en rotacion = X, Y , Z; Rotacion D \n");
            } else {
                z.setLeft(rLeft(z.getLeft()));
                z = rRight(z);
                FileWriter.writeFile("; Nodos en rotacion = X, Y , Z; Rotacion D \n");
            }
        }
        return z;
    }


    private void updateHeight(Node<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }


    private Node<T> insert(Node<T> root, T value) throws Exception {
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

    private Node<T> delete(Node<T> node, T element) {
        if (node == null && element != null) {
            return null;
        } else if (node.getData().compareTo(element) < 0) {
            node.setLeft(delete(node.getLeft(), element));
        } else if (node.getData().compareTo(element) > 0) {
            node.setRight(delete(node.getRight(), element));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            } else {
                Node<T> leftChild = mostLeft(node.getRight());
                node.setData(leftChild.getData());
                node.setRight(delete(node.getRight(), node.getData()));
            }
        }
        if (node != null) {
            node = balance(node);
        }
        return node;
    }


    private Node<T> mostLeft(final Node<T> node) {
        Node<T> temp = node;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp;
    }

    public Node<T> getRoot() {
        return root;
    }


    //TODO change this to be usable at the time to search resources or content


}
