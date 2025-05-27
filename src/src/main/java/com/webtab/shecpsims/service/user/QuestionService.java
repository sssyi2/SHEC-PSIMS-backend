package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.Question;

public interface QuestionService extends IService<Question> {
    boolean addQuestion(Question question);

    boolean delQuestion(int questionId);

    Question  getQuestionById(int questionId);
}
