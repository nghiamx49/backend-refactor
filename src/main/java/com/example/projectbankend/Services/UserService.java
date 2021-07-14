package com.example.projectbankend.Services;

import com.example.projectbankend.DTO.CartItemDTO;
import com.example.projectbankend.DTO.OrderItemDTO;
import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.ExceptionHandler.NotFoundException;
import com.example.projectbankend.ExceptionHandler.SystemErrorException;
import com.example.projectbankend.ExceptionHandler.WrongPasswordException;
import com.example.projectbankend.Mapper.OrderMapper;
import com.example.projectbankend.Mapper.UserMapper;
import com.example.projectbankend.Models.Account;
import com.example.projectbankend.Models.Order;
import com.example.projectbankend.Repository.*;
import com.example.projectbankend.RequestModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void updateUserDetail(UpdateUser updateUser) throws Exception {
        Account account = accountRepository.findByUserId(getUserId());
        try {
            userRepository.updateUser(getUserId(), updateUser.getFull_name(), updateUser.getZipcode());
            accountRepository.updateAccount(account.getId(), updateUser.getAvatar_source(), updateUser.getAddress(), updateUser.getPhone_number());
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
        if(productRepository.findById(createRating.getProduct_id()) == null) throw new NotFoundException("không tìm thấy sản phẩm");
        ratingRepository.createRating(getUserId(), createRating.getProduct_id(), createRating.getComment(), createRating.getStar(), new Date());
    }

    public List<CartItemDTO> productInCart() {
        List<CartItemDTO> data = new ArrayList<>();
        List<Order> items = orderRepository.findAllByUserIdAndOrderStatusType(getUserId(), "InCart");
        for(Order order: items) {
            data.add(OrderMapper.toCartDTO(order));
        }
        return data;
    }


    private void increaseQuantityPurchased(int id) {
        orderRepository.increaseItemQuantity(id);
    }

    private void decreaseQuantityPurchased(int id) {
        Order cartItem = orderRepository.findById(id);
        if(cartItem.getQuantity_purchased() == 1) {
            orderRepository.deleteItem(id);
        }
        else {
            orderRepository.decreaseItemQuantity(id);
        }
    }

    public void addToCart(OrderRequest orderRequest) throws Exception{
        try{
            Order order = orderRepository.findByProductIdAndUserId(orderRequest.getProduct_id(), getUserId());
            if(order != null) {
                orderRepository.updateQuantity(order.getId(), orderRequest.getQuantity_purchased());
            }
            else {
                orderRepository.addToCart(orderRequest.getProduct_id(), getUserId(), orderRequest.getQuantity_purchased());
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void deleteItem(int id) {
        orderRepository.deleteItem(id);
    }

    public void cartAction(int id, String action) {
        switch (action) {
            case "increase":
                increaseQuantityPurchased(id);
                break;
            case "decrease":
                decreaseQuantityPurchased(id);
                break;
            case "delete":
                deleteItem(id);
                break;
            default:
                break;
        }
    }

    public List<OrderItemDTO> getOrderHistory() {
        List<OrderItemDTO> result = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByUserIdAndOrderStatusType(getUserId(), "Success");
        for(Order order: orders) {
            result.add(OrderMapper.toOrderItemDTO(order));
        }
        return result;
    }

    public OrderItemDTO getOrderDetail(int id) {
        Order order = orderRepository.findByIdAndUserIdAndOrderStatusType(id, getUserId(), "Success");
        if(order == null) throw new NotFoundException("không tìm thấy chi tiết đơn hàng");
        return OrderMapper.toOrderItemDTO(order);
    }

    public void doPayment(Checkout checkout) {
        Order order = orderRepository.findByProductIdAndUserId(checkout.getProduct_id(), getUserId());
        if(order == null) throw new NotFoundException("Không tìm thấy đơn hàng");
        int method = checkout.getMethod().equals("Paypal") ? 2 : 1;
        orderRepository.doPayment(order.getId(), method, new Date(), checkout.getTotal());
        productRepository.updateSoldOut(order.getProduct().getId(), order.getQuantity_purchased());
    }
}
