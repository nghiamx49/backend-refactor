package com.example.projectbankend.Controllers;

import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.DTO.ProductDetailDTO;
import com.example.projectbankend.DTO.Response;
import com.example.projectbankend.Models.Product;
import com.example.projectbankend.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllAvailableProducts(@RequestParam(defaultValue = "0") @Min(0) Integer page) {
        List<ProductDTO> data = productService.getAllAvailableProducts(page);
        return ResponseEntity.ok(Response.response(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllAvailableProducts(@PathVariable int id) throws Exception {
        ProductDetailDTO data = productService.getProductDetail(id);
        return ResponseEntity.ok(Response.response(data));
    }
}
