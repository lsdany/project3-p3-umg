package com.ld.project3p3umg.domain;

import lombok.*;

/**
 * @author luisdany
 */
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Server implements Comparable<Server>{

    private String name;

    @Override
    public int compareTo(Server o) {
        return o.getName().compareToIgnoreCase(this.name);
    }
}
