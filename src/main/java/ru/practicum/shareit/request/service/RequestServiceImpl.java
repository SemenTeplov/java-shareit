package ru.practicum.shareit.request.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.request.dao.DaoRequestRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.Collection;

@Service
public class RequestServiceImpl implements RequestService {
    private final DaoRequestRepository repository;

    public RequestServiceImpl(DaoRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto) {
        return repository.create(itemRequestDto);
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        return repository.update(itemId, itemRequestDto);
    }

    @Override
    public Collection<ItemRequestDto> getAll() {
        return repository.getAll();
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        return repository.get(itemId);
    }

    @Override
    public ItemRequestDto delete(Long itemId) {
        return repository.delete(itemId);
    }
}
