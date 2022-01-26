package com.example.demo.bridgelabz.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.bridgelabz.dto.ForgotPasswordDto;
import com.example.demo.bridgelabz.dto.LoginDto;
import com.example.demo.bridgelabz.dto.ResetPassword;
import com.example.demo.bridgelabz.dto.UserRegistrationDto;
import com.example.demo.bridgelabz.model.UserRegistrationData;

public interface IUserRegistrationService {
    List<UserRegistrationData> getUserDeatils();

    UserRegistrationData userRegistration(UserRegistrationDto userDTO);

    UserRegistrationData getUserById(int userId);

    UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDto userDTO);

    void deleteUser(int userId);

    List<UserRegistrationData> getAllUsersData(String token);


    String verifyUser(String token);


    Optional<UserRegistrationData> UserLogin(LoginDto logindto);

    String forgotPassword(ForgotPasswordDto forgotpass);

    UserRegistrationData resetPassword(ResetPassword resetPassword, String token);

    String deleteAll();
}