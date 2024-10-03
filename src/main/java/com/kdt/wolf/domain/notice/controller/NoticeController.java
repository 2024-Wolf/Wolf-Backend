package com.kdt.wolf.domain.notice.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notice")
public class NoticeController {

        @GetMapping("/{noticeId}")
        public String getNotice(@PathVariable("noticeId") Long noticeId) {
            return "notice";
        }

        @PostMapping("/{noticeId}")
        public String createNotice(@PathVariable("noticeId") Long noticeId) {
            return "notice";
        }

        @PatchMapping("/{noticeId}")
        public String updateNotice(@PathVariable("noticeId") Long noticeId) {
            return "notice";
        }

        @DeleteMapping("/{noticeId}")
        public String deleteNotice(@PathVariable("noticeId") Long noticeId) {
            return "notice";
        }
    }
