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

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }

    @GetMapping("/authDetail")
    public String authDetail() {
        return "authDetail";
    }

    @GetMapping("/authEdit")
    public String authEdit() {
        return "authEdit";
    }

    @GetMapping("/challenge")
    public String challenge() {
        return "challenge";
    }

    @GetMapping("/challengeCreate")
    public String challengeCreate() {
        return "challengeCreate";
    }

    @GetMapping("/challengeDetail")
    public String challengeDetail() {
        return "challengeDetail";
    }

    @GetMapping("/challengeEdit")
    public String challengeEdit() {
        return "challengeEdit";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/faqCreate")
    public String faqCreate() {
        return "faqCreate";
    }

    @GetMapping("/faqDetail")
    public String faqDetail() {
        return "faqDetail";
    }

    @GetMapping("/faqEdit")
    public String faqEdit() {
        return "faqEdit";
    }

    @GetMapping("/group")
    public String group() {
        return "group";
    }

    @GetMapping("/groupDetail")
    public String groupDetail() {
        return "groupDetail";
    }

    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }

    @GetMapping("/noticeCreate")
    public String noticeCreate() {
        return "noticeCreate";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "noticeDetail";
    }

    @GetMapping("/noticeEdit")
    public String noticeEdit() {
        return "noticeEdit";
    }

    @GetMapping("/report")
    public String report() {
        return "report";
    }

    @GetMapping("/reportDetail")
    public String reportDetail() {
        return "reportDetail";
    }

    @GetMapping("/reportEdit")
    public String reportEdit() {
        return "reportEdit";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "userDetail";
    }

}
