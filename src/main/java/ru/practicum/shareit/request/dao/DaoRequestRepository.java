package ru.practicum.shareit.request.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

@Repository
public interface DaoRequestRepository {
    ItemRequest create(ItemRequest itemRequest);

    ItemRequest update(Long itemId, ItemRequest itemRequest);

    Collection<ItemRequest> getAll();

    ItemRequest get(Long itemId);

    ItemRequest delete(Long itemId);
}
