package com.example.projectbankend.Controllers;

import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.DTO.Response;
import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.Models.Validator.Ban;
import com.example.projectbankend.Models.Validator.Status;
import com.example.projectbankend.RequestModel.UpdateStatus;
import com.example.projectbankend.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("providers/{status}")
    public ResponseEntity<?> allProviders(@PathVariable @Status String status) {
        Map<String, Object> responseBody =
                Response.response(adminService.findAllProviderByStatus(status));
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("providers/update_status")
    public ResponseEntity<?> updateRegisterStatus(@Valid @RequestBody UpdateStatus updateStatus) throws Exception {
        adminService.updateProviderStatus(updateStatus);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @GetMapping("users/{status}")
    public ResponseEntity<?> allUsers(@PathVariable @Ban String status) {
        List<UserDTO> data;
        if(status.equals("ban")) {
            data = adminService.findAllUserByBanStatus(true);
        }
        else {
            data = adminService.findAllUserByBanStatus(false);
        }
        return ResponseEntity.ok(Response.response(data));
    }

    @PatchMapping("users/{id}/ban")
    public ResponseEntity<?> banUser(@PathVariable int id) throws Exception {
        adminService.banUser(id);
        return ResponseEntity.ok(Response.responseWithoutData());
    }

    @GetMapping("/product_requests/{status}")
    public ResponseEntity<?> allProductRequests(@PathVariable @Status  String status) {
        List<ProductDTO> data = adminService.getAllProductsByStatus(status);
        return ResponseEntity.ok(Response.response(data));
    }

    @PatchMapping("/product_requests/update_status")
    public ResponseEntity<?> updateProductStatus(@Valid @RequestBody UpdateStatus updateStatus) throws Exception {
        adminService.updateProductStatus(updateStatus);
        return ResponseEntity.ok(Response.responseWithoutData());
    }
}
