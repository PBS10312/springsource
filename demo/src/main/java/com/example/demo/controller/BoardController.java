package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequestMapping("/board")
@Controller
public class BoardController {

    @GetMapping("/create")
    public void getcreate() {
        // return "board/create";
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
