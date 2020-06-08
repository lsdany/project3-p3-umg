package com.ld.project3p3umg.services;

import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.dataStructure.avl.Node;
import com.ld.project3p3umg.dataStructure.hash.HashTable;
import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.domain.SearchResult;
import com.ld.project3p3umg.domain.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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
                map.put("error", "Server does not exist into the tree");
                return map;
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
        map.put("error", "Search value is null");
        return map;
    }

    public Map<String, Object> searchResources(String searchString) {

        Map<String, Object> resultMap = new HashMap<>();
        if (searchString != null && searchString.length() > 0) {
            if (searchString.startsWith("http") || searchString.startsWith("www")) {
                log.info("Looking for a server");
                var result = searchResource(searchString);
                if(result != null && !result.containsKey("error")){
                    Server server = (Server) result.get("server");
                    Resource resource = (Resource) result.get("resource");
                    SearchResult searchResult = new SearchResult();
                    searchResult.setResource(resource);
                    searchResult.setServer(server);
                    resultMap.put("resource", Arrays.asList(searchResult));
                }

            } else if (searchString.startsWith("/")) {
                log.info("Looking for a resource");
                List<SearchResult> serverList = searchResourcesByNameInTree(searchString);
                resultMap.put("resource", serverList);
            } else {
                log.info("Looking for content");
                List<SearchResult> resultList = searchResourcesByContentInTree(searchString);
                resultMap.put("resource", resultList);
            }

        }

        return resultMap;
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


    private List<SearchResult> searchResourcesByNameInTree(String value){
        searchResults = new ArrayList<>();
        AvlTree<Server> tree = serverService.getTree();
        if(tree != null && tree.getRoot() != null){
            inOrder(tree.getRoot(), value);
        }
        return searchResults;
    }

    private List<SearchResult> searchResourcesByContentInTree(String value){
        searchResults = new ArrayList<>();
        AvlTree<Server> tree = serverService.getTree();
        if(tree != null && tree.getRoot() != null){
            inOrderContent(tree.getRoot(), value);
        }
        return searchResults;
    }

    private List<SearchResult> searchResults = new ArrayList<>();

    private void inOrder(Node<Server> node, String value) {
        if (node != null) {
            if (node.getLeft() != null)
                inOrder(node.getLeft(), value);
            Resource resource = node.getData().getResources().search(value);
            if(resource != null){
                SearchResult result = new SearchResult();
                result.setServer(node.getData());
                result.setResource(resource);
                searchResults.add(result);
            }
            if (node.getRight() != null)
                inOrder(node.getRight(), value);
        }
    }

    private void inOrderContent(Node<Server> node, String value) {
        if (node != null) {
            if (node.getLeft() != null)
                inOrderContent(node.getLeft(), value);
            List<Resource> resources = node.getData().getResources().searchContent(value);
            if(resources != null){
                for(Resource r : resources){
                    SearchResult result = new SearchResult();
                    result.setServer(node.getData());
                    result.setResource(r);
                    searchResults.add(result);
                }
            }
            if (node.getRight() != null)
                inOrderContent(node.getRight(), value);
        }
    }

}
