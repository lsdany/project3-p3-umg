package com.ld.project3p3umg.pdf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component @Slf4j
public class PdfReader {

    public PdfReader(){

    }

    public File readFile(){
        File file = null;
        try{
            file = new File("project_report.pdf");
        }catch (Exception e){
            log.error("Error reading file");
        }
        return file;
    }

}
