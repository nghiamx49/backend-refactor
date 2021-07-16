package com.example.projectbankend.Controllers;

import com.example.projectbankend.DTO.Response;
import com.example.projectbankend.RequestModel.*;
import com.example.projectbankend.Services.AuthenticateService;
import com.example.projectbankend.Services.MailService;
import com.example.projectbankend.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authenticate/")
@Validated
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MailService mailService;

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();
        if(authenticateService.checkLogin(loginRequest)) {
            response.put("message", "đăng nhập thành công");
            response.put("token", jwtUtil.generateToken(loginRequest.getUsername()));
            response.put("account", authenticateService.accountDetail(loginRequest.getUsername()));
            response.put("isLoggedIn", true);
            response.put("status", 200);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            response.put("message", "đăng nhập thất bại. Tài khoản hoặc mật khẩu không đúng");
            response.put("isLoggedIn", false);
            response.put("status", 401);
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("check")
    public ResponseEntity<?> checkAuthenticate() {
            return ResponseEntity.ok(authenticateService.getAuthenticate());
    }

    @PostMapping("register/user")
    public ResponseEntity<?> registerAsNormalUser(@Valid @RequestBody UserRegister userRegister)  {
            authenticateService.registerAsUser(userRegister);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "đăng kí thành công");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("register/provider")
    public ResponseEntity<?> registerAsNormalUser(@Valid @RequestBody ProviderRegister providerRegister) {
            authenticateService.registerAsProvider(providerRegister);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "đăng kí thành công");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("verify_mail")
    public ResponseEntity<?> verifyMail(@Valid @RequestBody VerifyMail verifyMail) {
        Map<String, Object> data = mailService.sendMail(verifyMail.getEmail());
        return ResponseEntity.ok(Response.response(data, 0));
    }

    @PostMapping("verify_otp")
    public ResponseEntity<?> verifyOTP(@Valid @RequestBody VerifyOTP verifyOTP) {
        mailService.verify_otp(verifyOTP);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @PutMapping("reset_password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPassword resetPassword) {
        authenticateService.resetPassword(resetPassword);
        return ResponseEntity.ok(Response.responseWithoutData());
    }
}
