package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

@Repository
public interface DaoItemRepository {
    Item create(Item item, Long userId);

    Item update(Long itemId, Item item, Long userId);

    Collection<Item> getAll(Long userId);

    Item get(Long itemId);

    Collection<Item> search(String text);

    Item delete(Long itemId);
}
