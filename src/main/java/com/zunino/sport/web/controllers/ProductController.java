package com.zunino.sport.web.controllers;

import com.zunino.sport.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.findAll(name));
    }

}
