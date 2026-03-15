package com.zunino.sport.persistence.repository;

import com.zunino.sport.persistence.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserIdUser(Long idUser);
}
