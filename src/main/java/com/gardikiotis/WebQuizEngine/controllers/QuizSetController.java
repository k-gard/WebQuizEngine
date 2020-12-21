package com.gardikiotis.WebQuizEngine.controllers;

import com.gardikiotis.WebQuizEngine.models.*;
import com.gardikiotis.WebQuizEngine.services.CompletedQuizSetService;
import com.gardikiotis.WebQuizEngine.services.QuizService;
import com.gardikiotis.WebQuizEngine.services.QuizSetService;
import com.gardikiotis.WebQuizEngine.services.UserService;
import com.gardikiotis.WebQuizEngine.repositories.QuizSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizSetController {

    @Autowired
    private QuizSetRepository quizSetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizSetService quizSetService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private CompletedQuizSetService completedQuizSetService;


    @PostMapping(path = "api/quizSet" ,consumes = "application/json")
    public ResponseEntity createQuizSet(@RequestBody QuizSet postedQuizSet){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userService.findUserByEmail(auth.getName());
        QuizSet quizSet = new QuizSet();
      //  quizSet.setCustomUser(user);
        quizSet.setCategory(postedQuizSet.getCategory());
        quizSet.setDescription(postedQuizSet.getDescription());

        ArrayList<Quiz> quizzes = new ArrayList<>(postedQuizSet.getQuizzes());
        for(Quiz q : quizzes){
          //  q.setQuizSet(quizSet);
         //   user.addQuiz(q);
            q.setUser(user);
            q.setQuizSet(quizSet);
            quizSet.addQuiz(q);
            quizService.saveQuiz(q);

        }
        quizSet.setUser(user);
        quizSetService.saveQuizSet(quizSet);
        user.addQuizSet(quizSet);
        userService.updateUser(user);
      //

      //  quizSetService.saveQuizSet(quizSet);


        return ResponseEntity.ok().body(quizSet);
    }

    @GetMapping("api/allQuizSets")
    public ResponseEntity getAllQuizSets(){
        return ResponseEntity.ok().body(quizSetService.getAllQuizSets());

    }

    @DeleteMapping("api/quizSet/{id}")
    public ResponseEntity deleteQuizSetById(@PathVariable int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser user = userService.findUserByEmail(auth.getName());
        QuizSet quizSet =quizSetService.getQuizSetById(id);
        if (user.createdQuizSetWithId(id)){
            user.deleteQuizSet(quizSet);
            quizSetService.deleteQuizSetById(id);
            userService.updateUser(user);
            return ResponseEntity.noContent().build();}
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @PostMapping("api/quizSet/{id}/solve")
    public ResponseEntity solveQuizSetById(@PathVariable int id , @RequestBody QuizSetAnswer answer){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userService.findUserByEmail(auth.getName());
        QuizSet quizSet = quizSetService.getQuizSetById(id);
        CompletedQuizSet completedQuizSet = new CompletedQuizSet();
        Set<Integer> quizIds = answer.getAnswers().keySet();
        System.out.println(quizIds);
        quizIds.forEach(x -> { if (quizSet.getQuizzes().get(x).checkAnswer(answer.getAnswers().get(x).getAnswer())) {
            completedQuizSet.addScore();
        }
        });
        completedQuizSet.setCompletedAt(LocalDateTime.now());
        if (completedQuizSet.getScore() == quizSet.getQuizzes().size()){
        completedQuizSet.setPassed(true);}
        completedQuizSet.setUser(user);
        user.addCompletedQuizSet(completedQuizSet);
        completedQuizSetService.saveCompletedQuizSet(completedQuizSet);
        return ResponseEntity.ok().body(completedQuizSet);

    }

    @GetMapping("api/quizSet/{id}")
    public ResponseEntity getQuizSetById(@PathVariable int id ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser user = userService.findUserByEmail(auth.getName());
        QuizSet quizSet = quizSetService.getQuizSetById(id);

        return ResponseEntity.ok().body(quizSet);

    }


    }




