package com.example.projectbankend.Controllers;

import com.example.projectbankend.RequestModel.LoginRequest;
import com.example.projectbankend.RequestModel.ProviderRegister;
import com.example.projectbankend.RequestModel.UserRegister;
import com.example.projectbankend.Services.AuthenticateService;
import com.example.projectbankend.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authenticate/")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();
        if(authenticateService.checkLogin(loginRequest)) {
            response.put("message", "login successfully");
            response.put("token", jwtUtil.generateToken(loginRequest.getUsername()));
            response.put("account", authenticateService.accountDetail(loginRequest.getUsername()));
            response.put("isLoggedIn", true);
        }
        else {
            response.put("message", "login failed");
            response.put("isLoggedIn", false);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("register/user")
    public ResponseEntity<?> registerAsNormalUser(@Valid @RequestBody UserRegister userRegister) throws Exception {
        try {
            authenticateService.registerAsUser(userRegister);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "register successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("register/provider")
    public ResponseEntity<?> registerAsNormalUser(@Valid @RequestBody ProviderRegister providerRegister) throws Exception {
        try {
            authenticateService.registerAsProvider(providerRegister);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "register successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
