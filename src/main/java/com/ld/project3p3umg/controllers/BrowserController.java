package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.services.BrowserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author luisdany
 */
@Controller
@Slf4j
public class BrowserController {

    public final BrowserService browserService;

    public BrowserController(BrowserService browserService) {
        this.browserService = browserService;
    }

    @PostMapping("/search-resource")
    public String searchResource(Model model, @ModelAttribute("value") String value) {
        log.info("Searching {}", value);
        Map<String, Object> mapResult = browserService.searchResource(value);
        if(mapResult != null){
            log.info("map result {}", mapResult.toString());
        }
        return "index";
    }

    @PostMapping("/search-complete")
    public String searchResourceComplete(Model model, @ModelAttribute("value") String value) {
        log.info("Searching {}",value);
        browserService.searchResources(value);
        return "index";
    }

}
