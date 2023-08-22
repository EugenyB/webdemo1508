package com.example.webdemo1508.services;

import com.example.webdemo1508.repositories.ExpressionRepository;
import lombok.AllArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpressionService {

    ExpressionRepository expressionRepository;

    public boolean check(String expression, char first, char second) {
        int count = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == first) {
                count++;
            } else if (c == second) {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public boolean isCorrect(String expression) {
        if (check(expression, '(', ')')) {
            ExpressionBuilder builder = new ExpressionBuilder(expression);
            Expression expr = builder.build();
            ValidationResult result = expr.validate();
            return result.isValid();
        }
        return false;
    }

    public void saveToDB(String s, double res) {
        com.example.webdemo1508.data.Expression expression = new com.example.webdemo1508.data.Expression();
        expression.setExpr(s);
        expression.setResult(res);
        expressionRepository.save(expression);
    }

    public List<com.example.webdemo1508.data.Expression> getExpressions() {
        return expressionRepository.findAll();
    }
}
