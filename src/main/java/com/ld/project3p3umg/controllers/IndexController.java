package com.ld.project3p3umg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luisdany
 */

@Controller
public class IndexController {

    @GetMapping({"/index", "", "/"})
    public String indexView(Model model){
        return "index";
    }



}
