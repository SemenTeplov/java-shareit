package ru.practicum.shareit.request.dto;

import lombok.Data;

import ru.practicum.shareit.item.dto.ItemDto;

@Data
public class ItemForRequestDto {
    private Long id;
    private String name;
    private Long ownerId;

    public ItemForRequestDto(ItemDto item) {
        this.id = item.getId();
        this.name = item.getName();
        this.ownerId = item.getOwnerId();
    }
}
