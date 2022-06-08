package com.nhom5.blogonline.apiblogonline.answer.controller;

import java.util.List;

import com.nhom5.blogonline.apiblogonline.exception.ResourceNotFoundException;
import com.nhom5.blogonline.apiblogonline.answer.model.Answer;
import com.nhom5.blogonline.apiblogonline.answer.repository.AnswerRepository;

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
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepo;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Answer> getAllAnswers() {
        return this.answerRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Answer createQuestion(@RequestBody Answer answer) {
        return this.answerRepo.save(answer);
    }


    @PutMapping("/{id}")
    public Answer updateUser(@RequestBody Answer answer, @PathVariable("id") int id) {
        Answer existingAnswer = this.answerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taco not found with id :" + id));
        existingAnswer.setDescription(answer.getDescription());
        return this.answerRepo.save(existingAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Answer> deleteUser(@PathVariable("id") int id) {
        Answer existingAnswer = this.answerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        this.answerRepo.delete(existingAnswer);
        return ResponseEntity.ok().build();
    }
}
