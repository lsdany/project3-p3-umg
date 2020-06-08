package com.ld.project3p3umg.dataStructure.list;

import lombok.Getter;

/**
 * @author luisdany
 * @param <E>
 */
public class List<E extends Comparable<E>> {

    @Getter
    private Node<E> start = null;
    private Node<E> end = null;
    private int size = 0;
    private Node<E> actual = null;

    public void add(E element) {

        actual = new Node<>(element);

        if (start == null){
            start = actual;
        }else{
            actual.setRight(end);
            end.setLeft(actual);
            end = actual;
        }
        end = actual;
        size++;

    }

    public E searchById(String id) {
        actual = start;

//        while (actual != null) {
//            if (actual.getData() instanceof User) {
//                if (id.equals(((User) actual.getData()).getUser())) {
//                    return actual.getData();
//                }
//                actual = actual.getNext();
//            } else if (actual.getData() instanceof Student) {
//                if (id.equals(((Student) actual.getData()).getId())) {
//                    return actual.getData();
//                }
//                actual = actual.getNext();
//            } else if (actual.getData() instanceof Course) {
//                if (id.equals(((Course) actual.getData()).getId().toString())) {
//                    return actual.getData();
//                }
//                actual = actual.getBack();
//            } else {
//                return null;
//            }
//
//
//        }
        return null;
    }

    public void delete(E value) {

    }

    public void deleteById(String id) {

    }

    public E search(E value) {
        return null;
    }

    public void print() {

    }

    public int getSize() {
        return size;
    }

}
