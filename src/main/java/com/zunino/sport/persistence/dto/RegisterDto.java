package com.zunino.sport.persistence.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterDto(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String shippingAddress,

        @Email(message = "el email no es correcto")
        @NotBlank
        String email,

        @NotNull
        @Past(message = "La fecha debe ser antes que hoy")
        LocalDate birthDate,

        @NotBlank
        String password

) {}