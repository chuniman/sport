package com.zunino.sport.web.controllers;


import com.zunino.sport.persistence.dto.CartProductDTO;
import com.zunino.sport.service.CartProductService;
import com.zunino.sport.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartProductController {

    private CartProductService cartProductService;
    private JwtService jwtService;

    public CartProductController(CartProductService cartProductService, JwtService jwtService) {
        this.cartProductService = cartProductService;
        this.jwtService = jwtService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartProduct(@PathVariable Long userId) {
        return ResponseEntity.ok(cartProductService.getCartProducts(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveCartProduct(@Valid @RequestBody CartProductDTO cartProduct, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        Long jwtUserId = jwtService.extractUserId(token);

        if (!jwtUserId.equals(cartProduct.userId())) {
            return ResponseEntity.status(403).body(Map.of("message", "No tienes permiso para agregar productos al carrito de otro usuario"));
        }

        cartProductService.addProductToCart(cartProduct.userId(), cartProduct.productId(), cartProduct.quantity());
        return ResponseEntity.ok(Map.of("message", "Se añadio articulo al carrito"));
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable Long userId, @PathVariable Long productId) {
        cartProductService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(Map.of("message", "Se elimino el articulo del carrito"));
    }


}
