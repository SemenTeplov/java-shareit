package ru.practicum.shareit.request.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.Collection;

@Repository
public interface DaoRequestRepository {
    ItemRequestDto create(ItemRequestDto itemRequestDto);

    ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto);

    Collection<ItemRequestDto> getAll();

    ItemRequestDto get(Long itemId);

    ItemRequestDto delete(Long itemId);
}
