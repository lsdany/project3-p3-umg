package com.ld.project3p3umg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author luisdany
 */
@Controller
public class BrowserController {

    @PostMapping("/specific-search")
    public String specificSearch(Model model, String value){

        return null;
    }

    @PostMapping("/search")
    public String search(Model model, String value){

        return null;
    }


    @RequestMapping("/search/{value}")
    public String viewCoursesList(Model model, @PathVariable String value) {
        System.out.println(value);
        return "index";
    }

}
