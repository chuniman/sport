package com.zunino.sport.persistence.dto;

import jakarta.validation.constraints.*;

public record LoginDto(

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password

) {}