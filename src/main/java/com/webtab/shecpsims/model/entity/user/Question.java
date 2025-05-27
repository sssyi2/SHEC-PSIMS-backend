package com.webtab.shecpsims.model.entity.user;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Question implements Serializable {
    @TableId
    private Integer questionId;
    private String question;
    private String answer;
    private int status;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
