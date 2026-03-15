package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.ProductDTO;
import com.zunino.sport.persistence.entity.ProductEntity;
import com.zunino.sport.persistence.exception.ProductNotFoundException;
import com.zunino.sport.persistence.mapper.ProductMapper;
import com.zunino.sport.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> findAll(String name) {
        if (name != null) {
            ProductEntity productEntity = productRepository.findByName(name);
            if (productEntity == null) {
                throw new ProductNotFoundException("Product with name " + name + " not found");
            }
            else {
                return productMapper.toDtoList(List.of(productEntity));
            }
        }
        return productMapper.toDtoList((List<ProductEntity>) productRepository.findAll());
    }
}
