package com.zunino.sport.persistence.dto;

import java.time.LocalDateTime;

public record UserDto(

        Long idUser,
        String firstName,
        String lastName,
        String shippingAddress,
        String email,
        LocalDateTime birthDate

) {}