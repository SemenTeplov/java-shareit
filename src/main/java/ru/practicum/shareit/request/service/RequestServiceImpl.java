package ru.practicum.shareit.request.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.request.dao.DaoRequestRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final DaoRequestRepository repository;

    public RequestServiceImpl(DaoRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto) {
        return ItemRequestMapper.dtoMapper(repository.create(ItemRequestMapper.itemRequestMapper(itemRequestDto)));
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        return ItemRequestMapper.dtoMapper(repository.update(itemId, ItemRequestMapper.itemRequestMapper(itemRequestDto)));
    }

    @Override
    public Collection<ItemRequestDto> getAll() {
        return repository.getAll().stream().map(ItemRequestMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        return ItemRequestMapper.dtoMapper(repository.get(itemId));
    }

    @Override
    public ItemRequestDto delete(Long itemId) {
        return ItemRequestMapper.dtoMapper(repository.delete(itemId));
    }
}
