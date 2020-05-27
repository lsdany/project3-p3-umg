package com.ld.project3p3umg.services;

import com.ld.project3p3umg.file.JsonParse;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author luisdany
 */
@Service
public class UploadService {

    private final JsonParse jsonParse;
    private final ServerService serverService;

    public UploadService(JsonParse jsonParse, ServerService serverService) {
        this.jsonParse = jsonParse;
        this.serverService = serverService;
    }

    public void readJsonFile(InputStream inputStream){
        serverService.setServerTree(jsonParse.parseContent(inputStream));
    }

}
