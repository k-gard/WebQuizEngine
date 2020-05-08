package com.gardikiotis.WebQuizEngine;

public class UsersAnswer {
    private int[] answer;

    public UsersAnswer() {
    }

    public UsersAnswer(int[] answer) {
        this.answer = answer;
    }


    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
