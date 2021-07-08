package com.example.projectbankend.Controllers;

import com.example.projectbankend.DTO.CartItemDTO;
import com.example.projectbankend.DTO.OrderItemDTO;
import com.example.projectbankend.DTO.Response;
import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.Models.Validator.Action;
import com.example.projectbankend.RequestModel.*;
import com.example.projectbankend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public ResponseEntity<?> getUserProfile() {
        UserDTO userDetail = userService.userDetail();
        return ResponseEntity.ok(Response.response(userDetail, 0));
    }

    @PutMapping("update_profile")
    public ResponseEntity<?> updateProfiler(@Valid @RequestBody UpdateUser updateUser) throws Exception {
        userService.updateUserDetail(updateUser);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @PutMapping("change_password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePassword changePassword) {
        userService.changePassword(changePassword);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @PostMapping("rating_product")
    public ResponseEntity<?> rating(@Valid @RequestBody CreateRating createRating) {
        userService.ratingProduct(createRating);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @GetMapping("cart")
    public ResponseEntity<?> cart() {
        List<CartItemDTO> data = userService.productInCart();
        return ResponseEntity.ok(Response.response(data, 1));
    }

    @PostMapping("cart/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody OrderRequest orderRequest)  throws Exception{
        userService.addToCart(orderRequest);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @PutMapping("cart/{id}/{action}")
    public ResponseEntity<?> cartAction(@PathVariable int id, @PathVariable @Action String action) {
        userService.cartAction(id, action);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @GetMapping("order_history")
    public ResponseEntity<?> orderHistory() {
        List<OrderItemDTO> data = userService.getOrderHistory();
       return ResponseEntity.ok(Response.response(data, 1));
    }

    @GetMapping("order_history/{id}")
    public ResponseEntity<?> orderDetail(@PathVariable int id) {
        OrderItemDTO data = userService.getOrderDetail(id);
        return ResponseEntity.ok(Response.response(data, 0));
    }

    @PutMapping("cart/checkout")
    public ResponseEntity<?> checkout(@Valid @RequestBody Checkout checkout) {
        userService.doPayment(checkout);
        return ResponseEntity.ok(Response.responseWithoutData());
    }
}
