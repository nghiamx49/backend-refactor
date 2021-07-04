package com.example.projectbankend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @GetMapping("profile")
    public ResponseEntity<?> getUserProfile() {
        return null;
    }

    @PutMapping("update_profile")
    public ResponseEntity<?> updateProfiler() {
        return null;
    }

    @PatchMapping("change_password")
    public ResponseEntity<?> changePassword() {
        return null;
    }

    @PostMapping("rating_prduct")
    public ResponseEntity<?> rating() {
        return null;
    }

    @GetMapping("cart")
    public ResponseEntity<?> cart() {
        return null;
    }

    @PatchMapping("cart/action")
    public ResponseEntity<?> cartAction() {
        return null;
    }

    @GetMapping("order_history")
    public ResponseEntity<?> orderHistory() {
       return null;
    }

    @GetMapping("order_history/{id}")
    public ResponseEntity<?> orderDetail(@PathVariable int id) {
        return null;
    }
}
