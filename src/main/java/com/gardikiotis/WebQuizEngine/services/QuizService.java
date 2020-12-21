package com.gardikiotis.WebQuizEngine.services;

import com.gardikiotis.WebQuizEngine.models.AppUser;
import com.gardikiotis.WebQuizEngine.models.Quiz;
import com.gardikiotis.WebQuizEngine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    public Page<Quiz> getAllQuizzesPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Quiz> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null ;
        }
    }


    public Page<Quiz> getAllUserQuizzesPaged(AppUser user, Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        PageImpl<Quiz> page=new PageImpl<Quiz>(repository.findByUser(user), paging, repository.findByUser(user).size());
        Page<Quiz> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null ;
        }
    }



    public void deleteQuizById(int id) {
        repository.deleteById(id);
    }
}
