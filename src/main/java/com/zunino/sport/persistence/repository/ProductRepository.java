package com.zunino.sport.persistence.repository;

import com.zunino.sport.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);

}
