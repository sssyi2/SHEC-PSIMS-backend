package com.webtab.shecpsims.model.entity.user;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Question implements Serializable {
    @TableId(type= IdType.AUTO)
    private int questionId;
    private int questionnaireId;//关联问卷ID
    private String question;//问题内容
    private String questionType;
    private int sortOrder;
    //question表作为管理员对问题的管理
    @TableField(exist = false)
    private List<QuestionOptions> options;

    public List<QuestionOptions> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptions> options) {
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "options=" + options +
                ", questionId=" + questionId +
                ", questionnaireId=" + questionnaireId +
                ", question='" + question + '\'' +
                ", questionType='" + questionType + '\'' +
                ", sortOrder=" + sortOrder +
                '}';
    }
}
