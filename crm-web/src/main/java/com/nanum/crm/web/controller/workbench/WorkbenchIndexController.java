package com.nanum.crm.web.controller.workbench;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkbenchIndexController {

    @GetMapping("/workbench")
    public String toIndex(){
        return "workbench/index";
    }
}
