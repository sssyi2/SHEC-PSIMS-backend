package com.webtab.shecpsims.controller.user.pointsController.formController;

import com.webtab.shecpsims.mapper.user.QuestionMapper;
import com.webtab.shecpsims.model.entity.user.*;
import com.webtab.shecpsims.service.user.AnswerService;
import com.webtab.shecpsims.service.user.QuestionService;
import com.webtab.shecpsims.service.user.QuestionnaireService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//填写与提交问题
@RestController
@RequestMapping("/user/form")
public class patientQController {
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionMapper questionMapper;
    @PostMapping("/update/{id}")//判断问卷更新状态
//    判断在回答表中该用户是否提交问卷调查ID
    public R isSubmitted(@PathVariable("id")int userId,@RequestParam int questionnaireId) {
        //我们要完成的是怎么做到用户提交后不重复提交相同的问卷以及管理员发布新的问卷，用户这里怎么更新

        if(userId ==0){
            return R.error(509).msg("问题id不能为零");
        }
        User user = userService.getUserById(userId);
        if(user == null){
            return R.error(506).msg("用户不存在");
        }
        //对问题进行参数判断
       if(questionnaireId == 0){
           return R.error(508).msg("调查问卷ID为0");
       }
        boolean submitted = answerService.isSubmitted(userId,questionnaireId);
        System.out.println("是否已提交？"+submitted);
        //返回true则表示该用户已经提交过问卷，false则表明没有提过过问卷
        return R.ok().data(submitted);

    }
    //提交问题,写完所有的问题才可提交
    @PostMapping("/submit")
    public R submitForm(@RequestBody Answer answers ) {
        //提交后是否显示用户ID，如果显示用户ID，表示该用户已经提交过问卷调查，在问卷调查前不能再次提交。
        //问卷调查更新机制
        int UserID = answers.getUserId();
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
     if(answers.getQuestionnaireId()==0){
    return R.error(508).msg("提交问卷调查ID不能为零");
      }
     if(answers.getSubmitDate()==null){
         return R.error(509).msg("提交日期不能为空");
     }
        List<QuestionAnswer> questionAnswers = answers.getQuestionAnswers();
     for(QuestionAnswer questionAnswer : questionAnswers) {
         if(questionAnswer.getQuestionId()==0){
             return R.error(510).msg("问题ID不能为空");
         }
         int questionId = questionAnswer.getQuestionId();
         Question question = questionMapper.selectById(questionId);
         if(question==null){
             return R.error(511).msg("问题不能为空");
         }
         String questionType = question.getQuestionType();
         if(questionType.equals("单选题")){
             if(questionAnswer.getOptionId()==0){
                 return R.error(512).msg("选项不能为空");
             }
         }else if(questionType.equals("文本题")){
             if(questionAnswer.getTextAnswer()==null||questionAnswer.getTextAnswer().isEmpty()){
                 return R.error(513).msg("输入文本不能为空");
             }
         }else{
             if(questionAnswer.getOptionIds()==null||questionAnswer.getOptionIds().isEmpty()){
                 return R.error(514).msg("多选题选项不能为空");
             }
         }

     }
        boolean b = answerService.saveAnswer(answers);
     if(b){
         userpoints=userpoints+50;//保存成功的情况下更新积分
         user.setUserpoints(userpoints);
         userService.updateById(user);
         System.out.println(userpoints);
         return R.ok().data(user);
     }else{
         return R.error(515).msg("提交问卷失败");
     }

    }
    @GetMapping("/list")
//    显示一个最新发布的调查问卷
    public R listQ() {
        Questionnaires q = questionnaireService.getLast();
        return R.ok().data(q);
    }
}
