package com.nhom5.blogonline.apiblogonline.question.controller;

import com.nhom5.blogonline.apiblogonline.question.repository.QuestionRepository;
import com.nhom5.blogonline.apiblogonline.exception.ResourceNotFoundException;

import java.util.List;

import com.nhom5.blogonline.apiblogonline.question.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private QuestionRepository questionRepo;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Question> getAllQuestions() {
        return this.questionRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Question createQuestion(@RequestBody Question question) {
        return this.questionRepo.save(question);
    }

    @PutMapping("/{id}")
    public Question updateUser(@RequestBody Question question, @PathVariable("id") int id) {
        Question existingQuestion = this.questionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id :" + id));
        existingQuestion.setContentBlog(question.getContentBlog());
        return this.questionRepo.save(existingQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") int id) {
        Question existingQuestion = this.questionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id :" + id));
        this.questionRepo.delete(existingQuestion);
        return ResponseEntity.ok().build();
    }

}
