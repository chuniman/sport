package com.zunino.sport.persistence.repository;

import com.zunino.sport.persistence.entity.CartProductEntity;
import com.zunino.sport.persistence.entity.CartProductId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends CrudRepository<CartProductEntity, CartProductId> {

    List<CartProductEntity> findByIdUserId(Long userId);
}
