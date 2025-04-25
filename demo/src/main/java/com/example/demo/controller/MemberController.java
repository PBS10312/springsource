package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/member")
@Log4j2
@Controller
public class MemberController {
    // http://localhost:8080/member/register

    // 회원가입 : /member/register
    // void 일때 templates/member/register.html 라는 의미를 가짐
    @GetMapping("/register")
    public void getregister(@ModelAttribute("mDto") MemberDTO memberDTO) {
        log.info("회원가입");
    }

    @PostMapping("/register")
    public String postregister(@ModelAttribute("mDto") @Valid MemberDTO memberDTO, BindingResult result,
            RedirectAttributes rttr) {
        // @ToString 이 없으면 MemberDTO@43c4ee7d {}에 주소 형태로 나옴
        log.info("회원가입 요청{}", memberDTO);

        // 유효성 검사 통과하지 못한다면 원래 입력 페이지로 돌아가기
        if (result.hasErrors()) {
            return "/member/register";
        }

        // 로그인 페이지로 이동
        // redirect 방식으로 가면서 값을 보내고 싶다면?
        // rttr.addAttribute("userid", memberDTO.getUserid());
        rttr.addAttribute("userid", memberDTO.getUserid());
        rttr.addFlashAttribute("password", memberDTO.getPassword());
        return "redirect:/member/login";

    }

    // 로그인 : /member/login
    @GetMapping("/login")
    public void getlogin() {
        log.info("로그인 페이지 요청");
    }

    @PostMapping("/login")
    // public void postlogin(String userid, String password) {
    // public void postlogin(LoginDTO loginDTO) {
    public void postlogin(HttpServletRequest request) {
        // HttpServletRequest : 사용자의 정보 및 서버 쪽 정보 추출

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String remote = request.getRemoteAddr();
        String local = request.getLocalAddr();

        log.info("로그인 요청 {}, {}", userid, password);
        log.info("클라이언트 정보 {}, {}", remote, local);
        // template 찾기가 전부 기본으로 들어가있음
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
