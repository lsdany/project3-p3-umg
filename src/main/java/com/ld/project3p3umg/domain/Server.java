package com.ld.project3p3umg.domain;

import com.ld.project3p3umg.dataStructure.hash.HashTable;
import lombok.*;

/**
 * @author luisdany
 */
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
public class Server implements Comparable<Server>{

    private String website;
    private String company;
    private HashTable resources;

//    @Override
//    public int compareResourceName(String name) {
//        Resource resource = resources.search(name);
//        if(resource != null){
//            return resource.getName().compareToIgnoreCase(name);
//        }
//        return -1;
//    }
//
//    @Override
//    public int compareResourceContent(String content) {
//        Resource resource = resources.search(content);
//        if(resource != null){
//            return resource.getName().compareToIgnoreCase(content);
//        }
//        return -1;
//    }

    @Override
    public int compareTo(Server o) {
        return o.getWebsite().compareToIgnoreCase(this.website);
    }
}
