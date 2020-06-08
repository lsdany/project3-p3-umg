package com.ld.project3p3umg.dataStructure.list;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Node<E> {


    private E data;
    private Node<E> left;
    private Node<E> right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }


}


