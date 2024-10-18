package com.kdt.wolf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/authDetail")
    public String authDetail() {
        return "authDetail";
    }

    @GetMapping("/authEdit")
    public String authEdit() {
        return "authEdit";
    }

    @GetMapping("/challengeCreate")
    public String challengeCreate() {
        return "challengeCreate";
    }

    @GetMapping("/faqCreate")
    public String faqCreate() {
        return "faqCreate";
    }

    @GetMapping("/groupDetail")
    public String groupDetail() {
        return "groupDetail";
    }

    @GetMapping("/noticeCreate")
    public String noticeCreate() {
        return "noticeCreate";
    }
    @GetMapping("/reportDetail")
    public String reportDetail() {
        return "reportDetail";
    }

    @GetMapping("/reportEdit")
    public String reportEdit() {
        return "reportEdit";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "userDetail";
    }

}