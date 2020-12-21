package com.gardikiotis.WebQuizEngine.services;

import com.gardikiotis.WebQuizEngine.models.CompletedQuiz;
import com.gardikiotis.WebQuizEngine.repositories.CompletedQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedQuizService {

    @Autowired
    CompletedQuizRepository repository;

    public CompletedQuiz getCompletedQuizzesById(int Id){
       return repository.findById(Id).get();
    }

    public void saveCompletedQuiz(CompletedQuiz cq) {
        repository.save(cq);
    }


}
