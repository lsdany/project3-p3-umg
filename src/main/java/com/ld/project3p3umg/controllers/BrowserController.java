package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.domain.SearchResult;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.services.BrowserService;
import com.ld.project3p3umg.services.ListReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luisdany
 */
@Controller
@Slf4j
public class BrowserController {

    private final BrowserService browserService;
    private final ListReportService listReportService;

    public BrowserController(BrowserService browserService, ListReportService listReportService) {
        this.browserService = browserService;
        this.listReportService = listReportService;
    }

    private String getCorrectUrl(String result){
        if(result != null){
            return result.replace("_" , ".").replace("-","/");
        }
        return null;
    }

    @GetMapping("/search/{result}")
    public String search(Model model, @PathVariable("result")  String result) {
        log.info("Searching ");

        String url = getCorrectUrl(result);
        if(url != null){
            Map<String, Object> mapResult = browserService.searchResource(url);
            if (mapResult != null && !mapResult.containsKey("error")) {
                log.info("map result {}", mapResult.toString());
                Resource resource = (Resource) mapResult.get("resource");
                model.addAttribute("resource", resource);
                Server server = (Server) mapResult.get("server");
                model.addAttribute("server", server);

                SearchResult searchResult = new SearchResult();
                searchResult.setServer(server);
                searchResult.setResource(resource);
                listReportService.addToList(searchResult);
            }
        }

        return "browser";
    }

    @PostMapping("/search-resource")
    public String searchResource(Model model, @ModelAttribute("value") String value) {
        log.info("Searching {}", value);
        if(value != null && value.length() > 0){
            Map<String, Object> mapResult = browserService.searchResource(value);
            if (mapResult != null && !mapResult.containsKey("error")) {
                log.info("map result {}", mapResult.toString());
                Resource resource = (Resource) mapResult.get("resource");
                model.addAttribute("resource", resource);
                Server server = (Server) mapResult.get("server");
                model.addAttribute("server", server);
                SearchResult result = new SearchResult();
                result.setServer(server);
                result.setResource(resource);
                listReportService.addToList(result);
                return "browser";
            }
        }
        return "index";
    }

    @PostMapping("/search-complete")
    public String searchResourceComplete(Model model, @ModelAttribute("value") String value) {
        log.info("Searching {}", value);
        Map<String, Object> mapResult = browserService.searchResources(value);
        if (mapResult != null && !mapResult.containsKey("error")) {
            log.info("map result {}", mapResult.toString());
            List<SearchResult> searchResultList = new ArrayList<>();

            if (mapResult.get("resource") instanceof Resource) {
                searchResultList.add((SearchResult) mapResult.get("resource"));
            } else if (mapResult.get("resource") instanceof List) {
                searchResultList = (List<SearchResult>) mapResult.get("resource");
            }
            model.addAttribute("resultList", searchResultList);
        }
        return "browser-list";
    }

}
