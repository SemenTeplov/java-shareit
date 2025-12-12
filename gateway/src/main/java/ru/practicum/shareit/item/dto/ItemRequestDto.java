package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequestDto {
    @NotBlank(message = "Должно быть указано название")
    private String name;

    @NotBlank(message = "Должно быть указано описание")
    private String description;

    private Long commentId;

    private Long requestId;

    @NotNull(message = "Должен быть указан доступ")
    private Boolean  available;
}
