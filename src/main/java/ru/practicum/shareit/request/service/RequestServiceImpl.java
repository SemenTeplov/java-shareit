package ru.practicum.shareit.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.request.dao.DaoRequestRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final DaoRequestRepository repository;

    @Autowired
    public RequestServiceImpl(DaoRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto) {
        return ItemRequestMapper.dtoMapper(repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto)));
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        repository.findById(itemId).orElseThrow(() -> new NotFoundException("Реквест не найден"));

        itemRequestDto.setId(itemId);

        return ItemRequestMapper.dtoMapper(repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto)));
    }

    @Override
    public Collection<ItemRequestDto> getAll() {
        return repository.findAll().stream().map(ItemRequestMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        return ItemRequestMapper.dtoMapper(repository.findById(itemId).orElseThrow(() -> new NotFoundException("Реквест не найден")));
    }

    @Override
    public ItemRequestDto delete(Long itemId) {
        if (repository.findById(itemId).isPresent()) {
            ItemRequest request = repository.findById(itemId).get();
            repository.delete(request);

            return ItemRequestMapper.dtoMapper(request);
        }

        return null;
    }
}
