package com.webtab.shecpsims.controller.user.InformationController.PersonalController;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//修改个人信息
@RestController
@RequestMapping("/user/modify")
public class PersonalInforController {
    @Autowired
    private UserService userService;
    @PostMapping("/patient/{id}")
    public R modifyInformation( @PathVariable("id")int userId,@RequestBody User user) {
        if(userId==0){
            return R.error(504).msg("用户ID不能为空");
        }
       User user2 = userService.getUserById(userId);
        if(user2==null){
            return R.error(506).msg("用户不存在");
        }
        //用户已经注册,但没有填写基础信息
        if(user2.getAge()==0&&user2.getGender()==null&&user2.getEmail()==null
                &&user2.getPhoneNumber()==null&&user2.getReal_name()==null&&user2.getAddress()==null
        &&user2.getCountry()==null&&user2.getArea()==null){
            //表明还没有输入过信息，输入信息
            user2.setAge(user.getAge());
            user2.setGender(user.getGender());
            user2.setEmail(user.getEmail());
            user2.setPhoneNumber(user.getPhoneNumber());
            user2.setReal_name(user.getReal_name());
            user2.setAddress(user.getAddress());
            user2.setCountry(user.getCountry());
            user2.setArea(user.getArea());
            boolean b = userService.updateById(user2);//更新用户信息
            return b?R.ok().data(user2):R.error(533).msg("用户更新失败");
        }
        //用户注册后想修改基础信息

      if(user.getAge()!=user2.getAge()){
          user2.setAge(user.getAge());
      }
      if(user2.getGender()==null){
          user2.setGender(user.getGender());
      }else {
          if(!(user.getGender()).equals(user2.getGender())){
              user2.setGender(user.getGender());
          }
      }
     if(user2.getEmail()==null){
         user2.setEmail(user.getEmail());
     }else{
         if(!user.getEmail().equals(user2.getEmail())){
             user2.setEmail(user.getEmail());
         }
     }
     if(user2.getPhoneNumber()==null){
         user2.setPhoneNumber(user.getPhoneNumber());
     }else{
         if(!user.getPhoneNumber().equals(user2.getPhoneNumber())){
             user2.setPhoneNumber(user.getPhoneNumber());
         }
     }
     if(user2.getReal_name()==null){
         user2.setReal_name(user.getReal_name());
     }else{
         if(!user.getReal_name().equals(user2.getReal_name())){
             user2.setReal_name(user.getReal_name());
         }
     }
    if(user2.getAddress()==null){
        user2.setAddress(user.getAddress());
    }else{
        if(!user.getAddress().equals(user2.getAddress())){
            user2.setAddress(user.getAddress());
        }
    }
     if(user2.getCountry()==null){
         user2.setCountry(user.getCountry());
     }else{
         if(!user.getCountry().equals(user2.getCountry())){
             user2.setCountry(user.getCountry());
         }
     }
     if(user2.getArea()==null){
         user2.setArea(user.getArea());
     }else{
         if(!(user.getArea()).equals(user2.getArea())){
             user2.setArea(user.getArea());
         }
     }
      boolean b = userService.updateById(user2);
      return b?R.ok().data(user2):R.error(533).msg("用户更新失败");

    }
    @PostMapping("/doctor/{id}")
    public R modifyInformation2( @PathVariable("id")int UserId,@RequestBody User user) {
        if(UserId==0){
            return R.error(504).msg("用户ID不能为空");
        }
        User user2 = userService.getUserById(UserId);
        if(user2==null){
            return R.error(506).msg("用户不存在");
        }
        //用户已经注册,但没有填写基础信息
        if(user2.getAge()==0&&user2.getGender()==null&&user2.getEmail()==null
                &&user2.getPhoneNumber()==null&&user2.getReal_name()==null&&user2.getAddress()==null
                &&user2.getCountry()==null&&user2.getArea()==null&&user2.getCity()==null&&user2.getDepartment()==null){
            //表明还没有输入过信息，输入信息
            user2.setAge(user.getAge());
            user2.setGender(user.getGender());
            user2.setEmail(user.getEmail());
            user2.setPhoneNumber(user.getPhoneNumber());
            user2.setReal_name(user.getReal_name());
            user2.setAddress(user.getAddress());
            user2.setCountry(user.getCountry());
            user2.setArea(user.getArea());
            user2.setCity(user.getCity());
            user2.setDepartment(user.getDepartment());
            boolean b = userService.updateById(user2);//更新用户信息
            return b?R.ok().data(user2):R.error(533).msg("用户更新失败");
        }
        //用户注册后想修改基础信息

        if(user.getAge()!=user2.getAge()){
            user2.setAge(user.getAge());
            System.out.println("年龄设置成功");
        }
        if(user2.getGender()==null){
            user2.setGender(user.getGender());
        }else if(user.getGender()==null) {
            user2.setGender(user2.getGender());
        }else{
            if(!(user.getGender()).equals(user2.getGender())){
                user2.setGender(user.getGender());
            }
        }


        if(user2.getEmail()==null){
            user2.setEmail(user.getEmail());
        }else if(user.getEmail()==null) {
            user2.setEmail(user2.getEmail());
        }else{
            if(!(user.getEmail()).equals(user2.getEmail())){
                user2.setEmail(user.getEmail());
            }
        }
        if(user2.getPhoneNumber()==null){
            user2.setPhoneNumber(user.getPhoneNumber());
        }else if(user.getPhoneNumber()==null) {
            user2.setPhoneNumber(user2.getPhoneNumber());
        }else{
            if(!(user.getPhoneNumber()).equals(user2.getPhoneNumber())){
                user2.setPhoneNumber(user.getPhoneNumber());
            }
        }
        if(user2.getReal_name()==null){
            user2.setReal_name(user.getReal_name());
        }else if(user.getReal_name()==null) {
            user2.setReal_name(user2.getReal_name());
        }else{
            if(!(user.getReal_name()).equals(user2.getReal_name())){
                user2.setReal_name(user.getReal_name());
            }
        }
        if(user2.getAddress()==null){
            user2.setAddress(user.getAddress());
        }else if(user.getAddress()==null) {
            user2.setAddress(user2.getAddress());
        }else{
            if(!(user.getAddress()).equals(user2.getAddress())){
                user2.setAddress(user.getAddress());
            }
        }
        if(user2.getCountry()==null){
            user2.setCountry(user.getCountry());
        }else if(user.getCountry()==null) {
            user2.setCountry(user2.getCountry());
        }else{
            if(!(user.getCountry()).equals(user2.getCountry())){
                user2.setCountry(user.getCountry());
            }
        }
        if(user2.getArea()==null){
            user2.setArea(user.getArea());
        }else if(user.getArea()==null) {
            user2.setArea(user2.getArea());
        }else{
            if(!(user.getArea()).equals(user2.getArea())){
                user2.setArea(user.getArea());
            }
        }
        if(user2.getCity()==null){
            user2.setCity(user.getCity());
        }else if(user.getCity()==null) {
            user2.setCity(user2.getCity());
        }else{
            if(!(user.getCity()).equals(user2.getCity())){
                user2.setCity(user.getCity());
            }
        }
        if(user2.getDepartment()==null){
            user2.setDepartment(user.getDepartment());
        }else if(user.getDepartment()==null) {
            user2.setDepartment(user2.getDepartment());
        }else{
            if(!(user.getDepartment()).equals(user2.getDepartment())){
                user2.setDepartment(user.getDepartment());
            }
        }
        boolean b = userService.updateById(user2);
        return b?R.ok().data(user2):R.error(533).msg("用户更新失败");

    }
    @GetMapping("/list/{id}")
    public R informationList(@PathVariable("id") int UserId){
        User user = userService.getUserById(UserId);
        if(user==null){
            return R.error(507).msg("用户不存在");
        }

        return R.ok().data(user);
    }
}
