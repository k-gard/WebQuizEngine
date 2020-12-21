package com.gardikiotis.WebQuizEngine.repositories;

import com.gardikiotis.WebQuizEngine.models.CompletedQuiz;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedQuizRepository  extends PagingAndSortingRepository<CompletedQuiz, Integer> {

  //  List<CompletedQuiz> findByUser_Id(int UserId);
}
