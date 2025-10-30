package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private Long ownerId;

    @NotBlank(message = "Должно быть указано название")
    private String name;

    @NotBlank(message = "Должно быть указано описание")
    private String description;

    @NotNull(message = "Должен быть указан доступ")
    private Boolean  available;
}
