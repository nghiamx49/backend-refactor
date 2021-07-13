package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.CartItemDTO;
import com.example.projectbankend.DTO.OrderItemDTO;
import com.example.projectbankend.Models.Order;
import com.example.projectbankend.Models.ProductImage;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static CartItemDTO toCartDTO(Order order) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(order.getId());
        cartItemDTO.setProduct_name(order.getProduct().getName());
        cartItemDTO.setQuantity_purchased(order.getQuantity_purchased());
        cartItemDTO.setStore_name(order.getProduct().getProvider().getStore_name());
        List<String> images = new ArrayList<>();
        for(ProductImage productImage: order.getProduct().getImages()) {
            images.add(productImage.getImage_source());
        }
        cartItemDTO.setThumbnail_image(images.get(0));
        cartItemDTO.setUnit_price(order.getProduct().getUnit_price());
        cartItemDTO.setProduct_id(order.getProduct().getId());
        return cartItemDTO;
    }

    public static OrderItemDTO toOrderItemDTO(Order order) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(order.getId());
        orderItemDTO.setProduct_name(order.getProduct().getName());
        orderItemDTO.setQuantity_purchased(order.getQuantity_purchased());
        orderItemDTO.setStore_name(order.getProduct().getProvider().getStore_name());
        List<String> images = new ArrayList<>();
        for(ProductImage productImage: order.getProduct().getImages()) {
            images.add(productImage.getImage_source());
        }
        orderItemDTO.setImage_source(images.get(0));
        orderItemDTO.setUnit_price(order.getProduct().getUnit_price());
        orderItemDTO.setProduct_id(order.getProduct().getId());
        orderItemDTO.setDate_of_payment(order.getDate_of_payment());
        orderItemDTO.setPayment_method(order.getPayment().getType());
        orderItemDTO.setAddress(order.getUser().getAccount().getAddress());
        orderItemDTO.setUser_full_name(order.getUser().getFull_name());
        orderItemDTO.setPhone_number(order.getUser().getAccount().getPhone_number());
        return orderItemDTO;
    }
}
