package com.example.webdemo1508.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ExpressionDTO {
    private String expression;
    private String ib;
    private String vr;


    public String getResult() {
        Optional<Expression> expr = checkAndValid(expression);
        if (expr.isPresent()) {
            return String.format("%6.3f", expr.get().evaluate());
        } else {
            return "can't calculate";
        }
    }

    public String getButtonClass() {
        Optional<Expression> expr = checkAndValid(expression);
        if (expr.isPresent()) {
            return "btn btn-success";
        } else {
            return "btn btn-danger";
        }
    }

    public String getType() {
        Optional<Expression> expr = checkAndValid(expression);
        if (expr.isPresent()) {
            return "submit";
        } else {
            return "button";
        }
    }

    public Optional<Expression> checkAndValid(String expression) {
        try {
            Expression expr = new ExpressionBuilder(expression).build();
            if (expr.validate().isValid()) {
                return Optional.of(expr);
            } else return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
