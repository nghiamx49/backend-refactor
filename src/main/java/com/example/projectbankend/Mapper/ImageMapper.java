package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.ImageDTO;
import com.example.projectbankend.Models.ProductImage;

public class ImageMapper {
    public static ImageDTO imageDTO(ProductImage productImage) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage_source(productImage.getImage_source());
        imageDTO.setId(productImage.getId());
        return imageDTO;
    }
}
