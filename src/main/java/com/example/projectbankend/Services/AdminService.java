package com.example.projectbankend.Services;

import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.DTO.ProviderDTO;
import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.Mapper.ProductMapper;
import com.example.projectbankend.Mapper.ProviderMapper;
import com.example.projectbankend.Mapper.UserMapper;
import com.example.projectbankend.Models.Product;
import com.example.projectbankend.Models.Provider;
import com.example.projectbankend.Models.User;
import com.example.projectbankend.Repository.ProductRepository;
import com.example.projectbankend.Repository.ProviderRepository;
import com.example.projectbankend.Repository.UserRepository;
import com.example.projectbankend.RequestModel.UpdateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<ProviderDTO> findAllProviderByStatus(String status) {
        List<Provider> allProvider = providerRepository.findAllByStatus(status);
        List<ProviderDTO> providers = new ArrayList<>();
        for(Provider provider: allProvider) {
            providers.add(ProviderMapper.toProviderDTO(provider));
        }
        return providers;
    }

    public void updateProviderStatus(UpdateStatus updateStatus) throws Exception{
        try {
            providerRepository.updateRegisterStatus(updateStatus.getId(), updateStatus.getStatus());
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    };

    public List<UserDTO> findAllUserByBanStatus(boolean banStatus) {
        List<UserDTO> allUsers = new ArrayList<>();
        List<User> users = userRepository.findAllByBan(banStatus);
        for(User user: users) {
            allUsers.add(UserMapper.toUserDTO(user));
        }
        return allUsers;
    }

    public void banUser(int userId) throws Exception {
        try {
            userRepository.banUser(userId);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<ProductDTO> getAllProductsByStatus(String status) {
        List<Product> products = productRepository.findAllByStatus(status);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products) {
            productDTOS.add(ProductMapper.toProductDTO(product));
        }
        return productDTOS;
    }

    public void updateProductStatus(UpdateStatus updateStatus) throws Exception {
        try{
            productRepository.updateProductStatus(updateStatus.getId(), updateStatus.getStatus());
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}