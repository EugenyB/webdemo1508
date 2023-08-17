package com.example.webdemo1508.controller;

import com.example.webdemo1508.data.Expression;
import com.example.webdemo1508.dto.ExpressionDTO;
import com.example.webdemo1508.services.ExpressionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class CalculatorController {

    ExpressionService es;

    @GetMapping("/")
    public String index(Model model) {
        List<Expression> expressions = es.getExpressions();
        model.addAttribute("expressions", expressions);
        return "index";
    }

    @PostMapping("calculate")
    public String calculate(@RequestParam String expression, Model model) {
        String infoBrackets, validationResult;
        if (es.check(expression, '(', ')')) {
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

    @PostMapping("store")
    public String saveToDB(@RequestParam String expr, @RequestParam String result) {
        String s = result.trim().replace(',', '.');
        double res = Double.parseDouble(s);
        es.saveToDB(expr, res);
        return "redirect:/";
    }
}
