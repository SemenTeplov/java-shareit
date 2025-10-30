package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;

    @NotNull(message = "Необходимо указать почту")
    @Email(message = "Неверно указана электронная почта")
    private String email;
}
