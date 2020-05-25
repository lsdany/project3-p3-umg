package com.ld.project3p3umg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author luisdany
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
public class ServerJson {

    private String company;
    private String website;
    private ResourcesJson[] resources;

}
