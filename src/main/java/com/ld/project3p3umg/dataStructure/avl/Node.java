package com.ld.project3p3umg.dataStructure.avl;

import lombok.Getter;
import lombok.Setter;

/**
 * @author luisdany
 * @param <E>
 */

@Getter
@Setter
public class Node<E> {

    private Node<E> right;
    private Node<E> left;
    private Node<E> father;
    private E data;
    private int height;

    public Node(E data){
        this.data = data;
    }
}
