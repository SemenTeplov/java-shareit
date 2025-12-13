package ru.practicum.shareit.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.request.dao.RequestRepository;
import ru.practicum.shareit.request.dto.AnswerDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final UserRepository userRepository;
    private final ItemService itemService;

    @Autowired
    public RequestServiceImpl(RequestRepository repository, ItemService itemService, UserRepository userRepository) {
        this.repository = repository;
        this.itemService = itemService;
        this.userRepository = userRepository;
    }

    @Override
    public ItemRequestDto create(ItemRequestDto itemRequestDto, Long userId) {
        itemRequestDto.setUser(UserMapper.dtoMapper(userRepository
                .findById(userId).orElseThrow(() -> new NotFoundException("Такого пользователя нет"))));
        itemRequestDto.setId(repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto)).getId());

        return itemRequestDto;
    }

    @Override
    public ItemRequestDto update(Long itemId, ItemRequestDto itemRequestDto) {
        repository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Реквест не найден"));
        itemRequestDto.setId(itemId);
        itemRequestDto.setId(repository.save(ItemRequestMapper.itemRequestMapper(itemRequestDto)).getId());

        return itemRequestDto;
    }

    @Override
    public Collection<ItemRequestDto> getAllForUser(Long userId) {
        return getItemRequestDtoCollection(repository.getAllForUser(userId));
    }

    @Override
    public Collection<ItemRequestDto> getAllForOtherUsers(Long userId) {
        return getItemRequestDtoCollection(repository.getAllForOtherUsers(userId));
    }

    @Override
    public ItemRequestDto get(Long itemId) {
        ItemRequest itemRequest = repository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Реквест не найден"));

        ItemRequestDto request = ItemRequestMapper.dtoMapper(itemRequest, UserMapper
                .dtoMapper(userRepository.findById(itemRequest.getUserId()).get()));

        request.setItems(itemService.searchByRequestId(request.getId()).stream()
                .map(item -> new AnswerDto(item.getId(), item.getOwnerId(), item.getName())).toList());

        return request;
    }

    @Override
    public void delete(Long itemId) {
        if (repository.findById(itemId).isPresent()) {
            ItemRequest request = repository.findById(itemId).get();
            repository.delete(request);
        }
    }

    private Collection<ItemRequestDto> getItemRequestDtoCollection(Collection<ItemRequest> collection) {
        Collection<ItemDto> items = itemService.getAll();
        Map<Long, UserDto> users = userRepository.findAll().stream()
                .map(UserMapper::dtoMapper)
                .collect(Collectors.toMap(UserDto::getId, u -> u));

        return collection.stream()
                .map(i -> ItemRequestMapper.dtoMapper(i, users.get(i.getUserId())))
                .peek(i -> i.setItems(items.stream()
                        .filter(e -> e.getRequestId() != null && e.getRequestId().equals(i.getId()))
                        .map(item -> new AnswerDto(item.getId(), item.getOwnerId(), item.getName())).toList()))
                .collect(Collectors.toSet());
    }
}
