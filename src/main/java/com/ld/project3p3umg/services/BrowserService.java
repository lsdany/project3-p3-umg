package com.ld.project3p3umg.services;

import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.dataStructure.hash.HashTable;
import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.domain.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luisdany
 */
@Service
@Slf4j
public class BrowserService {

    private final ServerService serverService;

    public BrowserService(ServerService serverService) {
        this.serverService = serverService;
    }

    public Map<String, Object> searchResource(String serverComplete) {
        Map<String, Object> map = new HashMap<>();
        if (serverComplete != null && serverComplete.length() > 0) {
            AvlTree<Server> tree = serverService.getTree();
            String serverName = getServer(serverComplete);
            Server server = tree.findNode(Server.builder().website(serverName).build());

            if (server == null) {
                log.error("Server does not exist into the tree");
                return null;
            }

            HashTable table = server.getResources();
            if (table != null) {
                String resourceName = parseResource(serverComplete);
                Resource resource = table.search(resourceName);
                if (resource == null) {
                    return null;
                }
                map.put("resource", resource);
            }
            map.put("server", server);
            return map;
        }
        return null;
    }

    public Map<String, Object> searchResources(String searchString) {

        if (searchString != null && searchString.length() > 0) {

            if (searchString.startsWith("http") || searchString.startsWith("www")) {
                log.info("Looking for a server");
                return searchResource(searchString);

            } else if (searchString.startsWith("/")) {
                log.info("Looking for a resource");

            } else {
                log.info("Looking for content");
            }

        }

        return null;
    }


    private String parseResource(String server) {

        StringBuilder resource = new StringBuilder();
        // example: http://www.server.com/datos/archivo.txt
        if (server.startsWith("http")) {
            String[] serverSplit = server.split("/");
            for (int i = 3; i < serverSplit.length; i++) {
                resource.append("/").append(serverSplit[i]);
            }
            System.out.println("resource " + resource);
        } else if (server.startsWith("www")) {
            // example: www.server.com/datos/archivo.txt
            String[] serverSplit = server.split("/");
            for (int i = 1; i < serverSplit.length; i++) {
                resource.append("/").append(serverSplit[i]);
            }
            System.out.println("resource " + resource);
        }

        return resource.toString();
    }

    private String getServer(String server) {
        if (server.startsWith("http")) {
            String[] serverSplit = server.split("/");
            for (String serv : serverSplit) {
                if (serv.startsWith("www")) {
                    return serv;
                }
            }
        } else if (server.startsWith("www")) {
            String[] serverSplit = server.split("/");
            return Arrays.stream(serverSplit)
                    .filter(s -> s.startsWith("www"))
                    .findFirst().orElse(null);
        }
        return null;
    }

}
