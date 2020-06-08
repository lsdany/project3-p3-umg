package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.domain.Resource;
import com.ld.project3p3umg.services.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author luisdany
 */
@Slf4j
@Controller
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/add-resource")
    public String addResource(Model model, @ModelAttribute("resource")Resource resource, @RequestParam("file") MultipartFile file) {
        log.info("resource: {}", resource.toString());
        resourceService.addResource(resource, file);
        return "redirect:/resource-add-view";
    }

    @PostMapping("/delete-resource")
    public String deleteResource(Model model, @ModelAttribute("name") String name) {
        log.info("Resource to delete: {}", name);
        resourceService.deleteResource(name);
        return "redirect:/resource-delete-view";
    }

}
