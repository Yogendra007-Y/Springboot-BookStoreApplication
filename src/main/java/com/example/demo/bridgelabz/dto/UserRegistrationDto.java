package com.example.demo.bridgelabz.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegistrationDto {
    @Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s.]{2,}$", message = "FirstName is invalid")
    @NotEmpty(message = "Name can not be NULL")
    public String firstName;
    @Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s.]{2,}$", message = "LastName is invalid")
    @NotEmpty(message = "Name can not be NULL")
    public String lastName;
    public String kyc;
    public String emailId;
    public String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date dob;
    public int otp;
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date purchaseDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date expiryDate;
}
