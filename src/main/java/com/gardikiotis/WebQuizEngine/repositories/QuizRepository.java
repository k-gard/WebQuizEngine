package com.gardikiotis.WebQuizEngine.repositories;

import com.gardikiotis.WebQuizEngine.models.AppUser;
import com.gardikiotis.WebQuizEngine.models.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {

    public List<Quiz> findByUser(AppUser user);

}
