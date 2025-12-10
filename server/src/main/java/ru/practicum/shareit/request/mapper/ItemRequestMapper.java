package ru.practicum.shareit.request.mapper;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.dto.ItemForRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

public class ItemRequestMapper {
    public static ItemRequestDto dtoMapper(ItemRequest itemRequest, ItemDto item) {
        ItemRequestDto itemRequestDto = new ItemRequestDto();

        itemRequestDto.setId(itemRequest.getId());
        itemRequestDto.setDescription(itemRequest.getRequest());
        itemRequestDto.setCreateDate(itemRequest.getCreateDate());
        itemRequestDto.setItem(new ItemForRequestDto(item));

        return itemRequestDto;
    }

    public static ItemRequest itemRequestMapper(ItemRequestDto itemRequestDto) {
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.setId(itemRequestDto.getId());
        itemRequest.setRequest(itemRequestDto.getDescription());
        itemRequest.setCreateDate(itemRequestDto.getCreateDate());
        itemRequest.setItemId(itemRequestDto.getItem().getId());

        return itemRequest;
    }
}
