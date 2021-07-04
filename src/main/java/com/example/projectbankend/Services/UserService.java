package com.example.projectbankend.Services;

import com.example.projectbankend.DTO.CartItemDTO;
import com.example.projectbankend.DTO.OrderItemDTO;
import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.ExceptionHandler.NotFoundException;
import com.example.projectbankend.ExceptionHandler.WrongPasswordException;
import com.example.projectbankend.Mapper.UserMapper;
import com.example.projectbankend.Models.Account;
import com.example.projectbankend.Models.Order;
import com.example.projectbankend.Repository.*;
import com.example.projectbankend.RequestModel.ChangePassword;
import com.example.projectbankend.RequestModel.CreateRating;
import com.example.projectbankend.RequestModel.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;

    private int getUserId() {
        UserDetails providerPrincipal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account providerAccount = accountRepository.findByUsername(providerPrincipal.getUsername());
        return providerAccount.getUser().getId();
    }

    public UserDTO userDetail() {
        return UserMapper.toUserDTO(userRepository.findById(getUserId()));
    }

    public void updateUserDetail(UserDTO userDTO) throws Exception {
        Account account = accountRepository.findByUserId(userDTO.getId());
        try {
            userRepository.updateUser(userDTO.getId(), userDTO.getFull_name(), userDTO.getZipcode());
            accountRepository.updateAccount(account.getId(), userDTO.getAvatar_source(), userDTO.getAddress(), userDTO.getPhone_number());
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void changePassword(ChangePassword changePassword) {
        Account account = accountRepository.findByUserId(getUserId());
        if(passwordEncoder.matches(changePassword.getOld_password(), account.getPassword()) == false)
            throw new WrongPasswordException("mật khẩu cũ không khớp");
        accountRepository.updatePassword(account.getId(), passwordEncoder.encode(changePassword.getNew_password()));
    }

    public void ratingProduct(CreateRating createRating) {
        if(productRepository.findById(createRating.getProductId()) == null) throw new NotFoundException("không tìm thấy sản phẩm");
        ratingRepository.createRating(getUserId(), createRating.getProductId(), createRating.getComment(), createRating.getStar(), new Date());
    }

    public List<CartItemDTO> productInCart(int userId) {
        return orderRepository.cart(userId);
    }


    public void increaseQuantityPurchased(int itemId) {
        orderRepository.increaseItemQuantity(itemId);
    }

    public void decreaseQuantityPurchased(int itemId) {
        Order cartItem = orderRepository.findById(itemId);
        if(cartItem.getQuantity_purchased() == 1) {
            orderRepository.deleteItem(itemId);
        }
        else {
            orderRepository.decreaseItemQuantity(itemId);
        }
    }

    public void addToCart(int userId, OrderRequest orderRequest) {
        orderRepository.addToCart(orderRequest.getProduct_id(), userId, orderRequest.getQuantity_purchased());
    }

    public void deleteItem(int id) {
        orderRepository.deleteItem(id);
    }

    public List<OrderItemDTO> getOrderHistory(int userId) {
        return orderRepository.getOrderHistory(userId);
    }

    public OrderItemDTO getOrderDetail(int id) {
        OrderItemDTO orderItemDTO = orderRepository.getById(id, getUserId());
        if(orderItemDTO == null) throw new NotFoundException("không tìm thấy chi tiết đơn hàng");
        return orderItemDTO;
    }
}
