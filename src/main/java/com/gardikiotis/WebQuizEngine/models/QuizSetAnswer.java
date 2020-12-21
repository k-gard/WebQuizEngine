package com.gardikiotis.WebQuizEngine.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;


public class QuizSetAnswer {

    private HashMap<Integer,UserAnswer> answers;

    public QuizSetAnswer() {
    }

    public QuizSetAnswer(HashMap<Integer, UserAnswer> answers) {
        this.answers = answers;
    }

    public HashMap<Integer, UserAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Integer, UserAnswer> answers) {
        this.answers = answers;
    }
}
