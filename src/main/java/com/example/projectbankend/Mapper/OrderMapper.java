package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.CartItemDTO;
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
}
