package com.nanum.crm.web.controller.workbench;

import com.nanum.crm.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench")
public class WorkbenchController extends BaseController {

    @GetMapping("/index")
    public String toIndex(){
        return "workbench/index";
    }

    @GetMapping("/dashboard")
    public String toDashboard() {
        return "workbench/dashboard";
    }
}
