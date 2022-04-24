package com.nanum.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController{

    @GetMapping("/")
    public String toIndex() {
        return "index";
    }
}
