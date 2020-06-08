package com.ld.project3p3umg.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author luisdany
 */
@Getter @Setter @ToString
public class SearchResult {
    private Server server;
    private Resource resource;
    private List<Resource> resources;

    public String getValues(){
        return server.getWebsite().concat(resource.getName()).replace("/","-").replace(".", "_");
    }
}
