package com.zunino.sport.persistence.repository;

import com.zunino.sport.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository  extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

}
