package com.ld.project3p3umg.dataStructure;

/**
 * @author luisdany pernillo
 */
public interface Tree<E> {

    void addNode(E element) throws Exception;

    void deleteNode(E element);

    E findNode(E element);

}
