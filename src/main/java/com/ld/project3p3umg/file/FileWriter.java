package com.ld.project3p3umg.file;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class FileWriter {

    private static final String FILE_NAME = "bitacora.txt";

    public static void writeFile(String content){
        Path path = Paths.get(FILE_NAME);
        byte[] strToBytes = content.getBytes();

        try {
            Files.write(path, strToBytes, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("error writing file",e);
        }
    }



}
