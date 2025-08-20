package com.webtab.shecpsims.controller.user.pointsController.formController.admin;

import com.webtab.shecpsims.model.entity.user.*;
import com.webtab.shecpsims.service.user.AnswerService;
import com.webtab.shecpsims.service.user.QuestionService;
import com.webtab.shecpsims.service.user.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//对调查问题的增删改查
@RestController
@RequestMapping("/admin/form")
public class adminQController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private AnswerService answerService;
    @PostMapping("/add")//创建问卷
    public R addQ(@RequestBody Questionnaires questionnaire ) {
//参数判断
        if(questionnaire.getTitle()==null||questionnaire.getTitle().isEmpty()){
            return R.error(510).msg("问卷标题不能为空");
        }
        if(questionnaire.getDes()==null||questionnaire.getDes().isEmpty()){
            return R.error(511).msg("问卷描述不能为空");
        }
        if(questionnaire.getStatus()==null||questionnaire.getStatus().isEmpty()){
            return R.error(512).msg("问卷状态异常");
        }
     if(questionnaire.getAdminId()==0){
         return R.error(513).msg("管理员ID不能为零");
     }
     //判断管理员是否存在
        if(questionnaire.getCreateDate()==null){
            return R.error(514).msg("问卷调查创建日期不能为空");
        }
        if(questionnaire.getUpdateDate()==null){
            return R.error(515).msg("问卷调查更新日期不能为空");
        }
        boolean b = questionnaireService.addQuestionnaire(questionnaire);

        return b? R.ok().data("创建问卷成功"):R.error(516).msg("添加问卷失败");

    }
    @DeleteMapping("/delete/{id}")//删除问卷
    public R deleteQ(@PathVariable("id") int questionnaireId) {
        if(questionnaireId == 0) {
            return R.error(509).msg("问卷调查id不能为空");
        }
        boolean b = questionnaireService.deleteQ(questionnaireId);
        return b? R.ok().msg("删除成功"):R.error(513).msg("删除问题失败");

    }
    @PostMapping("/update/{id}")//更新问卷
    public R updateQ(@PathVariable("id") int questionnaireId, @RequestBody Questionnaires questionnaire) {
        if(questionnaireId == 0) {
            return R.error(509).msg("问题id不能为空");
        }
        if(questionnaire.getTitle()==null||questionnaire.getTitle().isEmpty()){
            return R.error(510).msg("问卷标题不能为空");
        }
        if(questionnaire.getDes()==null||questionnaire.getDes().isEmpty()){
            return R.error(511).msg("问卷描述不能为空");
        }
        if(questionnaire.getStatus()==null||questionnaire.getStatus().isEmpty()){
            return R.error(512).msg("问卷状态异常");
        }
        if(questionnaire.getAdminId()==0){
            return R.error(513).msg("管理员ID不能为零");
        }
        //判断管理员是否存在
        if(questionnaire.getCreateDate()==null){
            return R.error(514).msg("问卷调查创建日期不能为空");
        }
        if(questionnaire.getUpdateDate()==null) {
            return R.error(515).msg("问卷调查更新日期不能为空");
        }

        boolean b = questionnaireService.updateQ(questionnaireId,questionnaire);
        return b? R.ok().msg("更新问卷成功"):R.error(515).msg("更新问卷失败");

    }
    @GetMapping("/list/{id}")
    public R listQ(@PathVariable("id") int adminId) {//显示所有问卷
        List<Questionnaires> list = questionnaireService.listQ(adminId);
        return R.ok().data(list);
    }
    @PostMapping("/updateStatus/{id}")
    public R updateStatus(@PathVariable("id") int questionnaireId ) {
        if(questionnaireId == 0) {
            return R.error(509).msg("问卷id不能为空");
        }
        Questionnaires questionnaire = questionnaireService.getById(questionnaireId);
        if(questionnaire==null) {
            return R.error(510).msg("该问卷不存在");
        }
        questionnaire.setStatus("已发布");
        boolean b = questionnaireService.updateById(questionnaire);
        return b? R.ok().data(questionnaire):R.error(516).msg("问卷发布成功");
    }
   @GetMapping("/listUserSubmit/{id}")
    public R listUserSubmit(@PathVariable("id") int questionnaireId) {
       if(questionnaireId == 0) {
           return R.error(509).msg("问卷id不能为空");
       }
       Questionnaires questionnaire = questionnaireService.getById(questionnaireId);
       if(questionnaire==null) {
           return R.error(510).msg("该问卷不存在");
       }
    //获取用户提交的问卷调查
       List<Answer> answers = answerService.listUserSubmit(questionnaireId);
       return R.ok( ).data(answers);
   }
}
