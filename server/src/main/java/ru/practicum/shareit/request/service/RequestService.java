package ru.practicum.shareit.request.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.Collection;

@Service
public interface RequestService {
    ItemRequestDto create(ItemRequestDto itemRequestDto, Long userId);

    ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto);

    Collection<ItemRequestDto> getAllForUser(Long userId);

    Collection<ItemRequestDto> getAllForOtherUsers(Long userId);

    ItemRequestDto get(Long itemId);

    void delete(Long itemId);
}
