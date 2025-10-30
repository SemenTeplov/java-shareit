package ru.practicum.shareit.request.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoLocalRequestRepository implements DaoRequestRepository {
    private final ItemRequestMapper mapper = new ItemRequestMapper();
    private final List<ItemRequest> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto) {
        ItemRequest itemRequest = mapper.itemRequestMapper(itemRequestDto);
        itemRequest.setId(++id);

        list.add(itemRequest);

        return mapper.dtoMapper(list.getLast());
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        for (ItemRequest itemRequest : list) {
            if (itemRequest.getId().equals(itemId)) {
                itemRequest.setRequest(itemRequestDto.getRequest() != null ? itemRequestDto.getRequest() : itemRequest.getRequest());

                return mapper.dtoMapper(itemRequest);
            }
        }

        throw new NotFoundException("Запрос не найден");
    }

    @Override
    public Collection<ItemRequestDto> getAll() {
        return list.stream().map(mapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        return list.stream()
                .filter(r -> r.getId().equals(itemId)).map(mapper::dtoMapper)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Запрос не найден"));
    }

    @Override
    public ItemRequestDto delete(Long itemId) {
        ItemRequestDto itemRequest = get(itemId);
        list.remove(mapper.itemRequestMapper(itemRequest));

        return itemRequest;
    }
}
