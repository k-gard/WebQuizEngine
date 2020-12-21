package com.gardikiotis.WebQuizEngine.repositories;

import com.gardikiotis.WebQuizEngine.models.CompletedQuiz;
import com.gardikiotis.WebQuizEngine.models.CompletedQuizSet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedQuizSetRepository  extends PagingAndSortingRepository<CompletedQuizSet, Integer> {
}
