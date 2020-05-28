package com.ld.project3p3umg.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.dataStructure.hash.HashTable;
import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.domain.ResourcesJson;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.domain.ServerJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class JsonParse {

    private String fileContent;
    private final JsonFile jsonFile ;

    public JsonParse(JsonFile jsonFile){
        this.jsonFile = jsonFile;
    }


    public AvlTree<Server> parseContent(String path){
        this.fileContent = jsonFile.readFile(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ServerJson[] serverJsons = mapper.readValue(fileContent, ServerJson[].class);
            return getServers(serverJsons);
        } catch (JsonProcessingException e) {
            log.error("error parsing json file ", e);
        }
        return null;
    }


    public AvlTree<Server> parseContent(InputStream inputStream){
        this.fileContent = jsonFile.readFromStream(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ServerJson[] serverJsons = mapper.readValue(fileContent, ServerJson[].class);
            return getServers(serverJsons);
        } catch (JsonProcessingException e) {
            log.error("error parsing json file ", e);
        }
        return null;
    }


    private AvlTree<Server> getServers(ServerJson[] serverJsons){
        AvlTree<Server> tree = new AvlTree<>();
        for(ServerJson s : serverJsons){
            ResourcesJson[] resourcesJsons = s.getResources();
            HashTable table = new HashTable(50);
            for(ResourcesJson r : resourcesJsons){
                Resource resource = Resource.builder().name(r.getName()).content(r.getContent()).build();
                table.add(resource);
            }
            Server server = Server.builder().website(s.getWebsite()).company(s.getCompany()).resources(table).build();
            try {
                tree.addNode(server);
            } catch (Exception e) {
                log.error("Error adding server {}",server.toString(), e);
            }
        }
        return tree;
    }

}
