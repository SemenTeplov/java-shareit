package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Comment;

import java.util.ArrayList;
import java.util.List;

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

    private Booking lastBooking;
    private Booking nextBooking;
    private List<Comment> comments = new ArrayList<>();
}
