package com.kref.K.Ref.service;

import com.kref.K.Ref.entity.User;
import com.kref.K.Ref.entity.VerificationToken;
import com.kref.K.Ref.repository.TokenRepository;
import com.kref.K.Ref.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) throws Exception {
        User userFound = userRepository.findByEmail(user.getEmail());
        if(userFound==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }else{
            throw new Exception("User Already exist");
        }
    }

    @Override
    public void savetoken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken==null){
            return "Invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0){
            return "Expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "Valid";
    }
}
