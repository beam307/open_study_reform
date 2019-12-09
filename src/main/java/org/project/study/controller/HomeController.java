package org.project.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/**/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
