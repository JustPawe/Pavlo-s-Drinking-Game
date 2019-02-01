package com.markpaveszka.pavlosdrinkinggame;

/**
 * Created by Acer on 2017. 11. 21..
 */

public class Question {
    //Question currentQuestion = new Question(id, type, question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
    private int id;
    private String questionType;
    private String questionContent;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public Question(int id, String questionType, String questionContent, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        this.id = id;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    public int returnID(){
        return id;
    }
    public String returnQuestionType(){
        return questionType;
    }
    public String returnQuestionContent(){
        return questionContent;
    }
    public String returnCorrectAnswer(){
        return correctAnswer;
    }
    public String returnWrongAnswer1(){
        return wrongAnswer1;
    }
    public String returnWrongAnswer2(){
        return wrongAnswer2;
    }
    public String returnWrongAnswer3(){
        return wrongAnswer3;
    }

}
