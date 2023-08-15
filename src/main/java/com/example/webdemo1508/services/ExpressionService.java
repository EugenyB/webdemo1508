package com.example.webdemo1508.services;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import org.springframework.stereotype.Service;

@Service
public class ExpressionService {
    public boolean checkBrackets(String expression) {
        int count = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public boolean isCorrect(String expression) {
        Expression expr = new ExpressionBuilder(expression).build();
        ValidationResult result = expr.validate();
        return result.isValid();
    }
}
