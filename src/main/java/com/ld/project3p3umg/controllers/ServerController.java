package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.services.ServerService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author luisdany
 */
@Controller
@Slf4j
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }

//    @GetMapping("/server-add")
//    public String addView(Model model){
//        return "server-add";
//    }

    @PostMapping("/add-server")
    public String addServer(Model model, @ModelAttribute("server") Server server){
        log.info("Adding server");
        String error = serverService.addServer(server);
        if(error.length() > 0){
            model.addAttribute("error", error);
        }
        return "redirect:/server-view";
    }

    @PostMapping("/delete-server")
    public String deleteServer(Model model, @ModelAttribute("server") Server server){
        log.info("Removing server");
        String error = serverService.deleteServer(server);
        if(error.length() > 0){
            model.addAttribute("error", error);
        }
        return "redirect:/server-delete";
    }

}
