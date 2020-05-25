package com.ld.project3p3umg.services;

import com.ld.project3p3umg.dataStructure.Tree;
import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.domain.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author luisdany
 */
@Slf4j
@Service
public class ServerService {

    private AvlTree<Server> serverTree = new AvlTree<>();

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


}
