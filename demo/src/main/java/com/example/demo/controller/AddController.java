package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.CalcDTO; // Adjust the package path if necessary

@Log4j2
@Controller
public class AddController {
    // http://localhost:8080/calc => calc.html
    @GetMapping("/calc")
    public void getCalc() {
        log.info("calc 페이지 요청");
    }

    @PostMapping("/calc")
    public String postCalc(CalcDTO calcDTO) {
        log.info("calc 연산 요청 {}", calcDTO);
        int result = 0;
        switch (calcDTO.getOp()) {
            case "+":
                result = calcDTO.getNum1() + calcDTO.getNum2();
                break;
            case "-":
                result = calcDTO.getNum1() - calcDTO.getNum2();
                break;
            case "*":
                result = calcDTO.getNum1() * calcDTO.getNum2();
                break;
            case "/":
                result = calcDTO.getNum1() / calcDTO.getNum2();
                break;
        }
        log.info("연산결과 {} {} {} = {}", calcDTO.getNum1(), calcDTO.getOp(), calcDTO.getNum2(), result);
        calcDTO.setResult(result);
        // template 찾기
        // http://localhost:8080/calc + void =>templates/calc.html

        return "result";
        // http://localhost:8080/calc + void =>templates/result.html
    }

}
