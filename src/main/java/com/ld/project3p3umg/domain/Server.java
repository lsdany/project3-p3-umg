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

    @Override
    public int compareTo(Server o) {
        return o.getWebsite().compareToIgnoreCase(this.website);
    }
}
