package com.ld.project3p3umg.bootstrap;

import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.domain.Server;

public class TreeBootstrap {

    public static AvlTree<Server> getTree(){

        try {

            Server s1 = new Server("minfin.gob");
            Server s2 = new Server("catastro.gob");
            Server s3 = new Server("pollocampero.com");
            Server s4 = new Server("amazon.com");
            Server s5 = new Server("mcdonalds.com");
            Server s6 = new Server("olx.com");
            Server s7 = new Server("umg.edu");

            AvlTree<Server> tree = new AvlTree<>();
            tree.addNode(s1);
            tree.addNode(s2);
            tree.addNode(s3);
            tree.addNode(s4);
            tree.addNode(s5);
            tree.addNode(s6);
            tree.addNode(s7);
            return tree;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
