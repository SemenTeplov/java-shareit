package ru.practicum.shareit.request.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DaoLocalRequestRepository implements DaoRequestRepository {
    private final List<ItemRequest> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public ItemRequest create(ItemRequest itemRequest) {
        itemRequest.setId(++id);
        list.add(itemRequest);

        return list.getLast();
    }

    @Override
    public ItemRequest update(Long itemId, ItemRequest originItemRequest) {
        for (ItemRequest itemRequest : list) {
            if (itemRequest.getId().equals(itemId)) {
                itemRequest.setRequest(originItemRequest.getRequest() != null ? originItemRequest.getRequest() : itemRequest.getRequest());

                return itemRequest;
            }
        }

        throw new NotFoundException("Запрос не найден");
    }

    @Override
    public Collection<ItemRequest> getAll() {
        return list;
    }

    @Override
    public ItemRequest get(Long itemId) {
        return list.stream()
                .filter(r -> r.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Запрос не найден"));
    }

    @Override
    public ItemRequest delete(Long itemId) {
        ItemRequest itemRequest = get(itemId);
        list.remove(itemRequest);

        return itemRequest;
    }
}
