package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/member")
@Log4j2
@Controller
public class MemberController {
    // http://localhost:8080/member/register

    // 회원가입 : /member/register
    // void 일때 templates/member/register.html 라는 의미를 가짐
    @GetMapping("/register")
    public void getregister() {
        log.info("회원가입");
    }

    // 로그인 : /member/login
    @GetMapping("/login")
    public void getlogin() {
        log.info("로그인");
    }

    // 로그아웃 ㅣ /member/logout
    @GetMapping("/logout")
    public void getlogout() {
        log.info("로그아웃");
    }

    // 비밀번호 변경 : /member/change
    @GetMapping("/change")
    public void getchange() {
        log.info("회원정보변경");
    }

}
