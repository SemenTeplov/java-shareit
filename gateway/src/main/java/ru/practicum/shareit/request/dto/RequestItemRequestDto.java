package ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestItemRequestDto {
    private String request;

    @FutureOrPresent
    private LocalDateTime createDate;
}
