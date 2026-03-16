package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.CartResponseProductDTO;
import com.zunino.sport.persistence.entity.CartProductEntity;
import com.zunino.sport.persistence.entity.CartProductId;
import com.zunino.sport.persistence.entity.ProductEntity;
import com.zunino.sport.persistence.exception.NotEnoughProductException;
import com.zunino.sport.persistence.exception.ProductAlreadyInCartException;
import com.zunino.sport.persistence.exception.ProductNotFoundException;
import com.zunino.sport.persistence.exception.UserNotFoundException;
import com.zunino.sport.persistence.mapper.CartProductMapper;
import com.zunino.sport.persistence.repository.CartProductRepository;
import com.zunino.sport.persistence.repository.ProductRepository;
import com.zunino.sport.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {
    private CartProductRepository cartProductRepository;
    private ProductRepository productRepository;
    private CartProductMapper cartProductMapper;
    private UserRepository userRepository;

    public CartProductService(CartProductRepository cartProductRepository,UserRepository userRepository,
                              ProductRepository productRepository, CartProductMapper cartProductMapper) {
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
        this.cartProductMapper = cartProductMapper;
        this.userRepository = userRepository;
    }

    public boolean addProductToCart(Long userId, Long productId, int quantity) {
        boolean userExists = userRepository.existsById(userId);

        if (!userExists) {
            throw new UserNotFoundException("User not found");
        }

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

        CartProductId cartProductId = new CartProductId();
        cartProductId.setUserId(userId);
        cartProductId.setProductId(productId);
        if (cartProductRepository.existsById(cartProductId)){
            throw new ProductAlreadyInCartException("El producto ya existe en el carrito");
        }

        if (quantity > productEntity.getStock()) {
            throw new NotEnoughProductException("La cantidad debe ser menor a la cantidad en stock");
        }
        CartProductEntity cartProductEntity = new CartProductEntity();
        cartProductEntity.setId(cartProductId);
        cartProductEntity.setQuantity(quantity);
        cartProductEntity.setProduct(productEntity);
        cartProductRepository.save(cartProductEntity);
        return true;
    }

    public List<CartResponseProductDTO> getCartProducts(Long userId) {
        List<CartProductEntity> cartItems = cartProductRepository.findByIdUserId(userId);
        List<CartResponseProductDTO> cartList = cartItems.stream()
                .map(cartProductMapper::toDto)
                .toList();
        return cartList;
    }

    public boolean removeProductFromCart(Long userId, Long productId) {
        CartProductId cartProductId = new CartProductId();
        cartProductId.setUserId(userId);
        cartProductId.setProductId(productId);
        if (!cartProductRepository.existsById(cartProductId)){
            throw new ProductNotFoundException("Producto no encontrado");
        }
        cartProductRepository.deleteById(cartProductId);
        return true;
    }

}
