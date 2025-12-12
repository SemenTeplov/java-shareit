package ru.practicum.shareit.request.mapper;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.dto.UserDto;

public class ItemRequestMapper {
    public static ItemRequestDto dtoMapper(ItemRequest itemRequest, UserDto user) {
        ItemRequestDto itemRequestDto = new ItemRequestDto();

        itemRequestDto.setId(itemRequest.getId());
        itemRequestDto.setDescription(itemRequest.getRequest());
        itemRequestDto.setCreated(itemRequest.getCreated());
        itemRequestDto.setUser(user);

        return itemRequestDto;
    }

    public static ItemRequest itemRequestMapper(ItemRequestDto itemRequestDto) {
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.setId(itemRequestDto.getId());
        itemRequest.setRequest(itemRequestDto.getDescription());
        itemRequest.setCreated(itemRequestDto.getCreated());
        itemRequest.setUserId(itemRequestDto.getUser().getId());

        return itemRequest;
    }
}
