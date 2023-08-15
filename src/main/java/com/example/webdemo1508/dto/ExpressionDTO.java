package com.example.webdemo1508.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Data
@AllArgsConstructor
public class ExpressionDTO {
    private String expression;
    private String ib;
    private String vr;


    public String getResult() {
        Expression expr = new ExpressionBuilder(expression).build();
        if (expr.validate().isValid()) {
            return String.format("%6.3f", expr.evaluate());
        } else {
            return "can't calculate";
        }
    }

    public String getButtonClass() {
        Expression expr = new ExpressionBuilder(expression).build();
        if (expr.validate().isValid()) {
            return "btn btn-success";
        } else {
            return "btn btn-danger";
        }
    }

    public String getType() {
        Expression expr = new ExpressionBuilder(expression).build();
        if (expr.validate().isValid()) {
            return "submit";
        } else {
            return "button";
        }
    }
}
