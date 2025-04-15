package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequestMapping("/board")
@Controller
public class BoardController {

    @GetMapping("/create")
    public void getcreate() {
        // return "board/create";
    }

    @PostMapping("/create")
    public void postCreate(String name, HttpSession session) {
        log.info("name 값 가져오기 {}", name);

        session.setAttribute("name1", name);
        // 어느 페이지로 이동을 하던지 간에 name을 유지시키고 싶다면
        // 커맨드 객체(DTO생성) , @ModelAttribute 사용
        // return "board/list";

        // redirect:/board/list
        // rttr.addAttribute("name", name);
        // rttr.addFlashAttribute("name", name);
        // return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void getlist() {
        // return "board/list";
    }

    @GetMapping("/read")
    public void getread() {
        // return "board/read";
    }

    @GetMapping("/update")
    public void getupdate() {
        // return "board/update";
    }

}
