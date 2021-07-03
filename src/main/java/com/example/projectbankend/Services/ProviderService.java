package com.example.projectbankend.Services;

import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.ExceptionHandler.NotFoundException;
import com.example.projectbankend.Mapper.ProductMapper;
import com.example.projectbankend.Models.Account;
import com.example.projectbankend.Models.Category;
import com.example.projectbankend.Models.Product;
import com.example.projectbankend.Models.Provider;
import com.example.projectbankend.Repository.*;
import com.example.projectbankend.RequestModel.CreateProduct;
import com.example.projectbankend.RequestModel.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    public int getProviderId() {
        UserDetails providerPrincipal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account providerAccount = accountRepository.findByUsername(providerPrincipal.getUsername());
        return providerAccount.getProvider().getId();
    }

    public List<ProductDTO> getAllOwnProductsByStatus(String status, Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        List<ProductDTO> productDTOS = new ArrayList<>();
        Page<Product> products = productRepository.findByStatusAndProviderId(status, getProviderId(), paging);
        for(Product product: products) {
            productDTOS.add(ProductMapper.toProductDTO(product));
        }
        return productDTOS;
    }

    public void createProduct(CreateProduct createProduct) throws Exception {
        try {
            productRepository
                    .createNewProduct(createProduct.getName(),
                                    createProduct.getProduct_description(),
                                    createProduct.getQuantity(),
                                    createProduct.getUnit_price(),
                                    createProduct.getCategory_id(),
                                    getProviderId()
                    );
            Product product = productRepository.findByNameAndProviderId(createProduct.getName(), getProviderId());
            for(String image_source: createProduct.getImages()) {
                imageRepository.createProductImage(image_source, product.getId());
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void updateProduct(UpdateProduct updateProduct) throws Exception {
        if(productRepository.findById(updateProduct.getId()) == null) throw new NotFoundException("không tìm thấy sản phẩm");
        try {
            productRepository.updateProduct(updateProduct.getProduct_description(), updateProduct.getQuantity(), updateProduct.getId());
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
