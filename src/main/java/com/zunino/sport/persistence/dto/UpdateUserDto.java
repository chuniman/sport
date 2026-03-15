package com.zunino.sport.persistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UpdateUserDto(
                            String lastName,

                            String shippingAddress,

                            @Email(message = "el email no es correcto")
                            String email,

                            @Past(message = "La fecha debe ser antes que hoy")
                            LocalDate birthDate,

                            String password) {
}
