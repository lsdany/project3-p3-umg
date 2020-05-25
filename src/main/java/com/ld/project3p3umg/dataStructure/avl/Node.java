package com.ld.project3p3umg.dataStructure.avl;

import com.ld.project3p3umg.domain.Server;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luisdany

 */

@Getter
@Setter
public class Node<T extends Comparable<T>> {

    private Node<T> right;
    private Node<T> left;
    private Node<T> father;
    private T data;
    private int height;

    public Node(T data){
        this.data = data;
    }
}
