package com.kdt.wolf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
	
	@RequestMapping("/notice")
    @PostMapping
    public ResponseEntity<String> handleLogin(@RequestParam String username, @RequestParam String password) {
        // 로그인 처리 로직
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/")
    public String adminLoginPage() {
        return "adminLogin"; // adminLogin.jsp를 반환
    }
    
    @GetMapping("/auth")
    public String authPage() {
        return "auth"; // auth.jsp를 반환
    }
    
    @GetMapping("/challenge")
    public String challengePage() {
        return "challenge"; // challenge.jsp를 반환
    }
    
    @GetMapping("/faq")
    public String faqPage() {
        return "faq"; // faq.jsp를 반환
    }
    
    @GetMapping("/group")
    public String groupPage() {
        return "group"; // group.jsp를 반환
    }
    
    @GetMapping("/notice")
    public String notice() {
        return "notice"; // notice.jsp를 반환
    }
    
    @GetMapping("/report")
    public String reportPage() {
        return "report"; // report.jsp를 반환
    }
    
    @GetMapping("/user")
    public String userPage() {
        return "user"; // user.jsp를 반환
    }
    

}