package com.zunino.sport.persistence.repository;

import com.zunino.sport.persistence.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}
