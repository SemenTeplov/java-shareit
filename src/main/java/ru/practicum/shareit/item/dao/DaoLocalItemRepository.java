package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoLocalItemRepository implements DaoItemRepository {
    private final ItemMapper mapper = new ItemMapper();
    private final List<Item> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        Item item = mapper.itemMapper(itemDto);

        item.setId(++id);
        item.setOwnerId(userId);
        list.add(item);

        return mapper.dtoMapper(list.getLast());
    }

    @Override
    public ItemDto update(Long itemId, ItemDto itemDto, Long userId) {
        for (Item item : list) {
            if (item.getId().equals(itemId)) {
                item.setName(itemDto.getName() != null ? itemDto.getName() : item.getName());
                item.setDescription(itemDto.getDescription() != null ? itemDto.getDescription() : item.getDescription());
                item.setAvailable(itemDto.getAvailable() != null ? itemDto.getAvailable() : item.getAvailable());
                item.setOwnerId(userId);

                return mapper.dtoMapper(item);
            }
        }

        throw new NotFoundException("Элемент не найден");
    }

    @Override
    public Collection<ItemDto> getAll(Long userId) {
        return list.stream().filter(i -> i.getOwnerId().equals(userId)).map(mapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemDto get(Long itemId) {
        return list.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().map(mapper::dtoMapper)
                .orElseThrow(() -> new NotFoundException("Элемент не найден"));
    }

    @Override
    public Collection<ItemDto> search(String text) {
        if (text.isEmpty()) {
            return List.of();
        }

        return list.stream()
                .filter(i -> (i.getName().toLowerCase().contains(text.toLowerCase())
                        || i.getDescription().toLowerCase().contains(text.toLowerCase()))
                        && i.getAvailable().equals(Boolean.TRUE))
                .map(mapper::dtoMapper)
                .collect(Collectors.toSet());
    }

    @Override
    public ItemDto delete(Long itemId) {
        ItemDto itemDto = get(itemId);
        list.remove(mapper.itemMapper(itemDto));

        return itemDto;
    }
}
