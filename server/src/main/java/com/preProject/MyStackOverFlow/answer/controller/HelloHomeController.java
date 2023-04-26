package com.preProject.MyStackOverFlow.answer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {
    @GetMapping("boards")
    public String home() {
        return "hello-oauth2";
    }

    @GetMapping("/members/{memberId}")
    public String home3() {
        return "member-mypage";
    }

    @GetMapping("/login")
    public String home4() {
        return "login";
    }


}