package ru.practicum.shareit.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.request.dao.RequestRepository;
import ru.practicum.shareit.request.dto.ItemForRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final ItemService itemService;

    @Autowired
    public RequestServiceImpl(RequestRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto, Long userId) {
        itemRequestDto.setItem(new ItemForRequestDto(itemService.get(userId)));
        ItemRequest itemRequest = repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto));

        return ItemRequestMapper
                .dtoMapper(itemRequest, itemService.get(itemRequest.getItemId()));
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        repository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Реквест не найден"));
        itemRequestDto.setId(itemId);
        ItemRequest itemRequest = repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto));

        return ItemRequestMapper.dtoMapper(itemRequest, itemService.get(itemRequest.getItemId()));
    }

    @Override
    public Collection<ItemRequestDto> getAllForUser(Long userId) {
        return repository.getAllForUser(userId).stream()
                .map(i -> ItemRequestMapper.dtoMapper(i, itemService.get(i.getItemId())))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<ItemRequestDto> getAllForOtherUsers(Long userId) {
        return repository.getAllForOtherUsers(userId).stream()
                .map(i -> ItemRequestMapper.dtoMapper(i, itemService.get(i.getItemId())))
                .collect(Collectors.toSet());
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        ItemRequest itemRequest = repository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Реквест не найден"));

        return ItemRequestMapper.dtoMapper(itemRequest, itemService.get(itemRequest.getItemId()));
    }

    @Override
    public void delete(Long itemId) {
        if (repository.findById(itemId).isPresent()) {
            ItemRequest request = repository.findById(itemId).get();
            repository.delete(request);
        }
    }
}
