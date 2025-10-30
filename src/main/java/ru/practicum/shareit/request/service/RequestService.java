package ru.practicum.shareit.request.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.Collection;

@Service
public interface RequestService {
    ItemRequestDto create(ItemRequestDto itemRequestDto);

    ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto);

    Collection<ItemRequestDto> getAll();

    ItemRequestDto get(Long itemId);

    ItemRequestDto delete(Long itemId);
}
