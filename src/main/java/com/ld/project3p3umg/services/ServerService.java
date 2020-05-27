package com.ld.project3p3umg.services;

import com.ld.project3p3umg.bootstrap.TreeBootstrap;
import com.ld.project3p3umg.dataStructure.Tree;
import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.draw.TreeDraw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author luisdany
 */
@Slf4j
@Service
public class ServerService {

    private AvlTree<Server> serverTree = new AvlTree<>();
//    private AvlTree<Server> serverTree = TreeBootstrap.getTree();

    public AvlTree<Server> getTree(){
        return serverTree;
    }

    public void setServerTree(AvlTree<Server> serverTree) {
        this.serverTree = serverTree;
    }

    public String addServer(Server server){

        if(server != null){
            log.info("Server to add: {}", server.toString());
            try{
                serverTree.addNode(server);
            }catch (Exception e){
                log.error("Duplicated node! ", e);
                return "Error, node duplicated";
            }

        }
        return "";
    }


    public String deleteServer(Server server){
        if(server != null){
            serverTree.deleteNode(server);
        }
        return "";
    }

}
