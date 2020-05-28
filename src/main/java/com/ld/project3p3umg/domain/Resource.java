package com.ld.project3p3umg.domain;

import lombok.*;

import java.util.Objects;

/**
 * @author luisdany
 */
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Resource {

    private String name;
    private String content;
    public static int size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return this.getName().equalsIgnoreCase(o.toString());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
