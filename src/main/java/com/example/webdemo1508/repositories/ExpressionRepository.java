package com.example.webdemo1508.repositories;

import com.example.webdemo1508.data.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends JpaRepository<Expression, Integer> {
}