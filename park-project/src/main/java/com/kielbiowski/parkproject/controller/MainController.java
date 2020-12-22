package com.kielbiowski.parkproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = {"/index","/",""})
    public String indexGet(Model model){
        return "index";
    }

}
