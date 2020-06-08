package com.ld.project3p3umg.services;

import com.ld.project3p3umg.dataStructure.avl.AvlTree;
import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.parse.UrlParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author luisdany
 */
@Service
@Slf4j
public class ResourceService {

    private final ServerService serverService;
    private final UrlParse urlParse;

    public ResourceService(ServerService serverService, UrlParse urlParse) {
        this.serverService = serverService;
        this.urlParse = urlParse;
    }

    public String addResource(Resource resource, MultipartFile file){
        if(resource != null && file !=null){
            if(resource.getName() != null && resource.getName().length() > 0){
                String serverName = resource.getName();
                AvlTree<Server> tree = serverService.getTree();
                if(tree != null && tree.getRoot() != null){
                    Server server = tree.findNode(Server.builder().website(serverName).build());
                    if(server != null){
                        String content = getFileContent(file);
                        Resource resourceToAdd = Resource.builder()
                                .name(resource.getContent().concat(getFileName(file))).content(getFileContent(file)).build();
                        server.getResources().add(resourceToAdd);

                    }

                }else{
                    log.error("Node does not exist {}", resource.getName());
                }

            }


        }
        return "";
    }

    private String getFileName(MultipartFile file){
        return file.getResource().getFilename();
    }

    private String getFileContent(MultipartFile file) {
        try {
            byte[] content = file.getBytes();
            return new String(content);
        } catch (IOException e) {
            log.error("error getting file",e);
        }
        return "";
    }

    public void deleteResource(String name) {
        if(name != null && name.length() > 0){
            String serverName = urlParse.getServer(name);
            AvlTree<Server> tree = serverService.getTree();
            if(tree != null && tree.getRoot() != null){
                Server server = tree.findNode(Server.builder().website(serverName).build());
                if(server != null){
                    String resourceName = urlParse.getResourceName(name);
                    server.getResources().delete(resourceName);
                }else{
                    log.error("Server does not exist!");
                }
            }else{
                log.error("Empty tree");
            }
        }
    }


}
