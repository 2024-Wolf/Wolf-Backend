package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.global.base.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/challengeDetail")
    public String challengeDetail() {
        return "challengeDetail";
    }

    @GetMapping("/challengeEdit")
    public String challengeEdit() {
        return "challengeEdit";
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

    @GetMapping("/groupDetail")
    public String groupDetail() {
        return "groupDetail";
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