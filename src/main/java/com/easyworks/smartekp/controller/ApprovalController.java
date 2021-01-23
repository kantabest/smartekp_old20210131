package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprovalController {

    // 수신 결재함
    @GetMapping("approval/createApproval")
    public String createApproval() {
        return "approval/createApproval";
    }

    // 수신 결재함
    @GetMapping("approval/receiveApproval")
    public String receiveApproval() {
        return "approval/receiveApproval";
    }

    //상신 결재함
    @GetMapping("approval/sendApproval")
    public String sendApproval() {
        return "approval/sendApproval";
    }

    //진행 결재함
    @GetMapping("approval/proceedingApproval")
    public String proceedingApproval() {
        return "approval/proceedingApproval";
    }

    @GetMapping("approval/rejectApproval")
    public String rejectApproval() {
        return "approval/rejectApproval";
    }

    @GetMapping("approval/holdApproval")
    public String holdApproval() {
        return "approval/holdApproval";
    }

    @GetMapping("approval/successApproval")
    public String successApproval() {
        return "approval/successApproval";
    }

}
