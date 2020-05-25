package com.ld.project3p3umg.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author luisdany
 */
@Component
@Slf4j
public class JsonFile {

    public String readFile(String path) {
        log.info("executing readFile with path {}", path);
        String fileContent = "";
        try {
            fileContent = Files.readString(Paths.get(path));
        } catch (IOException io) {
            log.error("Ha ocurrido un error cargando el archivo", io);
        }
        return fileContent;
    }

    public String readFromStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }catch (IOException io){
            log.error("Error at readFromStream", io);
        }
        return sb.toString();
    }

}
