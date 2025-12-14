package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String text;

    @FutureOrPresent
    private LocalDateTime created;
}
