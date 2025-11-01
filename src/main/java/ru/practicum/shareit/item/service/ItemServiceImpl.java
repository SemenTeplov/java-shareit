package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.item.dao.DaoItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.user.dao.DaoUserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final DaoItemRepository repository;
    private final DaoUserRepository userRepository;

    public ItemServiceImpl(DaoItemRepository repository, DaoUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        userRepository.get(userId);

        return ItemMapper.dtoMapper(repository.create(ItemMapper.itemMapper(itemDto), userId));
    }

    @Override
    public ItemDto update(Long itemId, ItemDto itemDto, Long userId) {
        userRepository.get(userId);

        return ItemMapper.dtoMapper(repository.update(itemId, ItemMapper.itemMapper(itemDto), userId));
    }

    @Override
    public Collection<ItemDto> getAll(Long userId) {
        return repository.getAll(userId).stream().map(ItemMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemDto get(Long itemId) {
        return ItemMapper.dtoMapper(repository.get(itemId));
    }

    @Override
    public Collection<ItemDto> search(String text) {
        return repository.search(text).stream().map(ItemMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public ItemDto delete(Long itemId) {
        return ItemMapper.dtoMapper(repository.delete(itemId));
    }
}
