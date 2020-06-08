package com.ld.project3p3umg.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author luisdany
 */
@Getter @Setter
public class Visit implements Comparable<Visit>{

    private String ip;
    private LocalDateTime date;
    private String webSite;
    private int count;

    @Override
    public int compareTo(Visit o) {
        return o.getWebSite().compareToIgnoreCase(webSite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return webSite.equals(visit.webSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webSite);
    }
}
