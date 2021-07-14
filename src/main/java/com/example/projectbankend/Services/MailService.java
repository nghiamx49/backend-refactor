package com.example.projectbankend.Services;

import com.example.projectbankend.ExceptionHandler.NotFoundException;
import com.example.projectbankend.Models.Account;
import com.example.projectbankend.Repository.AccountRepository;
import com.example.projectbankend.RequestModel.ResetPassword;
import com.example.projectbankend.RequestModel.VerifyOTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private OTPService otpService;
    @Autowired
    private AccountRepository accountRepository;

    public Map<String, Object> sendMail(String email) {
        Account account = accountRepository.findByEmail(email);

        if(account == null) throw new NotFoundException("email không tồn tại trong hệ thống");

        int otp = otpService.generateOTP(account.getUsername());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("EASIEST: Mã xác minh OTP ");
        message.setText("EASIEST: Mã xác minh OTP của quý khách là " + otp + ". Mã có hiệu lực trong 10 phút");
        javaMailSender.send(message);
        Map<String, Object> response = new HashMap<>();
//        response.put("otp", otp);
        response.put("email", account.getEmail());
        response.put("username", account.getUsername());
        return response;
    }

    public void verify_otp(VerifyOTP verifyOTP) {
        String serverOtp = String.valueOf(otpService.getOtp(verifyOTP.getUsername()));
        System.out.println("serverOtp" + serverOtp);
        if (verifyOTP.getOtp().equals(serverOtp)) {
            otpService.clearOTP(verifyOTP.getUsername());
        } else throw new NotFoundException("OTP không trùng khớp");
    }
}
