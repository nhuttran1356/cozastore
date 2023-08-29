package com.cybersoft.cozastore_java21.service.imp;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);

    void updateResetPasswordToken(String token, String email);

    UserEntity get(String resetPasswordToken);
    void updatePassword(UserEntity user, String newPassword);
}
