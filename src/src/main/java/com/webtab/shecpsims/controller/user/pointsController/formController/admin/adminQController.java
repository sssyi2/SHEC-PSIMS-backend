package com.webtab.shecpsims.controller.user.pointsController.formController.admin;

import com.webtab.shecpsims.model.entity.user.Question;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//对调查问题的增删改查
@RestController
@RequestMapping("/admin/form")
public class adminQController {
    @Autowired
    private QuestionService questionService;
    @PostMapping("/add")
    public R addQ(@RequestBody Question question ) {
//            if(question.getQuestionId() == 0) {
//                return R.error(509).msg("问题id不能为空");
//            }
            question.setStatus(0);//未完成
            if(question.getQuestion() == null||question.getQuestion().isEmpty()) {
                return R.error(510).msg("问题不能为空");
            }
            if(question.getAnswer() != null ) {
                return R.error(511).msg("回答必须为空");
            }
        boolean b = questionService.addQuestion(question);

        return b? R.ok().data(question):R.error(512).msg("添加问题失败");

    }
    @DeleteMapping("/delete/{id}")
    public R deleteQ(@PathVariable("id") int questionId) {
        if(questionId == 0) {
            return R.error(509).msg("问题id不能为空");
        }
        boolean b = questionService.delQuestion(questionId);
        return b? R.ok().msg("删除成功"):R.error(513).msg("删除问题失败");

    }
    @PostMapping("/update/{id}")
    public R updateQ(@PathVariable("id") int questionId, @RequestBody Question question) {
        if(questionId == 0) {
            return R.error(509).msg("问题id不能为空");
        }
        Question q = questionService.getQuestionById(questionId);
        if(q == null) {
            return R.error(514).msg("问题不存在");
        }
        if(question.getQuestion() == null||question.getQuestion().isEmpty()) {
            return R.error(510).msg("问题不能为空");
        }
        if(question.getAnswer() != null) {
            return R.error(511).msg("回答必须为空");
        }
        question.setQuestionId(questionId);
        question.setStatus(0);
        boolean b = questionService.updateById(question);
        return b? R.ok().msg("修改成功"):R.error(515).msg("修改问题失败");

    }
    @GetMapping("/list")
    public R listQ() {
        List<Question> list = questionService.list();
        return R.ok().data(list);
    }

}
