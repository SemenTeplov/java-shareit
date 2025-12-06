package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {
    @NotBlank(message = "Должно быть указано название")
    private String name;

    @NotBlank(message = "Должно быть указано описание")
    private String description;

    private Long commentId;

    @NotNull(message = "Должен быть указан доступ")
    private Boolean  available;
}
