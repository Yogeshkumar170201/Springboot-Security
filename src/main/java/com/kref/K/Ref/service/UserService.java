package com.kref.K.Ref.service;

import com.kref.K.Ref.entity.User;

public interface UserService {
    void registerUser(User user) throws Exception;

    void savetoken(User user, String token);

    String validateToken(String token);
}
