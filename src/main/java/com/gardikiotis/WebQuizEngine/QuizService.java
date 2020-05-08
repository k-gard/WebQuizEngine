package com.gardikiotis.WebQuizEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;


    public List<Quiz> getAllQuizzes() {
       return new ArrayList<Quiz>((Collection<? extends Quiz>) repository.findAll());
    }

    public Quiz getQuizById(int id) {
    return repository.findById(id).get();
    }

    public void saveQuiz(Quiz q){
        repository.save(q);
    }

    public Page<Quiz>/*List<Quiz>*/ getAllQuizzesPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Quiz> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;//.getContent();
        } else {
            return null ; //new ArrayList<Quiz>();
        }
    }


    public void deleteQuizById(int id) {
        repository.deleteById(id);
    }
}
