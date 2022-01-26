package com.example.demo.bridgelabz.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bridgelabz.dto.ForgotPasswordDto;
import com.example.demo.bridgelabz.dto.LoginDto;
import com.example.demo.bridgelabz.dto.ResetPassword;
import com.example.demo.bridgelabz.dto.ResponseDTO;
import com.example.demo.bridgelabz.dto.UserRegistrationDto;
import com.example.demo.bridgelabz.exception.UserRegistrationException;
import com.example.demo.bridgelabz.model.UserRegistrationData;
import com.example.demo.bridgelabz.service.IUserRegistrationService;
import com.example.demo.bridgelabz.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
	@RequestMapping("/userregistrationservice")
	@Slf4j
	public class UserRegistrationController {

	    @Autowired
	    private IUserRegistrationService service;

	    @Autowired
	    private TokenUtil tokenUtil;


	    @RequestMapping(value = {"", "/", "/get"})
	    public ResponseEntity<ResponseDTO> getUserData() {
	        List<UserRegistrationData> usersList = service.getUserDeatils();
	        ResponseDTO response = new ResponseDTO("Get call success", usersList);
	        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	    }

	    @PostMapping("/register")
	    public ResponseEntity<ResponseDTO> addUserRegistrationData(@Valid @RequestBody UserRegistrationDto userDTO) {
	        UserRegistrationData userDetails = service.userRegistration(userDTO);
	        log.debug("User Registration input detaisl: " + userDTO.toString());
	        ResponseDTO response = new ResponseDTO("successfully Registered the user, Verification mail sent", tokenUtil.createToken(userDetails.getUserId()));
	        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	    }

	    @PutMapping("/update/{userId}")
	    public ResponseEntity<ResponseDTO> updateContactData(@PathVariable("userId") int userId,
	                                                         @Valid @RequestBody UserRegistrationDto userDTO) {
	        UserRegistrationData updateUser = service.updateUserRegistrationData(userId, userDTO);
	        log.debug("User Registration Data After Update " + updateUser.toString());
	        ResponseDTO response = new ResponseDTO("Updated contact data for" + userId, updateUser);
	        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	    }

	    @DeleteMapping("/delete/{userId}")
	    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("userId") int userId) {
	        service.deleteUser(userId);
	        ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + userId);
	        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	    }


	    @GetMapping("/readtokendata")
	    public ResponseEntity<ResponseDTO> readdata(@RequestHeader(name = "token") String token) throws UserRegistrationException {
	        List<UserRegistrationData> users = null;
	        users = service.getAllUsersData(token);
	        if (users.size() > 0) {
	            ResponseDTO responseDTO = new ResponseDTO("all user Fetched successfully", users);
	            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	        } else {
	            throw new UserRegistrationException("No Data Found");
	        }

	    }

	    @PostMapping("/userlogin")
	    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDto logindto) {
	        Optional<UserRegistrationData> login = service.UserLogin(logindto);
	        if (login != null) {
	            ResponseDTO dto = new ResponseDTO("User login successfully:", tokenUtil.createToken(login.get().getUserId()));
	            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	        } else {
	            ResponseDTO dto = new ResponseDTO("User login not successfully:", login);
	            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);

	        }
	    }


	    @PostMapping("/verify/{token}")
	    ResponseEntity<ResponseDTO> verifyUser(@Valid @PathVariable String token) {
	        String userVerification = service.verifyUser(token);
	        if (userVerification != null) {
	            ResponseDTO responseDTO = new ResponseDTO("User verified :", userVerification);
	            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	        } else {
	            ResponseDTO responseDTO = new ResponseDTO("User Not verified data:", userVerification);
	            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	        }
	    }

	    @PostMapping("/forgotpassword")
	    ResponseEntity<ResponseDTO> forgotpass(@Valid @RequestBody ForgotPasswordDto forgotpassword) {
	        String forgotPassword = service.forgotPassword(forgotpassword);
	        ResponseDTO response = new ResponseDTO("Reset Password link sent to Email  :", forgotPassword);
	        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	    }

	    @PostMapping("/resetpassword/{token}")
	    ResponseEntity<ResponseDTO> resetpass(@Valid @RequestBody ResetPassword resetpasswordDto, @PathVariable String token) {
	        UserRegistrationData userDetails = service.resetPassword(resetpasswordDto, token);
	        ResponseDTO response = new ResponseDTO("Password changed to   :", userDetails.getPassword());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }



	    @DeleteMapping("/deleteall")
	    public ResponseEntity<ResponseDTO> deleteAllAddressBookData() {
	        String message = service.deleteAll();
	        ResponseDTO respDTO = new ResponseDTO("Deleteall:", message);
	        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	    }


	}