package com.gardikiotis.WebQuizEngine;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompletedQuizRepository  extends PagingAndSortingRepository<CompletedQuiz, Integer> {

  //  List<CompletedQuiz> findByUser_Id(int UserId);
}
