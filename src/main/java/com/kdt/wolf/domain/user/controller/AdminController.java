package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.global.base.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @GetMapping("/adminLogin")
    public String adminLogin() {
        return "adminLogin";
    }

    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }

    @GetMapping("/noticeCreate")
    public String noticeCreate() {
        return "noticeCreate";
    }

    @GetMapping("/noticeEdit")
    public String noticeEdit() {
        return "noticeEdit";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "noticeDetail";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/faqCreate")
    public String faqCreate() {
        return "faqCreate";
    }

    @GetMapping("/faqEdit")
    public String faqEdit() {
        return "faqEdit";
    }

    @GetMapping("/faqDetail")
    public String faqDetail() {
        return "faqDetail";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "userDetail";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/group")
    public String group() {
        return "group";
    }

    @GetMapping("/groupDetail")
    public String groupDetail() {
        return "groupDetail";
    }

    @GetMapping("/challenge")
    public String challenge() {
        return "challenge";
    }

    @GetMapping("/challengeDetail")
    public String challengeDetail() {
        return "challengeDetail";
    }

    @GetMapping("/report")
    public String report() {
        return "report";
    }

    @GetMapping("/reportDetail")
    public String reportDetail() {
        return "reportDetail";
    }

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }

    @GetMapping("/authDetail")
    public String authDetail() {
        return "authDetail";
    }

}
