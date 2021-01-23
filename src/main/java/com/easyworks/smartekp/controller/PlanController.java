package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {
    
    @GetMapping("plan/personalPlan")
    public String personalPlan() {
        return "plan/personalPlan";
    }

    @GetMapping("plan/departmentPlan")
    public String departmentPlan() {
        return "plan/departmentPlan";
    }
    
      @GetMapping("plan/companyPlan")
    public String companyPlan() {
        return "plan/companyPlan";
    }  



}
