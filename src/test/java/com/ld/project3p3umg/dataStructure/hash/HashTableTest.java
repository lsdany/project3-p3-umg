package com.ld.project3p3umg.dataStructure.hash;

import com.ld.project3p3umg.domain.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    HashTable hash;

    @BeforeEach
    void setUp() {
        hash = new HashTable(20);


        Resource r1 = Resource.builder().name("index.html").content("content").build();
        Resource r2 = Resource.builder().name("/options/Harrington.txt").content("content").build();
        Resource r3 = Resource.builder().name("/credits/Rosalie.txt").content("content").build();
        Resource r4 = Resource.builder().name("/menu/Norton.css").content("content").build();
        Resource r5 = Resource.builder().name("/credits/Waller.html").content("content").build();
        Resource r6 = Resource.builder().name("i/context/Elinor.html").content("content").build();
        Resource r7 = Resource.builder().name("/info/Levy.css").content("content").build();
        Resource r8 = Resource.builder().name("/context/Sheppard.txt").content("content").build();
        Resource r9 = Resource.builder().name("/context/Mcintyre.html").content("content").build();
        Resource r10 = Resource.builder().name("/category/Long.txt").content("content").build();

        hash.add(r1);
        hash.add(r2);
        hash.add(r3);
        hash.add(r4);
        hash.add(r5);
        hash.add(r6);
        hash.add(r7);
        hash.add(r8);
        hash.add(r9);
        hash.add(r10);
    }

    @Test
    void add() {

    }

    @Test
    void search(){
        Resource r = hash.search("/category/Long.txt");
        System.out.println("object "+ r.toString());
    }

    @Test
    void search2(){
        Resource r = hash.search("/category/Long2.txt");
        System.out.println("object "+ r);
    }

    @Test
    void delete(){
        hash.delete("/category/Long.txt");
        System.out.println("tree");
    }

    @Test
    void delete2(){
        hash.delete("/category/Long2.txt");
        System.out.println("tree2");
    }
}