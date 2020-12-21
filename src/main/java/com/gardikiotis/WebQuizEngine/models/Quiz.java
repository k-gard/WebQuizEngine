package com.gardikiotis.WebQuizEngine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;

@Entity(name = "quiz")
@JsonIgnoreProperties(value={ "answer" }, allowSetters= true)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Column
    @NotBlank(message = "Text is mandatory")
    private String text;
    @Column
    @NotEmpty(message = "Options are mandatory")
    @Size(min = 2)
    private String[] options;

    @Column
    private int[] answer;



    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "quizSetId")
    @JsonIgnore
    private QuizSet quizSet;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private AppUser user;

    public QuizSet getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(QuizSet quizSet) {
        this.quizSet = quizSet;
    }

    public Quiz(int id, String title, String text, String[] options, int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }



    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(int[] userAnswer){
      return Arrays.equals(answer,userAnswer);
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer=" + Arrays.toString(answer) +
                '}';
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
