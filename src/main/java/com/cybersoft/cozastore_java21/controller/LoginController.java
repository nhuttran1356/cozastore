package com.cybersoft.cozastore_java21.controller;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.exception.CustomException;
import com.cybersoft.cozastore_java21.exception.CustomFileNotFoundException;
import com.cybersoft.cozastore_java21.exception.UserNotFoundException;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;
import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import com.cybersoft.cozastore_java21.repository.UserRepository;
import com.cybersoft.cozastore_java21.service.imp.UserServiceImp;
import com.cybersoft.cozastore_java21.utils.JwtHelper;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    UserRepository userRepository;
/*
{
    "status code": 200,...
    "message" : ""
    "data": kiểu gì cũng được
  }
*/
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(token);
        String jwt = jwtHelper.generateToken(email);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@Valid SignupRequest request, BindingResult result){
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data : list ) {
            throw new CustomException(data.getDefaultMessage());
//            System.out.println("KT " + data.getField() + " - " + data.getDefaultMessage());
        }

        boolean isSuccess = userServiceImp.addUser(request);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(isSuccess);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(String email){
        String token = RandomString.make(45);
        System.out.println(email);
        System.out.println(token);

        try {
            userServiceImp.updateResetPasswordToken(token, email);
            String resetLink = "http://localhost:8080/reset_password?token=" + token;
            System.out.println(resetLink);
            sendEmail(email, resetLink);

            BaseResponse response = new BaseResponse();
            response.setStatusCode(200);
            response.setMessage("Reset password token updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatusCode(404);
            errorResponse.setMessage("User not found with email: " + email);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
        catch (MessagingException | UnsupportedEncodingException e){
            BaseResponse errorMailResponse = new BaseResponse();
            errorMailResponse.setStatusCode(404);
            errorMailResponse.setMessage("Error while sending: " );
            return new ResponseEntity<>(errorMailResponse, HttpStatus.OK);
        }
    }

    private void sendEmail(String email, String resetLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@cozastore.com", "CozaStore Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);
    }
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            UserEntity user = userServiceImp.get(token);
            if (user != null) {
                userServiceImp.updatePassword(user, newPassword);

                BaseResponse response = new BaseResponse();
                response.setStatusCode(200);
                response.setMessage("Password reset successful.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                BaseResponse errorResponse = new BaseResponse();
                errorResponse.setStatusCode(404);
                errorResponse.setMessage("Invalid or expired reset token.");
                return new ResponseEntity<>(errorResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatusCode(500);
            errorResponse.setMessage("An error occurred while resetting the password.");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

}
