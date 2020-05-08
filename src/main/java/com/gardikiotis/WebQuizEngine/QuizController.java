package com.gardikiotis.WebQuizEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class QuizController {
    int i=1;

    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    @Autowired
    CompletedQuizService completedQuizService;

    @GetMapping(path = "/api/allQuizzes")
    public List<Quiz> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @GetMapping(path = "api/quizzes")
    public ResponseEntity<Page<Quiz>> getAllQuizzesPaged(@RequestParam/*("pageNo")*/ int page){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity(quizService.getAllQuizzesPaged(page,10,"id"),headers,HttpStatus.OK);

    }

    @GetMapping(path = "api/quizzes/completed")
    public Page<CompletedQuiz> getUsersCompletedQuizzes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser user = userService.findUserByEmail(auth.getName());
        return user.getCompletedQuizzesPaged(0,10,"completedAt");
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("id") int id){
            return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PostMapping(path = "/api/quizzes/{id}/solve" ,consumes = "application/json")
    public ResponseEntity solveQuiz(@RequestBody UsersAnswer answer, @PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser user = userService.findUserByEmail(auth.getName());
        if (quizService.getQuizById(id).checkAnswer(answer.getAnswer())) {
            user.addCompletedQuiz(new CompletedQuiz(id, LocalDateTime.now()));
            userService.updateUser(user);
            return ResponseEntity.ok(new AnswerResult(true, "Congratulations, you're right!"));
        } else {
            return ResponseEntity.ok(new AnswerResult(false, "Wrong answerResult! Please, try again."));

        }
    }

    @DeleteMapping(path = "/api/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable int id) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUser user = userService.findUserByEmail(auth.getName());
        Quiz quiz =quizService.getQuizById(id);
        if (user.createdQuizWithId(id)){
        user.deleteQuiz(quiz);
        quizService.deleteQuizById(id);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();}
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @PostMapping(path = "/api/quizzes" , consumes = "application/json")
    public Quiz postQuiz (@Valid @RequestBody Quiz q) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser user = userService.findUserByEmail(auth.getName());
        if (q.getAnswer() == null){int[] a = {}; q.setAnswer(a); }
        user.addQuiz(q);
        userService.updateUser(user);
        return q;
    }



}
