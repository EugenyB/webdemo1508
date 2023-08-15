package com.example.webdemo1508.controller;

import com.example.webdemo1508.dto.ExpressionDTO;
import com.example.webdemo1508.services.ExpressionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CalculatorController {

    ExpressionService es;

    @PostMapping("calculate")
    public String calculate(@RequestParam String expression, Model model) {
        String infoBrackets, validationResult;
        if (es.checkBrackets(expression)) {
            infoBrackets = "Brackets correct";
        } else {
            infoBrackets = "Brackets incorrect";
        }
        if (es.isCorrect(expression)) {
            validationResult = "Expression is correct";
        } else {
            validationResult = "Expression is wrong";
        }
        model.addAttribute("exprDto", new ExpressionDTO(expression, infoBrackets, validationResult));
        return "result";
    }
}
