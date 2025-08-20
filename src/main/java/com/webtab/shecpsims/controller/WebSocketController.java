package com.webtab.shecpsims.controller;

import com.webtab.shecpsims.model.entity.user.PointsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
//1.用户连接上线：如果是一个客服上线，就保存一个客服信息，同时去查找有没有再排队中的用户，如果有就给他们俩建立会话关系；如果是一个用户上线，先建立保存用户信息，再去查找有没有空闲客服，如果有就建立会话关系，如果没有就告诉他系统繁忙，让他进入等待状态。
//2.用户下线：如果是客户下线，就删除客服信息，为了方便，我直接让用户刷新页面重新匹配客服，其实这样做不是很好；如果是用户下线，就删除用户信息，让空闲下来的客服和排队中的用户建立会话关系。
//3.发送消息：如果是用户发的消息，就将消息推送给相应客服；如果是客服发的消息，就推送给相应用户。

@Controller
public class WebSocketController {

    @MessageMapping("/points")
    @SendTo("/topic/points")
    public PointsMessage sendPoints(PointsMessage message) {
        System.out.println("成功发送消息："+message);
        return message;
    }
}