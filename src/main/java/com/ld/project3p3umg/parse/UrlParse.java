package com.ld.project3p3umg.parse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author luisdany
 */
@Component
@Slf4j
public class UrlParse {

    public String getServer(String server) {
        if (server.startsWith("http") || server.startsWith("www")) {
            return getServerName(server.split("/"));
        }
        return null;
    }

    private String getServerName(String[] server) {
        return Arrays.stream(server)
                .filter(s -> s.startsWith("www"))
                .findFirst().orElse(null);
    }

    public String getResourceName(String server) {
        StringBuilder resource = new StringBuilder();
        if (server.startsWith("http")) {
            String[] serverSplit = server.split("/");
            for (int i = 3; i < serverSplit.length; i++) {
                resource.append("/").append(serverSplit[i]);
            }
            log.info("resource {}", resource);
        } else if (server.startsWith("www")) {
            String[] serverSplit = server.split("/");
            for (int i = 1; i < serverSplit.length; i++) {
                resource.append("/").append(serverSplit[i]);
            }
            log.info("resource {}", resource);
        }

        return resource.toString();
    }
}
