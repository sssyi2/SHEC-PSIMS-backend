package com.webtab.shecpsims.controller;

import com.webtab.shecpsims.model.entity.user.PointsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/points")
    @SendTo("/topic/points")
    public PointsMessage sendPoints(PointsMessage message) {
        System.out.println("成功发送消息："+message);
        return message;
    }
}