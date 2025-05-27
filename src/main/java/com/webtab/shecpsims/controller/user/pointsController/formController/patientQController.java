package com.webtab.shecpsims.controller.user.pointsController.formController;

import com.webtab.shecpsims.model.entity.user.Question;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.QuestionService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//填写与提交问题
@RestController
@RequestMapping("/user/form")
public class patientQController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @PostMapping("/write/{id}")
    //填写单个问题
    public R writeForm(@PathVariable("id")int questionId,@RequestParam String answer) {
        if(questionId ==0){
            return R.error(509).msg("问题id不能为零");
        }
        if(answer==null||answer.isEmpty()){
            return R.error(516).msg("回答不能为空");
        }
        Question question = questionService.getQuestionById(questionId);
        if(question==null){
            return R.error(514).msg("问题不存在");
        }
        question.setAnswer(answer);
        question.setStatus(1);
        questionService.updateById(question);
        return R.ok().data(question);

    }
    //提交问题,写完所有的问题才可提交
    @PostMapping("/submit/{id}")
    public R submitForm(@PathVariable("id") Integer UserID,@RequestBody List<Question> question) {
        if(UserID==0){
            return R.error(503).msg("用户id不能为空");
        }
        User user = userService.getUserById(UserID);
        if(user==null){
            return R.error(506).msg("用户不存在");
        }
        int userpoints = user.getUserpoints();
        if(userpoints<0){
            return R.error(507).msg("积分数有误");
        }//积分数无误则判断回答状态
        for(Question q:question){
            if(q.getQuestionId()==0){
                return R.error(509).msg("问题id不能为空");
            }
            if(questionService.getQuestionById(q.getQuestionId())==null){
                return R.error(514).msg("问题不存在");
            }
            Question q2=questionService.getQuestionById(q.getQuestionId());

            if(q.getAnswer()==null){
                return R.error(516).msg("回答不能为空");
            }
            if(q2.getStatus()!=1){
                return R.error(517).msg("回答状态异常");
            }
            q2.setAnswer(q.getAnswer());
            questionService.updateById(q2);
        }//回答无异常则增加用户积分
        userpoints=userpoints+50;
        user.setUserpoints(userpoints);
        userService.updateById(user);

        return R.ok().data(user);
    }
}
