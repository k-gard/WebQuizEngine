package com.gardikiotis.WebQuizEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
