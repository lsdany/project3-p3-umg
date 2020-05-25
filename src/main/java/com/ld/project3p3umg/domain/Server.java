package com.ld.project3p3umg.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author luisdany
 */
@Getter @Setter @ToString
public class Server implements Comparable<Server>{

    private String name;

    @Override
    public int compareTo(Server o) {
        return o.getName().compareToIgnoreCase(this.name);
    }
}
