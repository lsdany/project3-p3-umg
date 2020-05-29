package com.ld.project3p3umg.domain;

/**
 * @author luisdany
 */
public interface CustomCompare<T> {

    int compareResourceName(String v);

    int compareResourceContent(String v);
}
