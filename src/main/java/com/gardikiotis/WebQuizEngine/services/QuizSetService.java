package com.gardikiotis.WebQuizEngine.services;

import com.gardikiotis.WebQuizEngine.models.QuizSet;
import com.gardikiotis.WebQuizEngine.repositories.QuizSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class QuizSetService {
    @Autowired
    private QuizSetRepository repository;

    public void saveQuizSet(QuizSet quizSet) {
        repository.save(quizSet);
    }

    public ArrayList<QuizSet> getAllQuizSets() {
        return new ArrayList<>((Collection<? extends QuizSet>) repository.findAll());
    }

    public void deleteQuizSetById(int id) {
        repository.deleteById(id);
    }

    public QuizSet getQuizSetById(int id) {
        return repository.findById(id).orElse(null);
    }


    public Page<QuizSet> getAllQuizSetsPaged(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<QuizSet> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }
}
