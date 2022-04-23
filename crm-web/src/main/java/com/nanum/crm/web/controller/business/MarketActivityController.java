package com.nanum.crm.web.controller.business;

import com.nanum.crm.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketActivityController extends BaseController {

    @GetMapping("/business/market-activity-list")
    public String toIndex(){
        return "business/market-activity/market-activity-list";
    }


    @GetMapping("/business/market-activity-add")
    public String toAdd(){
        return "business/market-activity/market-activity-add";
    }

    @GetMapping("/business/market-activity/add.do")
    public String add() {
        return "null";
    }
}
