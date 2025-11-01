package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoLocalItemRepository implements DaoItemRepository {
    private final List<Item> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Item create(Item item, Long userId) {
        item.setId(++id);
        item.setOwnerId(userId);
        list.add(item);

        return list.getLast();
    }

    @Override
    public Item update(Long itemId, Item originItem, Long userId) {
        for (Item item : list) {
            if (item.getId().equals(itemId)) {
                item.setName(originItem.getName() != null ? originItem.getName() : item.getName());
                item.setDescription(originItem.getDescription() != null ? originItem.getDescription() : item.getDescription());
                item.setAvailable(originItem.getAvailable() != null ? originItem.getAvailable() : item.getAvailable());
                item.setOwnerId(userId);

                return item;
            }
        }

        throw new NotFoundException("Элемент не найден");
    }

    @Override
    public Collection<Item> getAll(Long userId) {
        return list.stream().filter(i -> i.getOwnerId().equals(userId)).collect(Collectors.toSet());
    }

    @Override
    public Item get(Long itemId) {
        return list.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Элемент не найден"));
    }

    @Override
    public Collection<Item> search(String text) {
        if (text.isEmpty()) {
            return List.of();
        }

        return list.stream()
                .filter(i -> (i.getName().toLowerCase().contains(text.toLowerCase())
                        || i.getDescription().toLowerCase().contains(text.toLowerCase()))
                        && i.getAvailable().equals(Boolean.TRUE))
                .collect(Collectors.toSet());
    }

    @Override
    public Item delete(Long itemId) {
        Item item = get(itemId);
        list.remove(item);

        return item;
    }
}
