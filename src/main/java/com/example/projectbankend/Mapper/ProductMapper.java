package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.ImageDTO;
import com.example.projectbankend.DTO.ProductDTO;
import com.example.projectbankend.DTO.ProductDetailDTO;
import com.example.projectbankend.DTO.RateDTO;
import com.example.projectbankend.Models.Product;
import com.example.projectbankend.Models.ProductImage;
import com.example.projectbankend.Models.Rate;

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
        productDTOs.setCategory_name(product.getCategory().getName());
        return productDTOs;
    }

    public static ProductDetailDTO toProductDetailDTO(Product product, List<RateDTO> rates) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(product.getId());
        productDetailDTO.setProduct_quantity(product.getQuantity());
        Set<ProductImage> setImage = product.getImages();
        List<ImageDTO> imageLink = new ArrayList<>();
        for(ProductImage productImage: setImage) {
            imageLink.add(ImageMapper.imageDTO(productImage));
        }
        productDetailDTO.setImage_sources((ArrayList<ImageDTO>) imageLink);
        productDetailDTO.setNumber_of_sold(product.getNumber_of_sold());
        productDetailDTO.setProvider_name(product.getProvider().getStore_name());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setUnit_price(product.getUnit_price());
        productDetailDTO.setRating((ArrayList<RateDTO>) rates);
        productDetailDTO.setCategory_name(product.getCategory().getName());
        productDetailDTO.setProduct_description(product.getProduct_description());
        return productDetailDTO;
    }
}
