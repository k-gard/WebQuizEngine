package com.gardikiotis.WebQuizEngine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CompletedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    //@JsonIgnore
    private int quizId;
    @Column
    private LocalDateTime completedAt;


   /* @Column
    private int userId;*/

    public CompletedQuiz(int quizId, LocalDateTime completedAt/*, int userId*/) {
        this.quizId = quizId;
        this.completedAt = completedAt;
     //   this.userId = userId;
    }

    public CompletedQuiz() {
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

  /*  public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompletedQuiz{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", completionTime=" + completedAt +
                '}';
    }
}
