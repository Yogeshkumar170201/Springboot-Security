package com.kref.K.Ref.controller;

import com.kref.K.Ref.entity.User;
import com.kref.K.Ref.event.RegistrationEvent;
import com.kref.K.Ref.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;



    @PostMapping("/register")
    public String registerUser(@RequestBody User user, final HttpServletRequest request){
        try {
            userService.registerUser(user);
            publisher.publishEvent(new RegistrationEvent(
                    user,
                    applicationUrl(request)
            ));
            return "User Registration Successfully";
        } catch (Exception e) {
            return "User Already exist";
        }
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                "/"+
                request.getContextPath();
    }

    @GetMapping("/verifyRegistration")
    public String verifyToken(@RequestParam("token") String token){
        String result = userService.validateToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verification Successfully";
        }else{
            return "Bad user";
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hey")
    public String hey(){
        return "hey";
    }

}
