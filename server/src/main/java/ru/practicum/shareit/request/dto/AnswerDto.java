package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDto {
    private Long itemId;
    private Long ownerId;
    private String name;
}
