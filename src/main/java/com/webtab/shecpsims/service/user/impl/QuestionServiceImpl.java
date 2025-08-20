package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.model.entity.user.Question;
import com.webtab.shecpsims.mapper.user.QuestionMapper;
import com.webtab.shecpsims.service.user.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

//    @Override
//    public boolean addQuestion(Question question) {
//       return save(question);
//    }
//
//    @Override
//    public boolean delQuestion(int questionId) {
//        int i = baseMapper.deleteById(questionId);
//        return i>0;
//    }

//    @Override
//    public Question getQuestionById(int questionId) {
//        Question question = baseMapper.selectById(questionId);
//        return question;
//    }

//    @Override
//    public List<Question> getList() {
//        LambdaQueryWrapper<Question> query = new LambdaQueryWrapper<>();
//
//        return questionMapper.selectList(query);
//    }
}
