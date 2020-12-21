package com.gardikiotis.WebQuizEngine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuizSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private AppUser user;



    @Column
    @NotBlank(message = "Category is mandatory")
    private String category;
    @Column
    @NotBlank(message = "description is mandatory")
    private String description;



    @OneToMany(targetEntity= Quiz.class, mappedBy="quizSet",cascade=CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    public QuizSet() {
    }

    public QuizSet(String category, String description, List<Quiz> quizzes) {
        this.category = category;
        this.description = description;
        this.quizzes = quizzes;
    }

    public int getId() {
        return id;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void addQuiz(Quiz q){
        this.quizzes.add(q);
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
