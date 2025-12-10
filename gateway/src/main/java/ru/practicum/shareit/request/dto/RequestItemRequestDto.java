package ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestItemRequestDto {
    private String description;

    @FutureOrPresent
    private LocalDateTime createDate;
}
