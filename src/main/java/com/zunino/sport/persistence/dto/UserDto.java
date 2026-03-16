package com.zunino.sport.persistence.dto;

import java.time.LocalDate;

public record UserDto(

        Long idUser,
        String firstName,
        String lastName,
        String shippingAddress,
        String email,
        LocalDate birthDate

) {}