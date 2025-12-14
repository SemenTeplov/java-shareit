package ru.practicum.shareit.request.dto;

import lombok.Data;

import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemRequestDto {
    private Long id;
    private String description;
    private LocalDateTime created;
    private UserDto user;
    private List<AnswerDto> items = new ArrayList<>();
}
