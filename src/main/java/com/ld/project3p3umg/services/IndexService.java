package com.ld.project3p3umg.services;

import com.ld.project3p3umg.draw.TreeDraw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author luisdany
 */
@Slf4j
@Service
public class IndexService {

    private final TreeDraw treeDraw;

    public IndexService(TreeDraw treeDraw){
        this.treeDraw = treeDraw;
    }

    public String generateTreeImage() {
        try{
            return treeDraw.draw();
        }catch (IOException e){
            log.error("There has been a problem generating the image",e);
        }
        return "";
    }
}
