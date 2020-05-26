package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.services.IndexService;
import com.ld.project3p3umg.services.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luisdany
 */
@Slf4j
@Controller
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService){
        this.indexService = indexService;
    }

    @GetMapping({"/index", "", "/"})
    public String indexView(Model model){
        return "index";
    }

    @GetMapping("/server-view")
    public String viewServerAdd(Model model){
        return "server-add";
    }

    @GetMapping("/server-delete")
    public String viewServerDelete(Model model){ return "server-delete";}

    @GetMapping("/view-tree")
    public String viewTree(Model model){
        image = indexService.generateTreeImage();
        log.info("image: {}",image);

        return "redirect:/tree";
    }

    String image;

    @GetMapping("/tree")
    public String viewTreeRed(Model model){
        model.addAttribute("treeImage", image);
        return "tree";
    }

    @GetMapping("/upload")
    public String viewUpload(Model model){return "upload";}

}
