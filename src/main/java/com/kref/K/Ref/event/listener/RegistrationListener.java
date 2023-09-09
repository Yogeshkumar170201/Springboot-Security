package com.kref.K.Ref.event.listener;

import com.kref.K.Ref.entity.User;
import com.kref.K.Ref.event.RegistrationEvent;
import com.kref.K.Ref.service.EmailSenderService;
import com.kref.K.Ref.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private static final String SUBJECT_OF_EMAIL = "Email Verification";

    private static final String BODY_OF_EMAIL = "Hi, Please verify your email. Verification link is attached to email. " +
            "Click on the Link below: ";

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.savetoken(user, token);
        String url = event.getApplicationUrl()+
                "verifyRegistration?token="+
                token;
        emailSenderService.sendSimpleMail(user.getEmail(),BODY_OF_EMAIL+url, SUBJECT_OF_EMAIL);
    }
}
