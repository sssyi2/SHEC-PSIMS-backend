package com.webtab.shecpsims.service.user;

import com.webtab.shecpsims.model.entity.user.PointsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PointsService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void updateUserPoints(String username, int points,int inviteSum) {
        PointsMessage message = new PointsMessage();
        message.setInviteSum(inviteSum);
        message.setUsername(username);
        message.setPoints(points);
        System.out.println("成功发送消息"+message);
        messagingTemplate.convertAndSend("/topic/points", message);
    }
    public void updateUserPoints2(int points) {
        PointsMessage message = new PointsMessage();
        message.setPoints(points);
        System.out.println("成功发送消息"+message);
        messagingTemplate.convertAndSend("/topic/userpoints", message);
    }
}