package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Collection;

@Repository
public interface DaoItemRepository {
    ItemDto create(ItemDto itemDto, Long userId);

    ItemDto update(Long itemId, ItemDto itemDto, Long userId);

    Collection<ItemDto> getAll(Long userId);

    ItemDto get(Long itemId);

    Collection<ItemDto> search(String text);

    ItemDto delete(Long itemId);
}
