package com.springboot.restapi.service;

import com.springboot.restapi.data.dto.ProductDto;
import com.springboot.restapi.data.dto.ProductResponseDto;

// 예제 6.21
public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
