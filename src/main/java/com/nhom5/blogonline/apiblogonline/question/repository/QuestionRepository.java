package com.nhom5.blogonline.apiblogonline.question.repository;

import com.nhom5.blogonline.apiblogonline.question.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
