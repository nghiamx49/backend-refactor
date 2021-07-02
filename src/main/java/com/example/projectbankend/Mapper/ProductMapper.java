package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.Models.Product;
import com.example.projectbankend.Models.ProductImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTOs = new ProductDTO();
        productDTOs.setId(product.getId());
        productDTOs.setProduct_quantity(product.getQuantity());
        Set<ProductImage> setImage = product.getImages();
        List<String> imageLink = new ArrayList<>();
        for(ProductImage productImage: setImage) {
            imageLink.add(productImage.getImage_source());
        }
        productDTOs.setImage_source(imageLink.get(0));
        productDTOs.setNumber_of_sold(product.getNumber_of_sold());
        productDTOs.setProvider_name(product.getProvider().getStore_name());
        productDTOs.setName(product.getName());
        productDTOs.setUnit_price(product.getUnit_price());
        return productDTOs;

    }
}
