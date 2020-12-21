package com.gardikiotis.WebQuizEngine.services;

import com.gardikiotis.WebQuizEngine.models.CompletedQuizSet;
import com.gardikiotis.WebQuizEngine.repositories.CompletedQuizSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedQuizSetService {

    @Autowired
    private CompletedQuizSetRepository repository;

    public void saveCompletedQuizSet(CompletedQuizSet completedQuizSet){
        repository.save(completedQuizSet);
    }


}
