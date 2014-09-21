package com.example.Simp.test;


import java.util.List;

public class Question {

    private String question;
    private List<String> variants;
    private int answer;

    public Question(String question, List<String> variants, int answer) {
        this.question = question;
        this.variants = variants;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
