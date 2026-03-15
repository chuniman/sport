package com.zunino.sport.web.controllers;


import com.zunino.sport.persistence.dto.CartProductDTO;
import com.zunino.sport.service.CartProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartProductController {

    private CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartProduct(@PathVariable Long userId) {
        return ResponseEntity.ok(cartProductService.getCartProducts(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveCartProduct(@Valid @RequestBody CartProductDTO cartProduct) {
        cartProductService.addProductToCart(cartProduct.userId(), cartProduct.productId(), cartProduct.quantity());
        return ResponseEntity.ok(Map.of("message", "Se añadio articulo al carrito"));
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable Long userId, @PathVariable Long productId) {
        cartProductService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(Map.of("message", "Se elimino el articulo del carrito"));
    }


}
